package com.xxr.kdapp.image

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import java.io.File

/**
 * @author：luck
 * @date：2020-03-23 19:08
 * @describe：获取图片缓存
 */
object ImageCacheUtils {
    /**
     * 根据url获取图片缓存
     * Glide 4.x请调用此方法
     * 注意：此方法必须在子线程中进行
     *
     * @param context
     * @param url
     * @return
     */
    @JvmStatic
    fun getCacheFileTo4x(context: Context?, url: String?): File? {
        return try {
            Glide.with(context!!).downloadOnly().load(url).submit().get()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 根据url获取图片缓存
     * Glide 3.x请调用此方法
     * 注意：此方法必须在子线程中进行
     *
     * @param context
     * @param url
     * @return
     */
    @JvmStatic
    fun getCacheFileTo3x(context: Context?, url: String?): File? {
        return try {
            Glide.with(context!!).load(url).downloadOnly(
                Target.SIZE_ORIGINAL,
                Target.SIZE_ORIGINAL
            ).get()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}