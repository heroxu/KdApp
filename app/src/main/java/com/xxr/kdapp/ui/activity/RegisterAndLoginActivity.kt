package com.xxr.kdapp.ui.activity

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseActivity
import com.xxr.kdapp.constant.Constant
import com.xxr.kdapp.listener.LoginTextWatcher
import com.xxr.kdapp.utils.LogUtils
import com.xxr.kdapp.utils.RegexUtils
import com.xxr.kdapp.utils.SPUtils
import com.xxr.kdapp.utils.UserUtils
import kotlinx.android.synthetic.main.activity_register_and_login.*

class RegisterAndLoginActivity : BaseActivity() {

    private var mLoginType: Int = Constant.LOGIN_PHONE_MESSAGE_CODE
    private var mMessageCodeTickCount = 0

    override fun layoutId(): Int {
        return R.layout.activity_register_and_login
    }

    override fun initData() {

    }

    @SuppressLint("StringFormatMatches")
    override fun initView() {
        initTitleBar(btb_login_title)
        mBaseTitleBar.setTitleText(R.string.title_login)

        arrayListOf(et_account, et_password, et_mobile, et_message_code).forEach {
            it.addTextChangedListener(object : LoginTextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    LogUtils.d(it.text)
                    changeTipImageStatus(it)
                    checkLoginStatus()

                }
            })
        }

        tv_fast_login.setOnClickListener {
            tv_fast_login.setTextColor(resources.getColor(R.color.color_login_enable))
            tv_password_login.setTextColor(resources.getColor(R.color.color_code_disable))
            tv_password_login.textSize = resources.getDimension(R.dimen.txt_size_7)
            tv_fast_login.textSize = resources.getDimension(R.dimen.txt_size_8)
            changeLoginUi(Constant.LOGIN_ACCOUNT_PASSWORD)
        }

        tv_password_login.setOnClickListener {
            tv_password_login.setTextColor(resources.getColor(R.color.color_login_enable))
            tv_fast_login.setTextColor(resources.getColor(R.color.color_code_disable))
            tv_password_login.textSize = resources.getDimension(R.dimen.txt_size_8)
            tv_fast_login.textSize = resources.getDimension(R.dimen.txt_size_7)
            changeLoginUi(Constant.LOGIN_PHONE_MESSAGE_CODE)
        }



        btn_login.setOnClickListener {
            SPUtils.instance?.put(Constant.USER_TYPE, Constant.VISITORS_USER)
            UserUtils.navToMain(this)
        }

        aiv_clean_account.setOnClickListener {
            et_account.text.clear()
        }

        aiv_password_visible.setOnClickListener {
            if (et_password.inputType == Constant.PASSWORD_VISIBLE) {
                et_password.inputType = Constant.PASSWORD_INVISIBLE
                aiv_password_visible.setImageResource(R.drawable.ic_password_invisibility)
            } else {
                et_password.inputType = Constant.PASSWORD_VISIBLE
                aiv_password_visible.setImageResource(R.drawable.ic_password_visibility)
            }

        }

        aiv_clean_mobile.setOnClickListener {
            et_mobile.text.clear()
        }

        atv_send_message.setOnClickListener {
            if (mMessageCodeTickCount == 0) {
                mCountDownTimer.start()
                atv_send_message.setText(R.string.send_message_code)
                atv_send_message.setTextColor(resources.getColor(R.color.color_code_enable))
            }
        }
    }

    private var mCountDownTimer: CountDownTimer = object : CountDownTimer(120 * 1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            mMessageCodeTickCount++
            atv_send_message.text = String.format(resources.getString(R.string.retry_send,120-mMessageCodeTickCount))
            atv_send_message.setTextColor(resources.getColor(R.color.color_code_disable))
        }
        override fun onFinish() {
            mMessageCodeTickCount = 0
            atv_send_message.setText(R.string.send_message_code)
            atv_send_message.setTextColor(resources.getColor(R.color.color_code_enable))
        }
    }

    private fun changeTipImageStatus(editText: EditText) {
        when (editText.id) {
            R.id.et_account -> {
                aiv_clean_account.visibility = if (editText.text.isEmpty()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
            R.id.et_password -> {
                aiv_password_visible.visibility = if (editText.text.isEmpty()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
            R.id.et_mobile -> {
                aiv_clean_mobile.visibility = if (editText.text.isEmpty()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
            R.id.et_message_code -> {
            }
            else -> {
                LogUtils.d(editText.text)
            }
        }
    }


    private fun changeLoginUi(loginType: Int) {
        if (loginType == Constant.LOGIN_PHONE_MESSAGE_CODE) {
            mLoginType = Constant.LOGIN_ACCOUNT_PASSWORD
            ll_login_with_account.visibility = View.VISIBLE
            ll_login_with_message_code.visibility = View.GONE
        } else if (loginType == Constant.LOGIN_ACCOUNT_PASSWORD) {
            mLoginType = Constant.LOGIN_PHONE_MESSAGE_CODE
            ll_login_with_message_code.visibility = View.VISIBLE
            ll_login_with_account.visibility = View.GONE
        }
        checkLoginStatus()
    }

    fun checkLoginStatus() {

        var loginEnable = false
        if (mLoginType == Constant.LOGIN_PHONE_MESSAGE_CODE) {
            loginEnable =
                RegexUtils.isMobileSimple(et_mobile.text) && et_message_code.length() == Constant.MESSAGE_CODE_LENGTH
        } else if (mLoginType == Constant.LOGIN_ACCOUNT_PASSWORD) {
            loginEnable = et_account.text.isNotEmpty() && et_password.text.isNotEmpty()
        }

        if (loginEnable) {
            btn_login.setBackgroundColor(resources.getColor(R.color.color_login_enable))
        } /*else {
            btn_login.setBackgroundColor(resources.getColor(R.color.color_login_disable))
        }*/
    }

}
