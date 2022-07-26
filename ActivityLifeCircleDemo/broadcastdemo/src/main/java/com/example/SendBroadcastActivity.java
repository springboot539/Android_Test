package com.example;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.sql.SQLOutput;

public class SendBroadcastActivity extends Activity {

    private EditText inputBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        inputBox = findViewById(R.id.be_send_input_box);
    }

    /**
     * 点击玩发送广播之后，这个方法就会被调用
     *
     * @param view
     */
    public void sendBroadcastMsg(View view) {
        System.out.println("已经触发了点击事件");
        String content = inputBox.getText().toString().trim();
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_SEND_MSG);
        intent.putExtra(Constants.KEY_CONTENT, content);
//        发送广播
        sendBroadcast(intent);
        System.out.println("helloworld");
    }

}
