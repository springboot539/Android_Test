package com.example.bankservicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.bankservicedemo.actions.interfaces.INormalUserAction;

public class NormalUserActivity extends Activity {

    private static final String TAG = "NormalUserActivity";
    private NormalUserConnection normalUserConnection;
    private boolean isBind;
    private NormalUserAction normalUserAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user);

        doBindService();
    }

    //绑定服务
    private void doBindService() {
        Log.d(TAG, "绑定服务");
        Intent intent = new Intent();
        intent.setAction("com.example.ACTION_NORMAL_USER");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage(this.getPackageName());
        normalUserConnection = new NormalUserConnection();
        isBind = bindService(intent, normalUserConnection, BIND_AUTO_CREATE);
    }

    private class NormalUserConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected..." + name);
            normalUserAction = NormalUserAction.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected..." + name);
        }
    }


    public void saveMoneyClick(View view) {
        Log.d(TAG, "saveMoneyClick");
        try {
            normalUserAction.saveMoney(100.0f);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getMoneyClick(View view) {
        Log.d(TAG, "getMoneyClick");
        try {
            normalUserAction.getMoney();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void loanMoneyClick(View view) {
        Log.d(TAG, "loanMoneyClick");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind && normalUserConnection != null) {
            unbindService(normalUserConnection);
            Log.d(TAG, "解绑了服务");
            normalUserConnection = null;
            isBind = false;
        }
    }
}
