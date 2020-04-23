package com.xxr.kdapp.ui.fragment.visitors

import android.os.Bundle
import android.view.View
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseFragment
import com.xxr.kdapp.constant.Constant
import com.xxr.kdapp.utils.IntentUtils
import com.xxr.kdapp.utils.SPUtils
import com.xxr.kdapp.utils.UserUtils
import kotlinx.android.synthetic.main.fragment_visitors_mine.*


/**
 * Author: xuxiarong
 * Date: 2020/4/16 15:00
 * Description: 我的Fragment
 */
class VisitorsMineFragment : BaseFragment() {

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): VisitorsMineFragment {
            val fragment = VisitorsMineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_visitors_mine

    override fun initView() {

        msl_visitors_change_user_type.setOnClickListener {
            val currentUserType = when {
                SPUtils.instance?.getInt(Constant.USER_TYPE, 1) == Constant.VISITORS_USER -> {
                     Constant.SECURITY_USER
                }
                SPUtils.instance?.getInt(Constant.USER_TYPE, 1) == Constant.SECURITY_USER -> {
                    Constant.VISITORS_USER
                }
                else -> {
                    Constant.UNREGISTER_USER
                }
            }
            SPUtils.instance?.put(Constant.USER_TYPE, currentUserType)
            UserUtils.navToMain(activity)
        }

        msl_visitors_enter_record.setOnClickListener {
            IntentUtils.startEnterRecord(activity)
        }
    }
}