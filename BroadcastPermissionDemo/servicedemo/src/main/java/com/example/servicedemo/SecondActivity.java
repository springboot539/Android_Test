package com.example.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.servicedemo.interfaces.ICommunication;
import com.example.servicedemo.services.SecondService;

/**
 * 混合启动方法
 */
public class SecondActivity extends Activity {

    private static final String TAG = "SecondActivity";
    private boolean isBind;
    private ICommunication communication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void startServiceClick(View view) {
        Intent intent = new Intent(this, SecondService.class);
        startService(intent);
    }

    public void bindServiceClick(View view) {
        Intent intent = new Intent(this, SecondService.class);
//        如果服务已经启动，则不会再次启动，如果服务没有启动，则启动服务
        isBind = bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            服务绑定了，会返回Binder
            Log.d(TAG,"服务绑定了...");
            communication = (ICommunication) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//          服务解绑了
            communication = null;
        }
    };

    public void callServiceMethod(View view) {
        if (communication != null) {
            communication.callServiceInnerMethod();
        }
    }

    public void unBindServiceClick(View view) {
        if (isBind && mConnection != null){
            unbindService(mConnection);
            isBind = false;
        }
    }

    public void stopServiceClick(View view) {
        Intent intent = new Intent(this, SecondService.class);
        stopService(intent);
    }
}
