package com.xxr.kdapp.view

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText


/**
 * Copyright (C), 2019-2020, 优必选科技有限公司
 * Author: xuxiarong
 * Date: 2020/4/21 20:28
 * Description: 光标始终在最后的EditText
 */
class LastInputEditTex : EditText {

    constructor(mContext: Context) : super(mContext) {
        val context = mContext
    }

    constructor(mContext: Context, mAttributeSet: AttributeSet) : super(mContext, mAttributeSet) {
        val context = mContext
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        //防止不能多选
        if (selStart == selEnd) {
            //判空，防止出现空指针
            if (text.isEmpty()) {
                setSelection(0)
            } else {
                setSelection(text.length)
            }
        }
    }
}