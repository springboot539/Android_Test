package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {
    private static final String TAG = "BootCompleteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action ===" + action);
        Log.d(TAG, "开机完成");
        Toast.makeText(context, "收到开机完成的广播", Toast.LENGTH_SHORT).show();
    }
}
