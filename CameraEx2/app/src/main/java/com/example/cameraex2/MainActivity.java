package com.example.cameraex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s =getIntent().getStringExtra("uri");
        int width =getIntent().getIntExtra("width",0);
        int height=getIntent().getIntExtra("height",0);


        Bitmap rotatedBitmap = null;


        ImageView imageView = findViewById(R.id.image);





        try {
            File file = new File(s);
            Bitmap bitmap = MediaStore.Images.Media
                    .getBitmap(getContentResolver(), Uri.fromFile(file));
            Log.d("filebit",bitmap.getWidth()+"dd"+bitmap.getHeight());

            if (bitmap != null) {
                ExifInterface ei = new ExifInterface(s);
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);

                switch (orientation) {

                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotatedBitmap = rotateImage(bitmap, 90);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotatedBitmap = rotateImage(bitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotatedBitmap = rotateImage(bitmap, 270);
                        break;

                    case ExifInterface.ORIENTATION_NORMAL:
                    default:
                        rotatedBitmap = bitmap;
                }
                //imageView.setImageBitmap(rotatedBitmap);
            }
        }catch(Exception err){
            err.printStackTrace();
        }


        // canvas.drawRect(width/4,width/4,(width/4)*3,(width/4)*3,paint);
        Log.d("size","width"+rotatedBitmap.getWidth()+"height="+rotatedBitmap.getHeight());

        Bitmap targetBitmap=Bitmap.createBitmap(rotatedBitmap,rotatedBitmap.getWidth()/2-250,rotatedBitmap.getHeight()/2-250,500,500);
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



    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }



}