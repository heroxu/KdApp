package com.xxr.kdapp

import android.app.Application
import android.content.Context
import android.util.Log
import com.xxr.kdapp.constant.Constant
import com.xxr.kdapp.utils.SPUtils
import com.xxr.kdapp.utils.Utils
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
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "KDApplication 创建 时间点 ${System.currentTimeMillis()}")
        instance = this
        initApp()
    }

    private fun initApp() {
        Utils.init(this)
        userType = SPUtils.instance?.getInt(Constant.USER_TYPE,2)!!
    }

}