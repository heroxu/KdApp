package com.xxr.kdapp.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.xxr.kdapp.R
import kotlinx.android.synthetic.main.layout_base_title_bar.view.*

class BaseTitleBar : LinearLayout {

    constructor(mContext: Context) : super(mContext) {
        val context = mContext
    }

    constructor(mContext: Context, mAttributeSet: AttributeSet) : super(mContext, mAttributeSet) {
        val context = mContext
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_base_title_bar, this)
    }

    fun setLeftIcon(resourceId: Int) : BaseTitleBar{
        aiv_base_left_icon.visibility = View.VISIBLE
        aiv_base_right_icon.setImageResource(resourceId)
        return this
    }

    fun setLeftIconVisible(visible: Int) : BaseTitleBar{
        aiv_base_left_icon.visibility = visible
        return this
    }

    fun setLeftIconClickListener(listener: OnClickListener): BaseTitleBar{
        aiv_base_left_icon.setOnClickListener(listener)
        return this
    }


    fun setLeftText(resourceId: Int) : BaseTitleBar{
        atv_base_left_text.visibility = View.VISIBLE
        atv_base_left_text.text = resources.getText(resourceId)
        return this
    }

    fun setLeftTextClickListener(listener: OnClickListener) : BaseTitleBar{
        atv_base_left_text.setOnClickListener(listener)
        return this
    }


    fun setTitleText(resourceId: Int) : BaseTitleBar{
        atv_base_title.visibility = View.VISIBLE
        atv_base_title.text = resources.getText(resourceId)
        return this
    }


    fun setRightIconClickListener(listener: OnClickListener) : BaseTitleBar{
        aiv_base_left_icon.setOnClickListener(listener)
        return this
    }

    fun setRightIcon(resourceId: Int) : BaseTitleBar{
        aiv_base_right_icon.visibility = View.VISIBLE
        aiv_base_right_icon.setImageResource(resourceId)
        return this
    }

    fun setRightText(resourceId: Int) : BaseTitleBar{
        atv_base_right_text.visibility = View.VISIBLE
        atv_base_right_text.text = resources.getText(resourceId)
        return this
    }

    fun setRightTextClickListener(listener: OnClickListener) : BaseTitleBar{
        atv_base_right_text.setOnClickListener(listener)
        return this
    }

}