package com.example.cameraex2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s =getIntent().getStringExtra("uri");
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageURI(Uri.parse(s));

    }
}