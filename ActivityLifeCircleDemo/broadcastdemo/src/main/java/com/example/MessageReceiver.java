package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {
    private static final String TAG = "MessageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("到这里了嘛");
        String action = intent.getAction();
        Log.d(TAG, "action  is === " + action);
        System.out.println("action  is === " + action);
        String content = intent.getStringExtra(Constants.KEY_CONTENT);
        Log.d(TAG, "content  is === " + content);
        System.out.println("content is ====" + content);
    }
}
