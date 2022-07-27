package com.example.multithreadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView mTv_content;


    private String mStrFormat;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0 && msg != null) {
                mTv_content.setText(msg.obj.toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv_content = findViewById(R.id.tv_content);
    }

    public void start(View view) {
//        String mStrFormat = getStringFormNet();
//        开启一个新线程
        new Thread(() -> {
            String stringFormNet = getStringFormNet();
//                需要把字符串传递给主线程，更新UI界面
            Message msg = new Message();
            msg.what = 0;
            msg.obj = stringFormNet;
            mHandler.sendMessage(msg);
        }).start();
        mTv_content.setText(mStrFormat);
        Toast.makeText(this, "任务完成了", Toast.LENGTH_SHORT).show();
    }

    private String getStringFormNet() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb = sb.append("这是第" + i + "。");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sb.toString();
        return sb.toString();
    }


}