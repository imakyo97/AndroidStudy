package com.example.cameraintentsample

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.engage.common.datamodel.Image
import java.text.SimpleDateFormat
import java.util.Date


class MainActivity : AppCompatActivity() {
    private var _imageUri: Uri? = null
    private val _cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallbackFromCamera()
    )

    private inner class ActivityResultCallbackFromCamera : ActivityResultCallback<ActivityResult> {
        override fun onActivityResult(result: ActivityResult) {
            if (result.resultCode == RESULT_OK) {
                val ivCamera = findViewById<ImageView>(R.id.ivCamera)
                ivCamera.setImageURI(_imageUri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCameraImageClick(view: View) {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val now = Date()
        val nowStr = dateFormat.format(now)
        val fileName = "CameraIntentSamplePhoto_${nowStr}.jpg"
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, fileName)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        _imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, _imageUri)
        _cameraLauncher.launch(intent)
    }
}
