package com.xxr.kdapp.ui.fragment.security

import android.os.Bundle
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseFragment
import com.xxr.kdapp.mvp.model.HomeBannerModel
import com.xxr.kdapp.ui.adpter.HomeBannerAdapter
import com.xxr.kdapp.utils.LogUtils
import com.xxr.kdapp.utils.StatusBarUtil
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.fragment_security_home.*


/**
 * Author: xuxiarong
 * Date: 2020/4/16 14:59
 * Description: 主页Fragment
 */
class SecurityHomeFragment : BaseFragment(){

    private var mTitle: String? = null
    private var mBannerList = arrayListOf(
        HomeBannerModel(R.drawable.ic_home_banner_1,""),
        HomeBannerModel(R.drawable.ic_home_banner_2,""),
        HomeBannerModel(R.drawable.ic_home_banner_3,""))

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


        banner_security_home.adapter = (HomeBannerAdapter(mBannerList))
        banner_security_home.setIndicator(CircleIndicator(activity))
        banner_security_home.setOnBannerListener(object : OnBannerListener<HomeBannerModel> {
            override fun OnBannerClick(data: HomeBannerModel, position: Int) {
                LogUtils.d("position = $position")
            }
        })
        banner_security_home.addOnPageChangeListener(object : OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }

        })
        banner_security_home.start()
    }
}