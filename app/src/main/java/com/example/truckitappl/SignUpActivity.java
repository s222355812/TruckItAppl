package com.example.truckitappl;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_REQUEST = 2;

    private ImageView imageViewProfile;
    private EditText editTextName, editTextUsername, editTextPassword, editTextConfirmPassword;
    private Button buttonCreateAccount;
    private Bitmap profileImageBitmap;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

        imageViewProfile = findViewById(R.id.imageViewProfile);
        editTextName = findViewById(R.id.editTextName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SignUpActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openImagePicker();
                } else {
                    ActivityCompat.requestPermissions(SignUpActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            STORAGE_PERMISSION_REQUEST);
                }
            }
        });

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String email = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                if (validateSignUp(name, email, password, confirmPassword)) {
                    // Save the user data in the database
                    saveUserData(name, email, password, profileImageBitmap);

                    Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }


    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        try {
                            profileImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                            imageViewProfile.setImageBitmap(profileImageBitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        launcher.launch(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();

            try {
                profileImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imageViewProfile.setImageBitmap(profileImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateSignUp(String name, String email, String password, String confirmPassword) {
        // Check if any of the fields are empty
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Check if the email is valid
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void saveUserData(String name, String email, String password, Bitmap profileImage) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USER_NAME, name);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        if (profileImage != null) {
            // Convert the profile image bitmap to a byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            profileImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] profileImageByteArray = stream.toByteArray();

            values.put(DatabaseHelper.COLUMN_PROFILE_IMAGE, profileImageByteArray);
        }

        long rowId = db.insert(DatabaseHelper.TABLE_USERS, null, values);

        if (rowId == -1) {
            Toast.makeText(this, "Failed to save user data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User data saved successfully", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }}
