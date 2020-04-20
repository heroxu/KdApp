package com.xxr.kdapp.ui.activity

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseActivity
import com.xxr.kdapp.constant.Constant
import com.xxr.kdapp.utils.SPUtils
import com.xxr.kdapp.utils.UserUtils
import com.xxr.kdapp.utils.Utils
import kotlinx.android.synthetic.main.activity_register_and_login.*
import java.util.regex.Pattern

class RegisterAndLoginActivity : BaseActivity() {

    private var mLoginType: Int = Constant.LOGIN_ACCOUNT
    private var mIsMatchPhone = false;
    private var mIsMatchMessageCode = false;

    override fun layoutId(): Int {
        return R.layout.activity_register_and_login
    }

    override fun initData() {
        setTitle("登录注册")

        et_mobile.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mIsMatchPhone = isMatch(Constant.PHONE_REGEX,s!!)
                checkLogin()
            }

        })

        et_message_code.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mIsMatchMessageCode = s!!.length == 6
                checkLogin()

            }

        })

        btn_login.setOnClickListener {
            if (mIsMatchMessageCode && mIsMatchPhone) {
                SPUtils.instance?.put(Constant.USER_TYPE,Constant.VISITORS_USER)
                UserUtils.navToMain(this)
            }
        }
    }

    override fun initView() {
        initTitleBar(btb_login_title)
    }

    fun checkLogin(){
        if(mIsMatchMessageCode && mIsMatchPhone){
            btn_login.setBackgroundColor(resources.getColor(R.color.color_login_enable))
        }else{
            btn_login.setBackgroundColor(resources.getColor(R.color.color_login_disable))
        }
    }

//    override fun onRightTextClick() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun onRightIconClick() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    /** 判断是否匹配正*

    @paramregex正则表达式

    @paraminput要匹配的字符串

    @return{@code true}: 匹配{@code false}: 不匹配

     */

    fun  isMatch(regex :String ,input : CharSequence ) : Boolean{
        return  input.isNotEmpty() && Pattern.matches(regex,input);

    }



}
