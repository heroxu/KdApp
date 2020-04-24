package com.xxr.kdapp.ui.adpter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.xxr.kdapp.R
import com.xxr.kdapp.constant.Constant
import com.xxr.kdapp.image.GlideEngine
import com.xxr.kdapp.mvp.model.EnterRecordModel
import java.text.SimpleDateFormat

class RecordAdapter(var mContext: Context, var mDataList: List<EnterRecordModel>) :
    RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.item_enter_record,
                null
            )
        )
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val enterRecord = mDataList[position]
        GlideEngine.createGlideEngine().loadImage(mContext, enterRecord.imgUrl, holder.mAtvEnterPicture)
        holder.mAtvCommunityName.text =
            String.format(mContext.resources.getString(R.string.record_community_name), enterRecord.name)
        holder.mAtvEnterTime.text = String.format(
            mContext.resources.getString(R.string.record_enter_time),
            SimpleDateFormat("yyyy年mm月dd日 hh:mm:ss").format(enterRecord.enterTime)
        )
        if (enterRecord.enterStatus == Constant.ENTER_ALLOW) {
            holder.mAtvEnterStatus.text = mContext.resources.getString(R.string.record_enter_allow)
            holder.mAtvEnterStatus.setTextColor(mContext.resources.getColor(R.color.color_enter_allow))
        } else if (enterRecord.enterStatus == Constant.ENTER_REFUSED) {
            holder.mAtvEnterStatus.text = mContext.resources.getString(R.string.record_enter_refused)
            holder.mAtvEnterStatus.setTextColor(mContext.resources.getColor(R.color.color_enter_refused))
        } else if (enterRecord.enterStatus == Constant.ENTER_CANCLE) {
            holder.mAtvEnterStatus.text = mContext.resources.getString(R.string.record_enter_cancel)
            holder.mAtvEnterStatus.setTextColor(mContext.resources.getColor(R.color.color_enter_cancel))
        } else {
            holder.mAtvEnterStatus.text = mContext.resources.getString(R.string.record_enter_cancel)
            holder.mAtvEnterStatus.setTextColor(mContext.resources.getColor(R.color.color_enter_cancel))
        }

    }

    class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mAtvEnterPicture: AppCompatImageView = itemView.findViewById(R.id.aiv_item_enter_picture)
        var mAtvCommunityName: AppCompatTextView = itemView.findViewById(R.id.atv_item_community_name)
        var mAtvEnterTime: AppCompatTextView = itemView.findViewById(R.id.atv_item_enter_time)
        var mAtvEnterStatus: AppCompatTextView = itemView.findViewById(R.id.atv_enter_status)

    }
}

