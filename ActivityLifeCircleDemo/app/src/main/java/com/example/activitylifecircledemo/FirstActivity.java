package com.example.activitylifecircledemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class FirstActivity extends Activity {
    private static final String TAG = "FirstActivity";

    public void skep2SecondActivity(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.d(TAG, "onCreate.....");
    }

    /**
     * 已经可见了 但是没有焦点 不可以进行操作
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 可见，并且获得到焦点，可以交互
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 暂停了 失去焦点不可以操作
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 已经不可见了
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
