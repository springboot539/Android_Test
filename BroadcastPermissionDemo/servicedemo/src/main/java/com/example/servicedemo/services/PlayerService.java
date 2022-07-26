package com.example.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.servicedemo.interfaces.IPlayerControl;
import com.example.servicedemo.interfaces.IPlayerViewControl;
import com.example.servicedemo.presenter.PlayPresenter;

public class PlayerService extends Service {

    private PlayPresenter playPresenter;

    @Override
    public void onCreate() {
        super.onCreate();
        if (playPresenter == null) {
            playPresenter = new PlayPresenter();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return playPresenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playPresenter = null;
    }
}
