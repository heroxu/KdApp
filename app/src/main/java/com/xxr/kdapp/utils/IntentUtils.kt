package com.xxr.kdapp.utils

import android.content.Context
import android.content.Intent
import com.xxr.kdapp.ui.activity.TakingPicturesActivity


/**
 * Author: xuxiarong
 * Date: 2020/4/22 10:13
 * Description: 应用全局Activity跳转的工具类
 */
class IntentUtils {

    companion object{
        fun startTakingPicture (context: Context?){
            startActivity(context)
        }

        private fun startActivity(context: Context?){
            context?.startActivity(Intent(context,TakingPicturesActivity::class.java))
        }
    }

}