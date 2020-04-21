package com.xxr.kdapp.listener

import android.text.Editable
import android.text.TextWatcher


/**
 * Copyright (C), 2019-2020, 优必选科技有限公司
 * Author: xuxiarong
 * Date: 2020/4/21 19:06
 * Description: 登陆页面的EditText输入文本的自定义监听
 */
interface LoginTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

}