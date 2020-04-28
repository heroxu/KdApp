package com.xxr.kdapp.manager

import androidx.annotation.NonNull
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.xxr.kdapp.utils.Utils

class KDLocationManager private constructor() {

    //声明AMapLocationClient类对象
    private var mLocationClient: AMapLocationClient = AMapLocationClient(Utils.getApp())
    //声明AMapLocationClientOption对象
    private var mLocationOption: AMapLocationClientOption? = AMapLocationClientOption()

    companion object {
        val instance: KDLocationManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            KDLocationManager()
        }
    }

    fun startGetLocation(listener: AMapLocationListener) {
        initDefaultLocationOptions()
        mLocationClient.setLocationOption(mLocationOption)
        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        mLocationClient.setLocationListener(listener)
        mLocationClient.stopLocation()
        mLocationClient.startLocation()
    }


    fun setLocationOption(@NonNull option: AMapLocationClientOption) {
        mLocationOption = option
    }

    fun setLocationClient(@NonNull client: AMapLocationClient) {
        mLocationClient = client
    }

    private fun initDefaultLocationOptions() {
        mLocationOption?.isNeedAddress = true
        mLocationOption?.isOnceLocationLatest = true
        mLocationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Battery_Saving

    }

}