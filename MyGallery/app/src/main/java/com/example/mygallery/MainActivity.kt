package com.example.mygallery

import android.content.pm.PackageManager
import android.media.ExifInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.lang.Exception
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private val REQUEST_READ_EXTERNAL_STORAGE = 1000
    private var timer: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {//권한이 허용되지 않았을 때.
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                //이전에 미리 거부
                alert("사진 정보를 얻으려면 외부 저장소 권환이 필수로 필요합니다.", "권한이 필요한 이유") {
                    yesButton {
                        //권한요청
                        ActivityCompat.requestPermissions(
                            this@MainActivity,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_READ_EXTERNAL_STORAGE
                        )
                    }
                    noButton { }
                }.show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERNAL_STORAGE
                )

            }
        } else {
            getAllPhotos()
        }


        btn_pause.setOnClickListener {
            pause()
        }

        btn_start.setOnClickListener {
            start()
        }

    }

    private fun start() {
        val adapter = viewPager.adapter

        timer?.cancel()
        timer = null

        timer = timer(period = 3000) {
            runOnUiThread {
                if (viewPager.currentItem < adapter!!.count - 1) {
                    viewPager.currentItem = viewPager.currentItem + 1
                } else {
                    viewPager.currentItem = 0
                }
            }
        }


    }

    private fun pause() {
        timer?.cancel()
        timer = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 1, 0, "이미지파일명")
        menu?.add(0, 2, 1, "메타데이터")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {



        val adapter = viewPager.adapter as MyPagerAdapter
        val photoFragment= adapter.getItem(viewPager.currentItem)as PhotoFragment
        val path=photoFragment.getUri()!!
        val photoName=path.split("/").last()


        var metadata:String=""
        try{
            val exif = ExifInterface(path)
            metadata=showExif(exif)
        }catch (e:Exception){
            Log.d("Exif",e.toString())
        }




        Log.d(",g" ,photoFragment.getUri()!!)
        when(item.itemId){
            1->{
                toast(photoName)
            }
            2->{
                toast(metadata)
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getAllPhotos()
                } else {
                    //권한거부
                    toast("권한 거부 됨")
                }
                return
            }
        }
    }

    private fun getAllPhotos() {
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"
        )

        val fragments = ArrayList<Fragment>()
        if (cursor != null) {
            while (cursor.moveToNext()) {

                val uri =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                Log.d("MainActivity", uri)
                fragments.add(PhotoFragment.newInstance(uri))
            }
            cursor.close()
        }
        val adapter = MyPagerAdapter(supportFragmentManager)
        adapter.updateFragments(fragments)
        viewPager.adapter = adapter


    }
    fun showExif(exif:ExifInterface):String{
        var  temp:String=""
        temp+="DATETIME:${exif.getAttribute((ExifInterface.TAG_DATETIME))}\n"
        temp+="FLASH:${exif.getAttribute((ExifInterface.TAG_FLASH))}\n"
        temp+="MAKE:${exif.getAttribute((ExifInterface.TAG_MAKE))}\n"
        temp+="MODEL:${exif.getAttribute((ExifInterface.TAG_MODEL))}\n"
        temp+="ORIENTATION:${exif.getAttribute((ExifInterface.TAG_ORIENTATION))}\n"
        temp+="WHITE_BALANCE:${exif.getAttribute((ExifInterface.TAG_WHITE_BALANCE))}\n"
        temp+="IMAGE_LENGTH:${exif.getAttribute((ExifInterface.TAG_IMAGE_LENGTH))}\n"
        temp+="IMAGE_WIDTH:${exif.getAttribute((ExifInterface.TAG_IMAGE_WIDTH))}\n"


        return  temp
    }
}