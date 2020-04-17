package com.xxr.kdapp.ui.fragment.visitors

import android.os.Bundle
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_visitors_home.*

/**
 * Author: xuxiarong
 * Date: 2020/4/16 14:59
 * Description: 主页Fragment
 */
class VisitorsHomeFragment : BaseFragment(){

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): VisitorsHomeFragment {
            val fragment = VisitorsHomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_visitors_home

    override fun initView() {
        tv_visitors_home.text = "访客首页"
    }
}