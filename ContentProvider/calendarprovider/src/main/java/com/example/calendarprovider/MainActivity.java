package com.example.calendarprovider;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.TimeZone;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkCalendarPermission();
        queryCalendars();

    }

    private void checkCalendarPermission() {
        int readPermission = checkSelfPermission(Manifest.permission.READ_CALENDAR);
        int writePermission = checkSelfPermission(Manifest.permission.WRITE_CALENDAR);
        if (readPermission == PackageManager.PERMISSION_GRANTED && writePermission == PackageManager.PERMISSION_GRANTED) {
//            表示有权限
        } else {
            Log.d(TAG, "么有权限需要获取");
//            去获取权限
//            做个提示,用户点击了确定以后再去调用请求权限
//            如果点击了不在提示,就不再获取了,将会不能使用
            requestPermissions(new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
//            判断结果 其实permission就是上面requestPermission(...)中的权限,只需要和grantResults对比是否相同就行
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                有权限了
                Log.d(TAG, "has Permission....");
            } else {
//                没有权限 没办法了
                Log.d(TAG, "no Permission....");
                finish();
            }
        }
    }

    private void queryCalendars() {
        ContentResolver contentResolver = getContentResolver();
//        Uri uri = Uri.parse("content://com.android.calendar/calendars");
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        Cursor query = contentResolver.query(uri, null, null, null, null);
        String[] columnNames = query.getColumnNames();
//        Log.d(TAG,"moveToFirst---->"+query.moveToFirst());
//        Log.d(TAG,"moveToLast?---->"+query.moveToLast());
//        Log.d(TAG,"为什么这个是False(moveToNext)?---->"+query.moveToNext());
        while (query.moveToNext()) {
            Log.d(TAG, "------------------");
            for (String columnName : columnNames) {
                Log.d(TAG, columnName + " ----- >" + query.getString(query.getColumnIndex(columnName)));
            }
            Log.d(TAG, "------------------");
        }
    }

    /**
     * 向日历添加提醒事件
     *
     * @param view
     */
    public void addAlertEvent(View view) {
        long calID = 1;
//        开始和结束时间
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2022, 6, 21, 11, 0);
        long beginTimeMills = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2022, 6, 22, 0, 0, 0);
        long endTimeMills = endTime.getTimeInMillis();
//        插入事件
        String timeZone = TimeZone.getDefault().getID();
        Log.d(TAG, "timezone ----- > " + timeZone);
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, beginTimeMills);
        values.put(CalendarContract.Events.DTEND, endTimeMills);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone);
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.TITLE, "这是一个晴朗的早晨，要抬头看远方");
        values.put(CalendarContract.Events.DESCRIPTION, "一万年太久，只争朝夕");
        values.put(CalendarContract.Events.EVENT_LOCATION, "中国-上海");
        Uri uri = CalendarContract.Events.CONTENT_URI;
        ContentResolver contentResolver = getContentResolver();
        Uri resultUri = contentResolver.insert(uri, values);
        Log.d(TAG, "最后的结果，resultUri ------ > " + resultUri);

//        向日历里面添加提醒事件

        String eventId = resultUri.getLastPathSegment();
        Log.d(TAG, "eventId ---- >" + eventId);
        ContentValues contentValues = new ContentValues();
        contentValues.put(CalendarContract.Reminders.MINUTES, 17);
        contentValues.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALARM);
        contentValues.put(CalendarContract.Reminders.EVENT_ID, eventId);
        Uri eventUri = CalendarContract.Reminders.CONTENT_URI;
        contentResolver.insert(eventUri, contentValues);
        Log.d(TAG, "已经成功插入提醒功能");


    }
}