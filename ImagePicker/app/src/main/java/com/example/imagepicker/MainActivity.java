package com.example.imagepicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkReadImagePermission();
    }

    private void checkReadImagePermission() {
        int permissionCode = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCode != PackageManager.PERMISSION_GRANTED) {
//            没有权限，进行申请
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            Log.d(TAG,"grantResult length -->" + grantResults.length);
            Log.d(TAG,"grantResult grantResults[0] -->" + grantResults[0]);
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                有权限了
                Log.d(TAG, "has " + grantResults[0] + "permission...");
                Toast.makeText(this, "已经获取到权限了", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void pickImages(View view) {
        startActivity(new Intent(this, pickerActivity.class));
    }
}