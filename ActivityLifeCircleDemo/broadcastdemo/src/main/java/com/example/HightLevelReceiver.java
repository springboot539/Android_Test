package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class HightLevelReceiver extends BroadcastReceiver {
    private static final String TAG = "HightLevelReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "action is ===" + intent.getAction());
//        终止往下传播
        abortBroadcast();
//        修改广播内容
        Bundle resultExtras = getResultExtras(true);
        String content = resultExtras.getCharSequence("content").toString();
        Log.d(TAG, "content === " + content);
        resultExtras.putCharSequence("content", "我是修改的内容");
        setResultExtras(resultExtras);
    }
}
