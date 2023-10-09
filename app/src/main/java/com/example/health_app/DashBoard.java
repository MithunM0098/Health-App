package com.example.health_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.health_app.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DashBoard extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST = 1001;
    private static final int PICK_IMAGE_REQUEST = 1002;
    private static final int CAMERA_REQUEST = 1003;
    private ImageView profilePictureImageView, camera;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        // Initialize views
        initializeViews();

        // Setup toolbar and navigation drawer
        setupToolbarAndNavigationDrawer();
        profilePictureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, e.g., open a dialog to change the profile picture
                showProfilePictureChangeDialog();
            }
        });
        // Load profile picture if it exists
        loadProfilePicture();
    }

    private void showProfilePictureChangeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Profile Picture");
        builder.setItems(new CharSequence[]{"Choose from Gallery"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // User chose to select a new profile picture from the gallery
                        pickImageFromGallery();
                        break;
                    // Add more options here if needed
                }
            }
        });
        builder.show();
    }

    // Define a method to save the image to internal storage
    private void saveImageToInternalStorage(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            File internalStorageDir = getFilesDir(); // or getCacheDir() for cache storage
            File profileImageFile = new File(internalStorageDir, "profile_image.jpg");

            FileOutputStream outputStream = new FileOutputStream(profileImageFile);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            // Now you have saved the image permanently, you can set it as the profile picture
            // You may also want to store the file path in SharedPreferences or a database
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


// ...

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            // Save the selected image to internal storage
            saveImageToInternalStorage(selectedImageUri);

            // Load and display the new profile picture
            loadProfilePicture();
        }
    }

// ...

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void initializeViews() {
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        profilePictureImageView = headerView.findViewById(R.id.profilepic);
        camera = headerView.findViewById(R.id.cameraIcon);
    }

    private void setupToolbarAndNavigationDrawer() {
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);

        // Set up navigation drawer icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Set up navigation item click listener
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleNavigationItemSelected(item);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void loadProfilePicture() {

        File profileImageFile = new File(getFilesDir(), "profile_image.jpg");

        if (profileImageFile.exists()) {
            // Load the image and set it to the ImageView
            Bitmap bitmap = BitmapFactory.decodeFile(profileImageFile.getAbsolutePath());
            profilePictureImageView.setImageBitmap(bitmap);
        }
    }

    /*int id = item.getItemId();
    drawerLayout.closeDrawer(GravityCompat.START);
    switch (id)
    {

        case R.id.nav_home:
            Intent i1=new Intent(DashBoard.this,DashBoard.class);
            startActivity(i1);
            break;
        case R.id.nav_edit:
            Intent i=new Intent(DashBoard.this,Edit_profile.class);
            startActivity(i);
            break;
        case R.id.symptoms:
            Toast.makeText(DashBoard.this, "Symptoms is Clicked",Toast.LENGTH_SHORT).show();break;
        case R.id.logout:
            Intent i2=new Intent(DashBoard.this,MainActivity.class);
            startActivity(i2);
            break;
        default:
            return true;

    }
    return true;
}
});*/
    private void handleNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Intent i1 = new Intent(DashBoard.this, DashBoard.class);
                startActivity(i1);
                break;
            case R.id.nav_edit:
                Intent i = new Intent(DashBoard.this, Edit_profile.class);
                startActivity(i);
                break;
            case R.id.symptoms:
                Toast.makeText(DashBoard.this, "Symptoms is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Intent i2 = new Intent(DashBoard.this, MainActivity.class);
                startActivity(i2);
                break;
        }
    }
}

    // Rest of your code...

    // Handle navigation item selection


    // Rest of your code...

