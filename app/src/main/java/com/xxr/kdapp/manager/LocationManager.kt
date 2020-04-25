package com.xxr.kdapp.manager

import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.xxr.kdapp.utils.Utils

class LocationManager {
    //声明AMapLocationClient类对象
     var  mLocationClient : AMapLocationClient?= AMapLocationClient(Utils.getApp());
    //声明定位回调监听器
    lateinit var  mLocationListener : AMapLocationListener

    //设置定位回调监听

}