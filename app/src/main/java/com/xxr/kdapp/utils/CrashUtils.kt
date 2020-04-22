package com.xxr.kdapp.utils

import android.Manifest.permission
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Process
import android.util.Log
import androidx.annotation.RequiresPermission
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import kotlin.system.exitProcess

/**
 * Author: xuxiarong
 * Date: 2020/4/122 09:20
 * Description: Crash相关的工具类
 */
class CrashUtils private constructor() {
    companion object {
        private var defaultDir: String? = null
        private var dir: String? = null
        private var versionName: String? = null
        private var versionCode = 0
        private val FILE_SEP = System.getProperty("file.separator")
        @SuppressLint("SimpleDateFormat")
        private val FORMAT: Format = SimpleDateFormat("MM-dd HH-mm-ss")
        private var DEFAULT_UNCAUGHT_EXCEPTION_HANDLER: Thread.UncaughtExceptionHandler? = null
        private var UNCAUGHT_EXCEPTION_HANDLER: Thread.UncaughtExceptionHandler? = null
        private var sOnCrashListener: OnCrashListener? = null
        /**
         * Initialization.
         *
         * Must hold `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />`
         */
        @SuppressLint("MissingPermission")
        fun init() {
            init("")
        }

        /**
         * Initialization
         *
         * Must hold `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />`
         *
         * @param crashDir The directory of saving crash information.
         */
        @RequiresPermission(permission.WRITE_EXTERNAL_STORAGE)
        fun init(crashDir: File) {
            init(crashDir.absolutePath, null)
        }

        /**
         * Initialization
         *
         * Must hold `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />`
         *
         * @param crashDirPath The directory's path of saving crash information.
         */
        @RequiresPermission(permission.WRITE_EXTERNAL_STORAGE)
        fun init(crashDirPath: String) {
            init(crashDirPath, null)
        }

        /**
         * Initialization
         *
         * Must hold `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />`
         *
         * @param onCrashListener The crash listener.
         */
        @SuppressLint("MissingPermission")
        fun init(onCrashListener: OnCrashListener?) {
            init("", onCrashListener)
        }

        /**
         * Initialization
         *
         * Must hold `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />`
         *
         * @param crashDir        The directory of saving crash information.
         * @param onCrashListener The crash listener.
         */
        @RequiresPermission(permission.WRITE_EXTERNAL_STORAGE)
        fun init(crashDir: File, onCrashListener: OnCrashListener?) {
            init(crashDir.absolutePath, onCrashListener)
        }

        /**
         * Initialization
         *
         * Must hold `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />`
         *
         * @param crashDirPath    The directory's path of saving crash information.
         * @param onCrashListener The crash listener.
         */
        @RequiresPermission(permission.WRITE_EXTERNAL_STORAGE)
        fun init(crashDirPath: String, onCrashListener: OnCrashListener?) {
            dir = if (isSpace(crashDirPath)) {
                null
            } else {
                if (crashDirPath.endsWith(FILE_SEP)) crashDirPath else crashDirPath + FILE_SEP
            }
            defaultDir =
                if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() && Utils.getApp().externalCacheDir != null) Utils.getApp().externalCacheDir.toString() + FILE_SEP + "crash" + FILE_SEP else {
                    Utils.getApp().cacheDir.toString() + FILE_SEP + "crash" + FILE_SEP
                }
            sOnCrashListener = onCrashListener
            Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER)
        }

        ///////////////////////////////////////////////////////////////////////////
// other utils methods
///////////////////////////////////////////////////////////////////////////
        private fun input2File(input: String, filePath: String) {
            val submit =
                Executors.newSingleThreadExecutor()
                    .submit(Callable {
                        var bw: BufferedWriter? = null
                        try {
                            bw = BufferedWriter(FileWriter(filePath, true))
                            bw.write(input)
                            return@Callable true
                        } catch (e: IOException) {
                            e.printStackTrace()
                            return@Callable false
                        } finally {
                            try {
                                bw?.close()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    })
            try {
                if (submit.get()) return
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }
            Log.e("CrashUtils", "write crash info to $filePath failed!")
        }

        private fun createOrExistsFile(filePath: String): Boolean {
            val file = File(filePath)
            if (file.exists()) return file.isFile
            return if (!createOrExistsDir(file.parentFile)) false else try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }

        private fun createOrExistsDir(file: File?): Boolean {
            return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
        }

        private fun isSpace(s: String?): Boolean {
            if (s == null) return true
            var i = 0
            val len = s.length
            while (i < len) {
                if (!Character.isWhitespace(s[i])) {
                    return false
                }
                ++i
            }
            return true
        }

        init {
            try {
                val pi = Utils.getApp().packageManager.getPackageInfo(Utils.getApp().packageName, 0)
                if (pi != null) {
                    versionName = pi.versionName
                    versionCode = pi.versionCode
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            DEFAULT_UNCAUGHT_EXCEPTION_HANDLER =
                Thread.getDefaultUncaughtExceptionHandler()
            UNCAUGHT_EXCEPTION_HANDLER = Thread.UncaughtExceptionHandler { t, e ->
                if (e == null) {
                    if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER?.uncaughtException(t, null)
                    } else {
                        Process.killProcess(Process.myPid())
                        exitProcess(1)
                    }
                    return@UncaughtExceptionHandler
                }
                val time =
                    FORMAT.format(Date(System.currentTimeMillis()))
                val sb = StringBuilder()
                val head = "************* Log Head ****************" +
                        "\nTime Of Crash      : " + time +
                        "\nDevice Manufacturer: " + Build.MANUFACTURER +
                        "\nDevice Model       : " + Build.MODEL +
                        "\nAndroid Version    : " + Build.VERSION.RELEASE +
                        "\nAndroid SDK        : " + Build.VERSION.SDK_INT +
                        "\nApp VersionName    : " + versionName +
                        "\nApp VersionCode    : " + versionCode +
                        "\n************* Log Head ****************\n\n"
                sb.append(head)
                    .append(ThrowableUtils.getFullStackTrace(e))
                val crashInfo = sb.toString()
                val fullPath =
                    (if (dir == null) defaultDir else dir) + time + ".txt"
                if (createOrExistsFile(fullPath)) {
                    input2File(crashInfo, fullPath)
                } else {
                    Log.e("CrashUtils", "create $fullPath failed!")
                }
                if (sOnCrashListener != null) {
                    sOnCrashListener!!.onCrash(crashInfo, e)
                }
                if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER?.uncaughtException(t, e)
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
// interface
///////////////////////////////////////////////////////////////////////////
    interface OnCrashListener {
        fun onCrash(crashInfo: String?, e: Throwable?)
    }

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
}