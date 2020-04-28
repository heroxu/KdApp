package com.xxr.kdapp.ui.activity

import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.xxr.kdapp.R
import com.xxr.kdapp.manager.KDLocationManager
import com.xxr.kdapp.utils.LogUtils
import com.xxr.kdapp.utils.Utils
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity(), AMapLocationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        tv_start_location.setOnClickListener {
            KDLocationManager.instance.startGetLocation(this)
        }
    }

    override fun onLocationChanged(aMapLocation: AMapLocation) {
        LogUtils.d("aMapLocation = $aMapLocation" )
        tv_start_location.text = "定位地址:\n ${aMapLocation.address}"
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {
        LogUtils.d("hasCapture = $hasCapture" )

    }
}