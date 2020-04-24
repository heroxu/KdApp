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
    const val USER_TYPE = "user_type"
    const val SECURITY_USER_UUID = "security_user_uuid"


    /**
     * 用户类型 暂定 1：安保人员 2外来人员
     */
    const val UNREGISTER_USER = 0
    const val VISITORS_USER = 1
    const val SECURITY_USER = 2


    /**
     * 登录方式 0：账户密码 1：短信验证码登陆 2: 其他方式，例如第三方平台
     */
    const val LOGIN_ACCOUNT_PASSWORD = 0
    const val LOGIN_PHONE_MESSAGE_CODE = 1
    const val LOGIN_OTHER = 2

    /**
     * 短信验证码长度
     */
    const val MESSAGE_CODE_LENGTH = 6


    /**
     * 键盘密码可见不可见
     */
    const val PASSWORD_VISIBLE = 128
    const val PASSWORD_INVISIBLE = 129

    /**
     * 入园记录状态码 0：已通过 1：被拒绝 2：取消
     */
    const val ENTER_ALLOW = 0
    const val ENTER_REFUSED = 1
    const val ENTER_CANCLE = 2
}