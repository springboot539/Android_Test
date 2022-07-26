package com.example.activitylifecircledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String MESS_RECORD = "mess_record";
    public static final String RECORD_KEY = "msg";
    private EditText contentBox;
    private Button sendBn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        sharedPreferences = this.getSharedPreferences(MESS_RECORD, MODE_PRIVATE);
        String record = sharedPreferences.getString(RECORD_KEY, null);
        if (!TextUtils.isEmpty(record)) {
            contentBox.setText(record);
        }
    }

    private void initListener() {
        sendBn.setOnClickListener(v -> {
            String content = contentBox.getText().toString().trim();
            Log.d(TAG, "发送短信、、、、");
            if (TextUtils.isEmpty(content)) {
                Toast.makeText(this, "请输入要发送的内容；", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    private void initView() {
        contentBox = findViewById(R.id.content);
        sendBn = findViewById(R.id.send);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        保存数据，存储到SharePreference
        String content = contentBox.getText().toString().trim();
//        注意此处写错了因为少写了一个！ 所以进入不了if中间的代码

        if (!TextUtils.isEmpty(content)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(RECORD_KEY, content);
            edit.commit();
        }
    }
}