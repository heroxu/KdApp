package com.xxr.kdapp.ui.activity


import android.Manifest
import android.content.Intent
import android.util.Log
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.xxr.kdapp.R
import com.xxr.kdapp.base.BaseActivity
import com.xxr.kdapp.image.GlideEngine
import com.xxr.kdapp.utils.FileUtils
import com.xxr.kdapp.utils.LogUtils
import kotlinx.android.synthetic.main.activity_taking_pictures.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class TakingPicturesActivity : BaseActivity() , EasyPermissions.PermissionCallbacks{

    private lateinit var mPicturePath : String

    companion object {
        const val TAG = "TakingPicturesActivity"
        const val REQUEST_CAMERA_CODE = 10002
        const val REQUEST_SETTING_CODE = 10001

    }

    override fun layoutId(): Int {
        return R.layout.activity_taking_pictures
    }

    override fun initData() {
        mPicturePath = ""
    }

    override fun initView() {
        initTitleBar(base_title_bar)
        mBaseTitleBar.setTitleText(R.string.title_taking_picture)

        aiv_taking_picture.setOnClickListener {
            requestPermission()
        }
    }

    private fun openCamera(){
        PictureSelector.create(this)
            .openCamera(PictureMimeType.ofImage())
            .forResult(object : OnResultCallbackListener<LocalMedia?> {

                override fun onCancel() {
                    Log.i(TAG, "PictureSelector Cancel")
                }

                override fun onResult(result: MutableList<LocalMedia?>?) {
                    if (result != null) {
                        for (media in result) {
                            if(media!=null){
                                Log.i(TAG, "是否压缩:" + media.isCompressed)
                                Log.i(TAG, "压缩:" + media.compressPath)
                                Log.i(TAG, "原图:" + media.path)

//                                FileUtils.copyFile()
                                val file = FileUtils.getFileByPath(media.path)
                                LogUtils.d(file.path + "   ${file.name}")

                                if(media.path.isNotEmpty()){
                                    GlideEngine.createGlideEngine().loadImage(this@TakingPicturesActivity,media.path,aiv_camera_picture)

                                    OkGo.post<String>("http://10.10.18.55:8080/uploadFile")
                                        .tag(this)
//                                        .params("icon", File("D:\\xxr\\KdApp\\app\\src\\tempimage",media.path))
                                        .params("file", file)
                                        .isMultipart(true)
                                        .execute(object : StringCallback() {
                                            override fun onSuccess(response: Response<String>) {
                                                LogUtils.e("上传成功" + response.body())
                                            }

                                            override fun onError(response: Response<String>) {
                                                LogUtils.e("上传失败" + response.body())
                                            }
                                        })

                                }
                                Log.i(TAG, "是否裁剪:" + media.isCut)
                                Log.i(TAG, "裁剪:" + media.cutPath)
                                Log.i(TAG, "是否开启原图:" + media.isOriginal)
                                Log.i(TAG, "原图路径:" + media.originalPath)
                                Log.i(TAG, "Android Q 特有Path:" + media.androidQToPath)
                                Log.i(TAG, "宽高: " + media.width + "x" + media.height)
                                Log.i(TAG, "Size: " + media.size)
                            }
                        }
                    }
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LogUtils.d("TakingPicturesActivity","requestCode = $requestCode   resultCode = $resultCode" )
        if (requestCode == REQUEST_SETTING_CODE) {
            requestPermission()
        }
    }


    /**
     * 去申请权限
     */
    private fun requestPermission() {
        val perms = arrayOf(Manifest.permission.CAMERA)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            openCamera()
        } else {
            // RC_CAMERA_AND_RECORD_AUDIO 请求码, 用于回调的时候判断是哪次申请 perms 要申请的权限
            EasyPermissions.requestPermissions(
                this,
                "KotlinMvp应用需要以下权限，请允许",
                REQUEST_CAMERA_CODE,
                *perms
            )
        }
    }

    /**
     * 权限申请拒绝的回调
     *
     * @param requestCode 申请权限时的请求码
     * @param perms 申请拒绝的权限集合
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.i(SplashActivity.TAG, "onPermissionsDenied: ")

        if (requestCode != REQUEST_CAMERA_CODE) {
            return
        }

        for (i in 0 until perms.size) {
            if (perms[i] == Manifest.permission.CAMERA) {
                LogUtils.i(SplashActivity.TAG, "onPermissionsGranted: " + "相机权限被拒绝")
            }
        }

        //如果有一些权限被永久的拒绝, 就需要转跳到 设置-->应用-->对应的App下去开启权限
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this)
                .setTitle("权限已经被您拒绝")
                .setRationale("如果不打开权限则无法使用该功能,点击确定去打开权限")
                .setRequestCode(SplashActivity.REQUEST_SETTING_CODE) //用于onActivityResult回调做其它对应相关的操作
                .build()
                .show()
        }
    }

    /**
     * 权限申请成功的回调
     *
     * @param requestCode 申请权限时的请求码
     * @param perms 申请成功的权限集合
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.i(SplashActivity.TAG, "onPermissionsGranted: ")
        var hasCameraPermission = false
        if (REQUEST_CAMERA_CODE != requestCode) {
            return
        }
        for (i in 0 until perms.size) {
            if (perms[i] == Manifest.permission.CAMERA) {
                hasCameraPermission = true
                Log.i(SplashActivity.TAG, "onPermissionsGranted: " + "读取电话权限成功")
            }
        }
        if(hasCameraPermission){
            openCamera()
        }else{
            AppSettingsDialog.Builder(this)
                .setTitle("权限已经被您拒绝")
                .setRationale("如果不打开权限则无法使用该功能,点击确定去打开权限")
                .setRequestCode(SplashActivity.REQUEST_SETTING_CODE) //用于onActivityResult回调做其它对应相关的操作
                .build()
                .show()
        }
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


}
