package com.example.cameraex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s =getIntent().getStringExtra("uri");
        int width =getIntent().getIntExtra("width",0);
        int height=getIntent().getIntExtra("height",0);

        ImageView imageView = findViewById(R.id.image);
       /* imageView.setImageURI(Uri.parse(s));*/
        // 잘라낼 가로 픽셀 크기
        int nWidth  = 100;
        // 잘라낼 세로 픽셀 크기
        int nHeight = 100;
        Bitmap mSource = BitmapFactory.decodeFile(s);
        Bitmap targetBitmap=Bitmap.createBitmap(mSource,width/4,height/4,(width/4)*3,(width/4)*3);
        imageView.setImageBitmap(targetBitmap);
        /*

        int targetWidth = 100;
        int targetHeight = 100;
        RectF rectf = new RectF(width/4,height/4,(width/4)*3,(width/4)*3);//was missing before update
        Bitmap targetBitmap = Bitmap.createBitmap(
                targetWidth, targetHeight,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addRect(rectf, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(
                   mSource,
                new Rect(0, 0, mSource.getWidth(), mSource.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight),
                null);*/


        imageView.setImageBitmap(targetBitmap);
    }




}