package com.xxr.kdapp.ui.fragment

import android.os.Bundle
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*


/**
 * Author: xuxiarong
 * Date: 2020/4/16 15:00
 * Description: 我的Fragment
 */
class MineFragment : BaseFragment(){

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
        tv_mine.text = getString(R.string.app_mine)
    }
}