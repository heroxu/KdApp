package com.xxr.kdapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.xxr.kdapp.R
import com.xxr.kdapp.utils.LogUtils
import com.xxr.kdapp.utils.Utils
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity(), AMapLocationListener {
    //声明AMapLocationClient类对象
    var mLocationClient: AMapLocationClient? = null
    //声明定位回调监听器
    var mLocationListener: AMapLocationListener? = null
    //声明AMapLocationClientOption对象
    var mLocationOption: AMapLocationClientOption? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        //初始化定位
        mLocationClient = AMapLocationClient(Utils.getApp())
        mLocationOption = AMapLocationClientOption()
        mLocationListener = this
        //设置定位回调监听
        mLocationClient?.setLocationListener(mLocationListener)
        mLocationOption?.isNeedAddress = true
        mLocationOption?.isOnceLocationLatest = true
        mLocationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Battery_Saving
        tv_start_location.setOnClickListener {
            if (null != mLocationClient) {
                mLocationClient?.setLocationOption(mLocationOption)
                //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
                mLocationClient?.stopLocation()
                mLocationClient?.startLocation()
            }
        }
    }

    override fun onLocationChanged(aMapLocation: AMapLocation) {
        LogUtils.d("aMapLocation = $aMapLocation" )
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {
        LogUtils.d("hasCapture = $hasCapture" )

    }
}