package com.xxr.kdapp.ui.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.xxr.kdapp.R
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * Author: xuxiarong
 * Date: 2020/4/13 12:10
 * Description: 引导页面
 */
class SplashActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {


    companion object {
        const val TAG = "SplashActivity"
        const val READ_PHONE_STATE_AND_WRITE_EXTERNAL_STORAGE = 10000
        const val REQUEST_SETTING_CODE = 10001
    }

    private var alphaAnimation: AlphaAnimation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //渐变展示启动屏
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })

        requestPermission()
    }

    /**
     * 去申请权限
     */
    private fun requestPermission() {
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            navToMain()
        } else {
            // RC_CAMERA_AND_RECORD_AUDIO 请求码, 用于回调的时候判断是哪次申请 perms 要申请的权限
            EasyPermissions.requestPermissions(
                this,
                "KotlinMvp应用需要以下权限，请允许",
                READ_PHONE_STATE_AND_WRITE_EXTERNAL_STORAGE,
                *perms
            )
        }
    }

    /**
     * 权限申请拒绝的回调
     *
     * @param requestCode 申请权限时的请求码
     * @param perms 申请拒绝的权限集合
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.i(TAG, "onPermissionsDenied: ")

        if (requestCode != READ_PHONE_STATE_AND_WRITE_EXTERNAL_STORAGE) {
            return
        }

        for (i in 0 until perms.size) {
            if (perms[i] == Manifest.permission.READ_PHONE_STATE) {
                Log.i(TAG, "onPermissionsGranted: " + "读取电话权限被拒绝")
            } else if (perms[i] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                Log.i(TAG, "onPermissionsGranted: " + "写入数据权限被拒绝")
            }
        }

        //如果有一些权限被永久的拒绝, 就需要转跳到 设置-->应用-->对应的App下去开启权限
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this)
                .setTitle("权限已经被您拒绝")
                .setRationale("如果不打开权限则无法使用该功能,点击确定去打开权限")
                .setRequestCode(REQUEST_SETTING_CODE) //用于onActivityResult回调做其它对应相关的操作
                .build()
                .show()
        }
    }

    /**
     * 权限申请成功的回调
     *
     * @param requestCode 申请权限时的请求码
     * @param perms 申请成功的权限集合
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.i(TAG, "onPermissionsGranted: ")
        var hasReadPhoneState = false
        var hasWriteExternalStorage = false
        if (READ_PHONE_STATE_AND_WRITE_EXTERNAL_STORAGE != requestCode) {
            return
        }
        for (i in 0 until perms.size) {
            if (perms[i] == Manifest.permission.READ_PHONE_STATE) {
                hasReadPhoneState = true
                Log.i(TAG, "onPermissionsGranted: " + "读取电话权限成功")
            } else if (perms[i] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                hasWriteExternalStorage = true
                Log.i(TAG, "onPermissionsGranted: " + "写入数据权限成功")
            }
        }
        if(hasReadPhoneState&&hasWriteExternalStorage){
            if (alphaAnimation != null) {
                tv_splash.startAnimation(alphaAnimation)
            }
        }else{
            AppSettingsDialog.Builder(this)
                .setTitle("权限已经被您拒绝")
                .setRationale("如果不打开权限则无法使用该功能,点击确定去打开权限")
                .setRequestCode(REQUEST_SETTING_CODE) //用于onActivityResult回调做其它对应相关的操作
                .build()
                .show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SETTING_CODE) {
            requestPermission()
        }
    }


    private fun navToMain() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
