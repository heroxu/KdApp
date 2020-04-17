package com.xxr.kdapp.ui.fragment.security

import android.os.Bundle
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_security_home.*

/**
 * Author: xuxiarong
 * Date: 2020/4/16 14:59
 * Description: 主页Fragment
 */
class SecurityHomeFragment : BaseFragment(){

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): SecurityHomeFragment {
            val fragment = SecurityHomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_security_home

    override fun initView() {
        tv_security_home.text = "安保首页"
    }
}