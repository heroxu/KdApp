package com.xxr.kdapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseActivity
import com.xxr.kdapp.mvp.model.TabEntity
import com.xxr.kdapp.ui.fragment.visitors.VisitorsHomeFragment
import com.xxr.kdapp.ui.fragment.visitors.VisitorsMineFragment
import kotlinx.android.synthetic.main.activity_visitors_main.*
import java.util.ArrayList

/**
 * Author: xuxiarong
 * Date: 2020/4/13 11:20
 * Description: 全局Activity基类
 */
class VisitorsMainActivity : BaseActivity() {


    private val mTitles = arrayOf("首页", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.drawable.ic_home_normal, R.drawable.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.drawable.ic_home_selected, R.drawable.ic_mine_selected)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mVisitorsHomeFragment: VisitorsHomeFragment? = null
    private var mVisitorsMineFragment: VisitorsMineFragment? = null

    //默认为0
    private var mIndex = 0


    override fun layoutId(): Int {
        return R.layout.activity_visitors_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout_visitors.currentTab = mIndex
        switchFragment(mIndex)

    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mVisitorsHomeFragment?.let {
                transaction.show(it)
            } ?: VisitorsHomeFragment.getInstance(mTitles[position]).let {
                mVisitorsHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1  //发现
            -> mVisitorsMineFragment?.let {
                transaction.show(it)
            } ?: VisitorsMineFragment.getInstance(mTitles[position]).let {
                mVisitorsMineFragment = it
                transaction.add(R.id.fl_container, it, "discovery") }
            else -> {

            }
        }

        mIndex = position
        tab_layout_visitors.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
            .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout_visitors.setTabData(mTabEntities)
        tab_layout_visitors.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mVisitorsHomeFragment?.let { transaction.hide(it) }

        mVisitorsMineFragment?.let { transaction.hide(it) }
    }


    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
//        showToast("onSaveInstanceState->"+mIndex)
//        super.onSaveInstanceState(outState)
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout_visitors != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun initView() {

    }

    override fun initData() {

    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
