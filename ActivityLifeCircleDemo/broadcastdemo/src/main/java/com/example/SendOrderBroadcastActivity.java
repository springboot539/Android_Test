package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class SendOrderBroadcastActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order_broadcast);
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_ORDER_BROADCAST_TEST);
        intent.putExtra("intKey", 100);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("content","我是被发送的广播内容....");
        sendOrderedBroadcast(intent, Manifest.permission.ORDER_PERMISSION,null,null,Activity.RESULT_OK,null,bundle);
    }
}
