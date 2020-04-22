package com.xxr.kdapp

import android.app.Application
import android.content.Context
import android.util.Log
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.model.HttpParams
import com.xxr.kdapp.constant.Constant
import com.xxr.kdapp.utils.AppUtils
import com.xxr.kdapp.utils.CrashUtils
import com.xxr.kdapp.utils.LogUtils
import com.xxr.kdapp.utils.SPUtils
import okhttp3.OkHttpClient
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import kotlin.properties.Delegates


/**
 * Author: xuxiarong
 * Date: 2020/4/16 15:07
 * Description: 全局Application
 */
class KDApplication : Application() {


    companion object {
        private const val TAG = "MyApplication"
        var instance: Context by Delegates.notNull()
            private set
        var userType: Int = Constant.VISITORS_USER
        private var isDebug: Boolean? = null
        private val isMainProcess: Boolean? = null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "KDApplication 创建 时间点 ${System.currentTimeMillis()}")
        instance = this
        initApp()
        initNetWork()
    }

    private fun initNetWork() {
        val okHttpBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO)
        okHttpBuilder.addInterceptor(loggingInterceptor)

        //全局的读取超时时间
        okHttpBuilder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        //全局的写入超时时间
        okHttpBuilder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        //全局的连接超时时间
        okHttpBuilder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)

        val headers = HttpHeaders()
//        headers.put("commonHeaderKey1", "commonHeaderValue1") //header不支持中文，不允许有特殊字符
//
//        headers.put("commonHeaderKey2", "commonHeaderValue2")
        val params = HttpParams()
//        params.put("commonParamsKey1", "commonParamsValue1") //param支持中文,直接传,不要自己编码
//
//        params.put("commonParamsKey2", "这里支持中文参数")


        OkGo.getInstance().init(this)                       //必须调用初始化
            .setOkHttpClient(okHttpBuilder.build())               //建议设置OkHttpClient，不设置将使用默认的
            .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
            .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
            .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
            .addCommonHeaders(headers)                      //全局公共头
            .addCommonParams(params)                       //全局公共参数
    }

    private fun initApp() {

        userType = SPUtils.instance?.getInt(Constant.USER_TYPE, Constant.LOGIN_OTHER)!!
        //初始化Crash
        initCrash()

        val config: LogUtils.Config = LogUtils.getConfig()
            .setLogSwitch(isDebug()) // 设置 log 总开关，包括输出到控制台和文件，默认开
            .setConsoleSwitch(isDebug()) // 设置是否输出到控制台开关，默认开
            .setGlobalTag(null) // 设置 log 全局标签，默认为空
            // 当全局标签不为空时，我们输出的 log 全部为该 tag，
            // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
            .setLogHeadSwitch(true) // 设置 log 头信息开关，默认为开
            .setLog2FileSwitch(false) // 打印 log 时是否存到文件的开关，默认关
            .setDir("") // 当自定义路径为空时，写入应用的/cache/log/目录中
            .setFilePrefix("") // 当文件前缀为空时，默认为"util"，即写入文件为"util-yyyy-MM-dd.txt"
            .setBorderSwitch(true) // 输出日志是否带边框开关，默认开
            .setSingleTagSwitch(true) // 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
            .setConsoleFilter(LogUtils.V) // log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
            .setFileFilter(LogUtils.V) // log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
            .setStackDeep(1) // log 栈深度，默认为 1
            .setStackOffset(0) // 设置栈偏移，比如二次封装的话就需要设置，默认为 0
            .setSaveDays(3) // 设置日志可保留天数，默认为 -1 表示无限时长
            // 新增 ArrayList 格式化器，默认已支持 Array, Throwable, Bundle, Intent 的格式化输出
            .addFormatter(object : LogUtils.IFormatter<ArrayList<*>?>() {
                override fun format(arrayList: ArrayList<*>?): String {
                    return "LogUtils Formatter ArrayList { $arrayList }"
                }
            })
            .setFileWriter(null)
        LogUtils.i(config.toString())
    }

    private fun isDebug(): Boolean {
        if (isDebug == null) isDebug = AppUtils.isAppDebug()
        return isDebug!!
    }

    private fun initCrash() {
        CrashUtils.init(object : CrashUtils.OnCrashListener {
            override fun onCrash(crashInfo: String?, e: Throwable?) {
                LogUtils.e(crashInfo)
                AppUtils.relaunchApp()
            }
        })
    }
}