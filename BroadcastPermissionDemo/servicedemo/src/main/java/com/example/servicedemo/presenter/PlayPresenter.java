package com.example.servicedemo.presenter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.util.Log;

import com.example.servicedemo.interfaces.IPlayerControl;
import com.example.servicedemo.interfaces.IPlayerViewControl;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayPresenter extends Binder implements IPlayerControl {
    private static final String TAG = "PlayPresenter";
    private IPlayerViewControl viewController;
    private int currentState = PLAY_STATE_STOP;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private SeekTimeTask timeTask;

    @Override
    public void registerViewController(IPlayerViewControl iPlayerViewControl) {
        this.viewController = iPlayerViewControl;
    }

    @Override
    public void unregisterViewController() {
        viewController = null;
    }

    @Override
    public void playerOrpause() {
        Log.d(TAG, "playerOrpause...");
        if (currentState == PLAY_STATE_STOP) {
//            创建播放器
            initPlayer();
//            设置数据源
            try {
                mediaPlayer.setDataSource("/mnt/sdcard/see_you_again.mp3");
                mediaPlayer.prepare();
                mediaPlayer.start();
                currentState = PLAY_STATE_PLAY;
                startTimer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currentState == PLAY_STATE_PLAY) {
//            如果是播放就暂停
            if (mediaPlayer != null) {
                mediaPlayer.pause();
                currentState = PLAY_STATE_PAUSE;
                stopTimer();
            }
        } else if (currentState == PLAY_STATE_PAUSE) {
//            如果是暂停就播放
            if (mediaPlayer != null) {
                mediaPlayer.start();
                currentState = PLAY_STATE_PLAY;
                startTimer();
            }
        }

        if (viewController != null) {
            viewController.onPlayerStateChange(currentState);
        }
    }

    /**
     * 初始化播放器
     */
    private void initPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
    }

    @Override
    public void stopPlay() {
        Log.d(TAG, "stopPlay...");
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            currentState = PLAY_STATE_STOP;
            stopTimer();
//            更新播放状态
            if (viewController != null) {
                viewController.onPlayerStateChange(currentState);
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void seekTo(int seek) {
        Log.d(TAG, "seekTo..." + seek);
//      0-100之间
        if (mediaPlayer != null) {
            int target = (int) (seek * 1.0f / 100 * mediaPlayer.getDuration());
            mediaPlayer.seekTo(target);
        }
    }

    /**
     * 开启一个TimerTask
     */
    private void startTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        if (timeTask == null) {
            timeTask = new SeekTimeTask();
        }
        timer.schedule(timeTask, 0, 500);
    }

    private void stopTimer() {
        if (timeTask != null) {
            timeTask.cancel();
            timeTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private class SeekTimeTask extends TimerTask {

        @Override
        public void run() {
//            获取当前进度
            if (mediaPlayer != null && viewController != null) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                Log.d(TAG, "current play position ..." + currentPosition);
                int curPosition = (int) (currentPosition *0.1f/ mediaPlayer.getDuration() * 100);
                viewController.onSeekChange(curPosition);
            }
        }
    }
}
