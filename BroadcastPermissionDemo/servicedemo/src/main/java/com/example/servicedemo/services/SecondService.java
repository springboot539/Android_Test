package com.example.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.servicedemo.interfaces.ICommunication;

public class SecondService extends Service {

    private static final String TAG = "SecondService";

    private class InnerBinder extends Binder implements ICommunication {

        @Override
        public void callServiceInnerMethod() {
            serviceInnerMethod();
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate...." );
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind...." );
        InnerBinder innerBinder = new InnerBinder();
        return innerBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand...." );
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind...." );
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy...." );
        super.onDestroy();
    }

    /**
     * 服务内部方法
     */
    private void serviceInnerMethod(){
        Toast.makeText(this, "内部方法被调用了！", Toast.LENGTH_SHORT).show();
    }
}
