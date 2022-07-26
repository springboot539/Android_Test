package com.example.contactsprovider;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkReadContactsPermission();
    }

    private void checkReadContactsPermission() {
        int checkPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
//            判断结果
            if (permissions.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "has  permission");
            } else {
                Log.d(TAG, "no permission");

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                    Log.d(TAG, "用户之前勾选了不再询问");
//                    弹出一个对话框，提示用户需要权限，并跳转到设置中去
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
//                    在Activity结果范围内，再次检查是否有权限
                    startActivityForResult(intent, PERMISSION_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_CODE);
//                    请求授权
                    Log.d(TAG, "请求授权");
                }
            }
        }
    }

    public void getContactsInfo(View view) {
        Toast.makeText(this, "看看有没有反应", Toast.LENGTH_SHORT).show();
        getUserInfo();
    }

    private void getUserInfo() {
        ContentResolver contentResolver = getContentResolver();
//        Uri rawContactUri = Uri.parse("content://" + ContactsContract.AUTHORITY + "/raw_contacts");
        Uri rawContactUri = ContactsContract.RawContacts.CONTENT_URI;
        Cursor cursor = contentResolver.query(rawContactUri, new String[]{"contact_id", "display_name"}, null, null, null);
        List<UserInfo> userInfos = new ArrayList<>();
        while (cursor.moveToNext()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(cursor.getString(cursor.getColumnIndex("contact_id")));
            userInfo.setDisplayName(cursor.getString(cursor.getColumnIndex("display_name")));
            userInfos.add(userInfo);
        }
        cursor.close();
        Uri phoneUri = Uri.parse(ContactsContract.Data.CONTENT_URI + "/phones");
        for (UserInfo userInfo : userInfos) {
            Cursor query = contentResolver.query(phoneUri, new String[]{"data1"}, "raw_contact_id=?", new String[]{userInfo.getId()}, null);
            if (query.moveToNext()){
                userInfo.setPhoneNum(query.getString(0).replace("-",""));
            }
            query.close();
            Log.d(TAG,"userInfo -- >" + userInfo);

        }
    }
}