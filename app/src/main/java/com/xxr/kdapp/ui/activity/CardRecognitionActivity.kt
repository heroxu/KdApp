package com.xxr.kdapp.ui.activity

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.mlplugin.card.icr.cn.MLCnIcrCapture
import com.huawei.hms.mlplugin.card.icr.cn.MLCnIcrCaptureConfig
import com.huawei.hms.mlplugin.card.icr.cn.MLCnIcrCaptureFactory
import com.huawei.hms.mlplugin.card.icr.cn.MLCnIcrCaptureResult
import com.xxr.kdapp.R
import com.xxr.kdapp.utils.LogUtils
import kotlinx.android.synthetic.main.activity_card_recognition.*


class CardRecognitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_recognition)

        zhengmian.setOnClickListener {
            startCaptureActivity(idCallback, true, false)

        }

        fanmian.setOnClickListener {
            startCaptureActivity(idCallback, false, false)

        }
    }

    private val idCallback: MLCnIcrCapture.CallBack = object : MLCnIcrCapture.CallBack {
        override fun onSuccess( result: MLCnIcrCaptureResult?) {
            LogUtils.d(result?.name)
            if(result!=null){
                zhengmian.text = result.name
            }
        }

        override fun onFailure(p0: Int, p1: Bitmap?) {
            LogUtils.d(p0)
        }

        override fun onCanceled() {
            LogUtils.d("onCanceled")
        }

        override fun onDenied() {
            LogUtils.d("onDenied")
        }

    }

    private fun startCaptureActivity(callback: MLCnIcrCapture.CallBack, isFront: Boolean, isRemote: Boolean) {
        val config = MLCnIcrCaptureConfig.Factory() // 设置识别身份证的正反面。
            .setFront(isFront) // 设置是否使用云侧能力进行识别。
            .setRemote(isRemote)
            .create()
        val icrCapture = MLCnIcrCaptureFactory.getInstance().getIcrCapture(config)
        icrCapture.capture(callback, this)
    }
}
