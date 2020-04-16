package com.xxr.kdapp.ui.fragment

import android.os.Bundle
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author: xuxiarong
 * Date: 2020/4/16 14:59
 * Description: 主页Fragment
 */
class HomeFragment : BaseFragment(){

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        tv_home.text = getString(R.string.app_home)
    }
}