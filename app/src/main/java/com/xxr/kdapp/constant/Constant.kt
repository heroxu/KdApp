package com.xxr.kdapp.constant

import com.xxr.kdapp.R


/**
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

    const val LOGIN_ACCOUNT_PASSWORD = 0

    const val LOGIN_PHONE_MESSAGE_CODE = 1

    const val LOGIN_OTHER = 2

    const val MESSAGE_CODE_LENGTH = 6


    const val PASSWORD_VISIBLE = 128
    const val PASSWORD_INVISIBLE = 129


//    const val homeTitleMap =  mapOf<Int,Int>(R.string.app_home,R.string.)

}