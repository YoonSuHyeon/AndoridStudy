package com.example.aiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.aiactivity.ml.Model;
import com.google.flatbuffers.ByteBufferUtil;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.TensorProcessor;
import org.tensorflow.lite.support.common.ops.DequantizeOp;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.label.TensorLabel;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ImageProcessor imageProcessor =
                    new ImageProcessor.Builder()
                            .add(new ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
                            .build();

            Model model = Model.newInstance(this);


            // Creates inputs for reference.

            Drawable drawable = getDrawable(R.drawable.image8);

            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

            ByteBuffer buffer= ByteBuffer.allocate(bitmap.getByteCount()); //바이트 버퍼를 이미지 사이즈 만큼 선언

            bitmap.copyPixelsToBuffer(buffer);//비트맵의 픽셀을 버퍼에 저장

            //
            TensorBuffer inputI = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);







            TensorImage tImage = new TensorImage(DataType.FLOAT32);


            tImage.load(bitmap);
            tImage = imageProcessor.process(tImage);






            TensorProcessor probabilityProcessor =
                    new TensorProcessor.Builder().add(new DequantizeOp(0, 1/255.0f)).build();




            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);



            inputFeature0.loadBuffer(tImage.getBuffer());
            TensorBuffer dequantizedBuffer = probabilityProcessor.process(inputFeature0);



            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(dequantizedBuffer);
            TensorBuffer probabilityBuffer =
                    TensorBuffer.createFixedSize(new int[]{1, 20}, DataType.FLOAT32);
            probabilityBuffer=outputs.getOutputFeature0AsTensorBuffer();

            @NonNull float[] floatArray = probabilityBuffer.getFloatArray();

            float temp =0;
            int idx=0;
            for (int i = 0; i < floatArray.length; i++) {
                if(floatArray[i]> temp){
                    temp=floatArray[i];
                    idx=i;
                }
            }

            Log.d("idx:", idx + "   " + temp);
            final String ASSOCIATED_AXIS_LABELS = "label.txt";
            List<String> associatedAxisLabels = null;

            try {
                associatedAxisLabels = FileUtil.loadLabels(this, ASSOCIATED_AXIS_LABELS);
            } catch (IOException e) {
                Log.e("tfliteSupport", "Error reading label file", e);
            }
            Log.d("gg:", associatedAxisLabels.get(idx) );
            // Releases model resources if no longer used.


            TensorProcessor probabilityProcessor1 =
                    new TensorProcessor.Builder().add(new NormalizeOp(0, 20)).build();

            if (null != associatedAxisLabels) {
                // Map of labels and their corresponding probability
                TensorLabel labels = new TensorLabel(associatedAxisLabels,
                        probabilityProcessor1.process(probabilityBuffer));

                // Create a map to access the result based on label
                Map<String, Float> floatMap = labels.getMapWithFloatValue();

            }




            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }
}