package com.example.sobclient;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;

public class TestContentProvier extends Activity {

    private static final String TAG = "TestContentProvier";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcontentprovider);
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://content://com.example.contentprovider/");
        contentResolver.registerContentObserver(uri, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Log.d(TAG,"用户数据发生变化...");
            }
        });
    }

    /**
     * 点击的时候获取其他程序的内容
     *
     * @param view
     */
    public void getRemoteUser(View view) {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.example.contentprovider/user");
        Cursor cursor = contentResolver.query(uri, null, "userName=?", new String[]{"pangjiaming"}, null);
        String[] columnNames = cursor.getColumnNames();
        for (String columnName : columnNames) {
            Log.d(TAG, "cloumnNames ---- >" + columnName);
        }

        while (cursor.moveToNext()) {
            Log.d(TAG, "=======");
            for (String columnName : columnNames) {
                String value = cursor.getString(cursor.getColumnIndex(columnName));
                Log.d(TAG, columnName + "  =======  " + value);
            }
            Log.d(TAG, "---------------");
        }

        cursor.close();
    }

    public void addUser(View view) {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://content://com.example.contentprovider/user");
        ContentValues values = new ContentValues();
        values.put("userName", "BillerGate");
        values.put("password", "123");
        values.put("sex", "female");
        values.put("age", 10);
        contentResolver.insert(uri, values);
    }
}
