package com.xxr.kdapp.ui.adpter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.xxr.kdapp.mvp.model.HomeBannerModel
import com.youth.banner.adapter.BannerAdapter

/**
 * Copyright (C), 2019-2020, 优必选科技有限公司
 * Author: xuxiarong
 * Date: 2020/4/24 15:26
 * Description: 轮播图的适配器
 */
class HomeBannerAdapter(mHomeBannerList: List<HomeBannerModel?>?) : BannerAdapter<HomeBannerModel?, HomeBannerAdapter.BannerViewHolder>(mHomeBannerList) {
    override fun onCreateHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerViewHolder {
        val imageView = ImageView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }


    class BannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onBindView(holder: BannerViewHolder?, data: HomeBannerModel?, position: Int, size: Int) {
        if(holder!=null && data!=null){
            holder.imageView.setImageResource(data.imageRes)
        }
    }
}