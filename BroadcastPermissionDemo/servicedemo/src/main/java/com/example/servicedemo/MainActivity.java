package com.example.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.servicedemo.interfaces.ICommunication;
import com.example.servicedemo.services.FirstService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private boolean isServiceBinded;
    private ICommunication remoteBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 开启服务
     *
     * @param view
     */
    public void startServiceClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FirstService.class);
        startService(intent);
    }


    /**
     * 停止服务
     *
     * @param view
     */
    public void stopServiceClick(View view) {
        Intent intent = new Intent(this, FirstService.class);
        stopService(intent);
    }

    public void bindServiceClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FirstService.class);
        isServiceBinded = bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected....");
            remoteBinder = (ICommunication) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected....");
            remoteBinder = null;
        }
    };


    public void unBindServiceClick(View view) {
        if (connection != null) {
            unbindService(connection);
            Log.d(TAG, "call service inner Methods");
        }
    }

    public void callServiceMethod(View view) {
        Log.d(TAG, "call service inner method...");
        remoteBinder.callServiceInnerMethod();
    }
}
