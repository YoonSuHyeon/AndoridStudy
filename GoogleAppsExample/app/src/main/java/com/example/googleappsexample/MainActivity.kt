package com.example.googleappsexample

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.speech.RecognizerIntent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.InputStream


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var filePath: File
     var reqWidth = 0
    var reqHeight = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_browser.setOnClickListener(this)
        btn_call.setOnClickListener(this)
        btn_camera_data.setOnClickListener(this)
        btn_camera_file.setOnClickListener(this)
        btn_contacts.setOnClickListener(this)
        btn_map.setOnClickListener(this)
        btn_speech.setOnClickListener(this)
        reqWidth = resources.getDimensionPixelSize(R.dimen.request_image_width);
        reqHeight = resources.getDimensionPixelSize(R.dimen.request_image_height);
    }

    override fun onClick(v: View?) {
        if(v==btn_contacts){
            //주소록 목록 띄우기
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            startActivityForResult(intent,10)
        }else if(v==btn_camera_data){
            //카메라 앱 intent -data 흭득
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,30)
        }else if(v==btn_camera_file){
            //카메라 앱 intent -file 공유
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                try{
                    val dirPath=Environment.getExternalStorageDirectory().absolutePath+"/myApp"
                    val dir = File(dirPath)
                    if(!dir.exists()){
                        dir.mkdir()
                    }
                    filePath=File.createTempFile("IMG",".jpg",dir)
                    if(!filePath.exists()){
                        filePath.createNewFile()
                    }
                    val photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider",filePath)
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                    intent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                    startActivityForResult(intent,40)
                }catch(e:Exception){
                    e.printStackTrace()
                }

            }
            else{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),100)
            }

        }else if(v==btn_speech){
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"음성인식 테스트")
            startActivityForResult(intent,50)

        }else if(v==btn_map){
            val intent =Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5662952,126.9779451"))
            startActivity(intent)
        }else if(v==btn_browser){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.seoul.go.kr"))
            startActivity(intent)
        }else if(v==btn_call){
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_CALL,Uri.parse("tel:02-120"))
                startActivity(intent)
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),100)
            }
        }else if(v==resultImageView){
            val intent = Intent()
            intent.action=Intent.ACTION_VIEW
            val photoURI =FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID+".provider",filePath)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(intent)
        }
    }
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            val result = data!!.dataString
            resultView.text = result
        } else if (requestCode == 30 && resultCode == Activity.RESULT_OK) {
            val bitmap = data!!.extras!!["data"] as Bitmap?
            resultImageView.setImageBitmap(bitmap)
        } else if (requestCode == 40 && resultCode == Activity.RESULT_OK) {
            if (filePath != null) {
                val options = BitmapFactory.Options()
                options.inJustDecodeBounds = true
                try {
                    var `in`: InputStream? = FileInputStream(filePath)
                    BitmapFactory.decodeStream(`in`, null, options)
                    `in`!!.close()
                    `in` = null
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val height = options.outHeight
                val width = options.outWidth
                var inSampleSize = 1
                if (height > reqHeight || width > reqWidth) {
                    val heightRatio =
                        Math.round(height.toFloat() / reqHeight as Float)
                    val widthtRatio =
                        Math.round(width.toFloat() / reqWidth as Float)
                    inSampleSize = if (heightRatio < widthtRatio) heightRatio else widthtRatio
                }
                val imgOptions = BitmapFactory.Options()
                imgOptions.inSampleSize = inSampleSize
                val bitmap =
                    BitmapFactory.decodeFile(filePath.absolutePath, imgOptions)
                resultImageView.setImageBitmap(bitmap)
            }
        } else if (requestCode == 50 && resultCode == Activity.RESULT_OK) {
            val results =
                data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val result = results[0]
            resultView.text = result
        }
    }



}
