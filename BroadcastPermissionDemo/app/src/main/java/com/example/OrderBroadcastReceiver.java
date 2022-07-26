package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OrderBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "OrderBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG,"action is ===" + intent.getAction());
    }
}
