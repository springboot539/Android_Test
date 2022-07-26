package com.example.smsprovider;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.net.URI;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSmsReadPermission();
    }

    private void checkSmsReadPermission() {
        int permissionResult = checkSelfPermission(Manifest.permission.READ_SMS);
        if (permissionResult != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_SMS},PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE){
            Log.d(TAG,"permissions length ---- > " + permissions.length);
            Log.d(TAG,"grantResult ---- > " + grantResults[0]);
        }
    }

    public void getSMSContent(View view) {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        Cursor query = contentResolver.query(uri, null, null, null, null);
        String[] columnNames = query.getColumnNames();
        while (query.moveToNext()){
            for (String columnName : columnNames) {
                Log.d(TAG,columnName + "-----> " + query.getString(query.getColumnIndex(columnName)));
            }
        }
        query.close();
    }

    /**
     * 跳转到验证码界面
     * @param view
     */
    public void getVed(View view) {
        startActivity(new Intent(this,VerifyCodeActivity.class));
    }
}