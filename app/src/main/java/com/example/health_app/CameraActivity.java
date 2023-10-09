package com.example.health_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hdodenhof.circleimageview.CircleImageView;

public class CameraActivity extends AppCompatActivity {


    private CircleImageView profilepic;

    private ImageCapture imageCapture;
    private ImageView cameraIcon;


    private ExecutorService cameraExecutor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);


        profilepic = findViewById(R.id.profilepic);
        cameraIcon = findViewById(R.id.cameraIcon);
        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takepicture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takepicture,2);

            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==2){
            Bundle bundle=data.getExtras();
            Bitmap imageBitmap=(Bitmap) bundle.get("data");
            profilepic.setImageBitmap(imageBitmap);
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (data != null) {
//            Uri uri = data.getData();
//            if (uri != null) {
//                profilepic.setImageURI(uri);
//            } else {
//                // Handle the case where the URI is null
//                Toast.makeText(this, "Image URI is null", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            // Handle the case where the data Intent is null
//            Toast.makeText(this, "Data Intent is null", Toast.LENGTH_SHORT).show();
//        }
//    }
}
