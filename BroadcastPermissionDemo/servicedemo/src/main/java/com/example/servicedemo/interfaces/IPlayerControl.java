package com.example.servicedemo.interfaces;

/**
 * 控制音乐接口
 */
public interface IPlayerControl {

    /**
     * 播放状态
     */
    int PLAY_STATE_PLAY = 1;
    int PLAY_STATE_PAUSE = 2;
    int PLAY_STATE_STOP = 3;

    /**
     * 把UI的控制接口设置给逻辑层
     *
     * @param iPlayerViewControl
     */
    void registerViewController(IPlayerViewControl iPlayerViewControl);

    /**
     * 取消通知的注册
     */
    void unregisterViewController();

    /**
     * 播放
     */
    void playerOrpause();

    /**
     * 停止
     */
    void stopPlay();

    /**
     * 设置播放进度
     *
     * @param seek
     */
    void seekTo(int seek);
}

