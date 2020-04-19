package com.xxr.kdapp.ui.activity

import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register_and_login.*

class RegisterAndLoginActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_register_and_login
    }

    override fun initData() {
        setTitle("登录注册")
    }

    override fun initView() {
        initTitleBar(btb_login_title)
    }


}
