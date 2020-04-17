package com.xxr.kdapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.xxr.kdapp.constant.Constant
import com.xxr.kdapp.ui.activity.SecurityMainActivity
import com.xxr.kdapp.ui.activity.VisitorsMainActivity


/**
 * Copyright (C), 2019-2020, 优必选科技有限公司
 * Author: xuxiarong
 * Date: 2020/4/17 16:20
 * Description: 用户相关的工具类
 */
class UserUtils {
    companion object {

        fun isSecurity(): Boolean {
            return SPUtils.instance?.getInt(Constant.USER_TYPE, 1) == Constant.SECURITY_USER
        }

        fun isVisitors(): Boolean {
            return SPUtils.instance?.getInt(Constant.USER_TYPE, 1) == Constant.VISITORS_USER
        }


        fun navToMain(context: Context?) {

            if (context == null) {
                return
            }

            val intent = when {
                isSecurity() -> {
                    Intent(context, SecurityMainActivity::class.java)
                }
                isVisitors() -> {
                    Intent(context, VisitorsMainActivity::class.java)
                }
                else -> {
                    Intent(context, VisitorsMainActivity::class.java)
                }
            }
            context.startActivity(intent)
            (context as Activity).finish()

        }
    }
}