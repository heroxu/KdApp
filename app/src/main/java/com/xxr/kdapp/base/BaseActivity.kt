package com.xxr.kdapp.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.xxr.kdapp.KDApplication
import com.xxr.kdapp.R
import kotlinx.android.synthetic.main.layout_base_title_bar.view.*


/**
 * Author: xuxiarong
 * Date: 2020/4/16 14:20
 * Description: 全局Activity基类
 */
abstract class BaseActivity : AppCompatActivity(),BaseTitleBar.IBaseOnClickListener{

    private var mBaseTitleBar : BaseTitleBar? = null

    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        initData()
    }

    fun initTitleBar(titleBar : BaseTitleBar){
        mBaseTitleBar = titleBar
        mBaseTitleBar?.setBackClickListener(this)
    }

    fun setTitle(title : String){
        mBaseTitleBar?.setTitleText(title)
    }

    override fun onBackClick() {
        finish()
    }


    /**
     * 打卡软键盘
     */
    fun openKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    fun Context.showToast(content: String): Toast {
        val toast = Toast.makeText(KDApplication.instance, content, Toast.LENGTH_SHORT)
        toast.show()
        return toast
    }
}