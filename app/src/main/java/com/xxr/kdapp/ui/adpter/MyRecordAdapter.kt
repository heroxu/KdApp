package com.xxr.kdapp.ui.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.xxr.kdapp.R
import com.xxr.kdapp.mvp.model.EnterRecordModel
import java.text.SimpleDateFormat

class MyRecordAdapter(var mContext: Context, var mDataList: List<EnterRecordModel>) :
    RecyclerView.Adapter<MyRecordAdapter.MyRecordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecordViewHolder {
        return MyRecordViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.item_enter_record,
                null
            )
        )
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: MyRecordViewHolder, position: Int) {
        var enterRecord = mDataList[position]

        holder.mAtvCommunityName.text = String.format(mContext.resources.getString(R.string.record_community_name),enterRecord.name)
        holder.mAtvEnterTime.text = String.format(mContext.resources.getString(R.string.record_community_name),SimpleDateFormat("YY-MM-DD-hh-mm-ss").format(enterRecord.enterTime))
        holder.mAtvEnterStatus.text = String.format(mContext.resources.getString(R.string.record_community_name),enterRecord.enterStatus.toString())
    }

    class MyRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mAtvEnterPicture = itemView.findViewById<AppCompatImageView>(R.id.aiv_item_enter_picture)
        var mAtvCommunityName = itemView.findViewById<AppCompatTextView>(R.id.atv_item_community_name)
        var mAtvEnterTime = itemView.findViewById<AppCompatTextView>(R.id.atv_item_enter_time)
        var mAtvEnterStatus = itemView.findViewById<AppCompatTextView>(R.id.atv_enter_status)

    }
}

