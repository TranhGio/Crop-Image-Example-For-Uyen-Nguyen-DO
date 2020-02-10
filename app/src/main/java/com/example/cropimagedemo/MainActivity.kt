package com.example.cropimagedemo

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CAMERA = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Request quyen camera
        requestPermissions(arrayOf(android.Manifest.permission.CAMERA), REQUEST_CAMERA)
        //Set su kien click cho button
        btnOpeCamera.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Mo activity de cat anh
                CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this@MainActivity);
            }
        })
    }
    //Ham nhan ket qua tra ve sau khi da mo activity cat anh
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Kiem tra request code
        when (requestCode) {
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                //Nhan ket qua tra ve sau khi cat anh
                val result = CropImage.getActivityResult(data)
                //Kiem tra trang thai tra ve (Co 2 trang thai la Ok va Error)
                if (resultCode == Activity.RESULT_OK) {
                    //Chuyen ket qua sang URI
                    val resultUri: Uri = result.uri
                    //Set image URI bang URI da nhan duoc
                    imgCrop.setImageURI(resultUri)
                } //Xu ly ket qua khi xay ra loi
                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    val error = result.error
                }
            }
        }
    }
}
