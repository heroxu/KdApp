package com.xxr.kdapp.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.xxr.kdapp.R

class BaseTitleBar : LinearLayout {

    constructor(mContext: Context) : super(mContext) {
        val context = mContext
    }
    constructor(mContext: Context, mAttributeSet: AttributeSet) : super(mContext, mAttributeSet) {
        val context = mContext
    }

    init {
        var inflate = LayoutInflater.from(context).inflate(R.layout.layout_base_title_bar, this)
    }



}