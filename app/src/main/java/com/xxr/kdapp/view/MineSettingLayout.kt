package com.xxr.kdapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.xxr.kdapp.R
import kotlinx.android.synthetic.main.layout_base_title_bar.view.*

class MineSettingLayout : LinearLayout {

    constructor(mContext: Context) : super(mContext) {
        val context = mContext
    }

    constructor(mContext: Context, mAttributeSet: AttributeSet) : super(mContext, mAttributeSet) {
        val context = mContext
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_mine_setting, this)
    }

    fun setLeftIcon(resourceId: Int) : MineSettingLayout{
        aiv_base_left_icon.visibility = View.VISIBLE
        aiv_base_right_icon.setImageResource(resourceId)
        return this
    }

    fun setLeftIconClickListener(listener: OnClickListener): MineSettingLayout{
        aiv_base_left_icon.setOnClickListener(listener)
        return this
    }


    fun setLeftText(resourceId: Int) : MineSettingLayout{
        atv_base_left_text.visibility = View.VISIBLE
        atv_base_left_text.text = resources.getText(resourceId)
        return this
    }

    fun setLeftTextClickListener(listener: OnClickListener) : MineSettingLayout{
        atv_base_left_text.setOnClickListener(listener)
        return this
    }


    fun setTitleText(resourceId: Int) : MineSettingLayout{
        atv_base_title.visibility = View.VISIBLE
        atv_base_title.text = resources.getText(resourceId)
        return this
    }


    fun setRightIconClickListener(listener: OnClickListener) : MineSettingLayout{
        aiv_base_left_icon.setOnClickListener(listener)
        return this
    }

    fun setRightIcon(resourceId: Int) : MineSettingLayout{
        aiv_base_right_icon.visibility = View.VISIBLE
        aiv_base_right_icon.setImageResource(resourceId)
        return this
    }

    fun setRightText(resourceId: Int) : MineSettingLayout{
        atv_base_right_text.visibility = View.VISIBLE
        atv_base_right_text.text = resources.getText(resourceId)
        return this
    }

    fun setRightTextClickListener(listener: OnClickListener) : MineSettingLayout{
        atv_base_right_text.setOnClickListener(listener)
        return this
    }

}