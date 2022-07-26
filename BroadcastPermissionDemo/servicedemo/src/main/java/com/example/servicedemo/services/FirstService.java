package com.example.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.servicedemo.interfaces.ICommunication;

public class FirstService extends Service {

    private static final String TAG = "FirstService";

    private class InnerBinder extends Binder implements ICommunication {
        @Override
        public void callServiceInnerMethod() {
            sayHello();
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind ....");
        return new InnerBinder();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate ....");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestory ....");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommadn ....");
        return super.onStartCommand(intent, flags, startId);
    }


    private void sayHello() {
        Toast.makeText(this, "hello!", Toast.LENGTH_SHORT).show();
    }
}

