package com.xxr.kdapp.constant

import com.xxr.kdapp.R


/**
 * Copyright (C), 2019-2020, 优必选科技有限公司
 * Author: xuxiarong
 * Date: 2020/4/17 15:56
 * Description: 应用的全局常量类
 */
object Constant {


    /**
     * Sp相关的key
     */
    //用户类型 暂定 1：安保人员 2外来人员
    val USER_TYPE = "user_type"
    //安保用户唯一标识
    val SECURITY_USER_UUID = "security_user_uuid"

    const val UNREGISTER_USER = 0


    const val VISITORS_USER = 1

    const val SECURITY_USER = 2


    /**
     * 登录类型
     */

    const val LOGIN_ACCOUNT = 0


    const val LOGIN_PHONE_MESSAGE_CODE = 1

    const val LOGIN_OTHER = 2


    const val PHONE_REGEX = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,1,3,5-8])|(18[0-9])|(147))\\d{8}$"

    const val EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    const val SHEN_REGEX = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$"


//    const val homeTitleMap =  mapOf<Int,Int>(R.string.app_home,R.string.)

}