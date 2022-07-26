package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

/**
 * 对于横竖屏切屏操作
 * 生命周期也会发生变化
 * 当切换的时候Activity会销毁然后重新Create
 * 需要在设置里面 设置禁止旋转或者
 * 如果开启了自动选择
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SeekBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate----");
        initView();
    }

    private void initView() {
        progress = findViewById(R.id.progress);
//        初始化数据
        progress.setMax(100);
        progress.setProgress(0);
        Log.d(TAG,"progress---" + progress.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart----");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume----");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause----");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop----");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy----");
    }
}
