package com.xxr.kdapp

import android.app.Application
import android.content.Context
import android.util.Log
import kotlin.properties.Delegates


/**
 * Copyright (C), 2019-2020, 优必选科技有限公司
 * Author: xuxiarong
 * Date: 2020/4/16 15:07
 * Description: 全局Application
 */
class KDApplication : Application(){


    companion object {

        private const val TAG = "MyApplication"
        var context: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG,"KDApplication 创建 时间点 ${System.currentTimeMillis()}")
        context = applicationContext
    }

}