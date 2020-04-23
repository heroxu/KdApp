package com.xxr.kdapp.ui.activity


import androidx.recyclerview.widget.LinearLayoutManager
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseActivity
import com.xxr.kdapp.constant.Constant
import com.xxr.kdapp.mvp.model.EnterRecordModel
import com.xxr.kdapp.ui.adpter.RecordAdapter
import kotlinx.android.synthetic.main.activity_enter_record.*

class EnterRecordActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_enter_record
    }

    override fun initData() {
        rv_enter_record.layoutManager = LinearLayoutManager(this)
        rv_enter_record.adapter = RecordAdapter(this,addTestData())
    }

    override fun initView() {
        initTitleBar(base_title_bar)
        mBaseTitleBar.setTitleText(R.string.title_enter_record)
    }

    fun addTestData(): List<EnterRecordModel> {
        return listOfNotNull(
            EnterRecordModel(
                "深圳湾一号",
                "http://10.10.18.55:8080/downloadFile/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190513172049.jpg",
                System.currentTimeMillis(),
                Constant.ENTER_ALLOW
            ),
            EnterRecordModel(
                "南山一号",
                "http://10.10.18.55:8080/downloadFile/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190513172049.jpg",
                System.currentTimeMillis()+ 1000 * 195 ,
                Constant.ENTER_REFUSED
            ),
            EnterRecordModel(
                "坪山一号",
                "http://10.10.18.55:8080/downloadFile/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190513172049.jpg",
                System.currentTimeMillis()+ 1000 * 395 ,
                Constant.ENTER_CANCLE
            )

        )
    }


}


