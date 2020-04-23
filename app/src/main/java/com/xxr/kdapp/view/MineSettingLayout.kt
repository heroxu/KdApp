package com.xxr.kdapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.xxr.kdapp.R
import kotlinx.android.synthetic.main.layout_mine_setting.view.*

class MineSettingLayout : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MineSettingLayout)
        val icon = typedArray.getResourceId(R.styleable.MineSettingLayout_mineIcon, R.drawable.ic_mine_change_user)
        val text = typedArray.getText(R.styleable.MineSettingLayout_mineText)
        val textSize = typedArray.getFloat(R.styleable.MineSettingLayout_mineTextSize, 16f)
        val textColor =
            typedArray.getColor(R.styleable.MineSettingLayout_mineTextColor, 0xff333333.toInt())
        val arrowIcon = typedArray.getResourceId(R.styleable.MineSettingLayout_mineArrowIcon, R.drawable.ic_next)
        typedArray.recycle()

        aiv_mine_content_icon.setImageResource(icon)
        atv_mine_content.text = text
        atv_mine_content.textSize = textSize
        atv_mine_content.setTextColor(textColor)
        aiv_mine_arrow.setImageResource(arrowIcon)

    }


    init {
        LayoutInflater.from(context).inflate(R.layout.layout_mine_setting, this)
    }

    fun setContentIcon(resourceId: Int): MineSettingLayout {
        aiv_mine_content_icon.visibility = View.VISIBLE
        aiv_mine_content_icon.setImageResource(resourceId)
        return this
    }


    fun setContentText(resourceId: Int): MineSettingLayout {
        atv_mine_content.visibility = View.VISIBLE
        atv_mine_content.text = resources.getText(resourceId)
        return this
    }

    fun setClickListener(listener: OnClickListener): MineSettingLayout {
        setOnClickListener(listener)
        return this
    }
}