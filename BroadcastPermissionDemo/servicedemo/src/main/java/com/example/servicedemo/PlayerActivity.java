package com.example.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.example.servicedemo.interfaces.IPlayerControl;
import com.example.servicedemo.interfaces.IPlayerViewControl;
import com.example.servicedemo.services.PlayerService;

import static com.example.servicedemo.interfaces.IPlayerControl.PLAY_STATE_PAUSE;
import static com.example.servicedemo.interfaces.IPlayerControl.PLAY_STATE_PLAY;
import static com.example.servicedemo.interfaces.IPlayerControl.PLAY_STATE_STOP;

public class PlayerActivity extends Activity {

    private static final String TAG = "PlayerActivity";
    private SeekBar seekBar;
    private Button palyOrPause;
    private Button close;
    private PlayerConnection playerConnection;
    private IPlayerControl iPlayerControl;
    private boolean isUserTouchProgressBar = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();
        initEvent();
        initService();
        initBindService();
    }

    /**
     * 开启服务
     */
    private void initService() {
        startService(new Intent(this, PlayerService.class));
    }

    /**
     * 绑定服务
     */
    private void initBindService() {
        Intent intent = new Intent(this, PlayerService.class);
        if (playerConnection == null) {
            playerConnection = new PlayerConnection();
        }
        bindService(intent, playerConnection, BIND_AUTO_CREATE);
    }

    private class PlayerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPlayerControl = (IPlayerControl) service;
            iPlayerControl.registerViewController(mIPlayerViewController);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iPlayerControl = null;
        }
    }

    private void initEvent() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                进度条改变
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                 已经触摸
                isUserTouchProgressBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//              停止拖动
                int touchprogress = seekBar.getProgress();
                Log.d(TAG, "touchprogress --->" + touchprogress);
                if (iPlayerControl != null) {
                    iPlayerControl.seekTo(touchprogress);
                }
                isUserTouchProgressBar = false;
            }
        });
        palyOrPause.setOnClickListener(v -> {
//            播放/暂停
            if (iPlayerControl != null) {
                iPlayerControl.playerOrpause();
            }
        });

        close.setOnClickListener(v -> {
//            关闭
            if (iPlayerControl != null) {
                iPlayerControl.stopPlay();
            }
        });
    }

    private void initView() {
        seekBar = findViewById(R.id.seek_bar);
        palyOrPause = findViewById(R.id.play_or_pause_btn);
        close = findViewById(R.id.close_btn);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playerConnection != null) {
//            释放资源
            iPlayerControl.unregisterViewController();
            unbindService(playerConnection);
        }
    }

    private IPlayerViewControl mIPlayerViewController = new IPlayerViewControl() {
        @Override
        public void onPlayerStateChange(int state) {
//            要根据播放状态来修改UI
            switch (state) {
                case PLAY_STATE_PLAY:
//                    播放中修改修改按钮
                    palyOrPause.setText("暂停");
                    break;
                case PLAY_STATE_PAUSE:
                case PLAY_STATE_STOP:
                    palyOrPause.setText("播放");
                    break;
            }
        }

        @Override
        public void onSeekChange(int seek) {
//           改变播放进度,当用户触摸到
            Log.d(TAG, "current thread ... " + Thread.currentThread().getName());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!isUserTouchProgressBar) {
                        seekBar.setProgress(seek);
                    }
                }
            });
        }
    };
}
