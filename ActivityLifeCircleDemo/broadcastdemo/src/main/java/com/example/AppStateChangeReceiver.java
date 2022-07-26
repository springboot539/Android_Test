package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 软件应用的安装和卸载，目的是为了收集信息
 */
public class AppStateChangeReceiver extends BroadcastReceiver {

    private static final String TAG = "AppStateChangeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
//            应用安装
            Log.d(TAG, "应用已经安装了，他的相关信息是 ： " + intent.getData());
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
//            应用卸载
            Log.d(TAG, "应用已经卸载了，他的相关信息是 ： " + intent.getData());
        }
    }
}
