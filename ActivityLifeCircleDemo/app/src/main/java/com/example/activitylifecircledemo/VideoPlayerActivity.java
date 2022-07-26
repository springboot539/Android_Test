package com.example.activitylifecircledemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class VideoPlayerActivity extends Activity {
    private static final String TAG = "VideoPlayerActivity";
    private TextView statusText;
    private Button playerControlBtn;
    private boolean isPlayer = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();
        initListener();
    }


    private void initListener() {
        playerControlBtn.setOnClickListener(v -> {
            if (isPlayer) {
                stop();

            } else {
                play();
            }
        });

    }

    private void play() {
        Log.d(TAG, "播放电影");
        statusText.setText("正在播放电影，声音很大.....");
        playerControlBtn.setTag("暂停");
        isPlayer = true;
    }

    private void stop() {
        Log.d(TAG, "停止播放电影");
        statusText.setText("电影已经停止播放~~~");
        playerControlBtn.setTag("播放");
        isPlayer = false;
    }

    private void initView() {
        statusText = findViewById(R.id.current_play_status);
        playerControlBtn = findViewById(R.id.player_control);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart....");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop....");
        if (isPlayer){
            stop();
        }
    }
}
