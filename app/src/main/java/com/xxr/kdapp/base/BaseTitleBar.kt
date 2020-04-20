package com.xxr.kdapp.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.xxr.kdapp.R
import kotlinx.android.synthetic.main.layout_base_title_bar.view.*

class BaseTitleBar : LinearLayout {

    private var mIBaseOnClickListener : IBaseOnClickListener? =null

    constructor(mContext: Context) : super(mContext) {
        val context = mContext
    }

    constructor(mContext: Context, mAttributeSet: AttributeSet) : super(mContext, mAttributeSet) {
        val context = mContext
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_base_title_bar, this)
        aiv_base_left_icon.setOnClickListener{
            mIBaseOnClickListener?.onBackClick()
        }
    }

    fun setTitleText(text: String) {
        atv_base_title.text = text
    }

    fun setBackClickListener(listener:IBaseOnClickListener){
        mIBaseOnClickListener = listener
    }

    fun setRightText(rightText : String){
        atv_base_right_text.visibility  = View.VISIBLE
        aiv_base_right_icon.visibility  = View.GONE
        atv_base_right_text.text = rightText
    }

    fun setRightIcon(resourceId : Int){
        atv_base_right_text.visibility  = View.GONE
        aiv_base_right_icon.visibility  = View.VISIBLE
        aiv_base_right_icon.setImageResource(resourceId)
    }

    fun setRightTextAndIcon(rightText : String,resourceId : Int){
        atv_base_right_text.visibility  = View.VISIBLE
        aiv_base_right_icon.visibility  = View.VISIBLE
        aiv_base_right_icon.setImageResource(resourceId)
        atv_base_right_text.text = rightText
    }

    interface IBaseOnClickListener{
        fun onBackClick()
//        fun onRightTextClick()
//        fun onRightIconClick()
    }

}