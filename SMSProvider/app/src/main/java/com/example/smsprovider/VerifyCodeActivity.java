package com.example.smsprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyCodeActivity extends Activity {

    private static final String TAG = "VerifyCodeActivity";
    private EditText phoneNumEt;
    private EditText verifyCodeEt;
    private Button countDownBtn;
    private Button commitBtn;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int MATCH_CODE = 1;

    static {
        uriMatcher.addURI("sms", "raw/#", MATCH_CODE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifycode);
        initView();
        initEvent();
        //注册短信内容提供者的观察者
        Uri uri = Uri.parse("content://sms/");
        getContentResolver().registerContentObserver(uri, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, @Nullable Uri uri) {
                if (uriMatcher.match(uri) == MATCH_CODE) {
                    Log.d(TAG, "uri ---- >" + uri);
//                    Cursor query = getContentResolver().query(uri,  new String[]{"body"}, null, null, null);
////                    String[] columnNames = query.getColumnNames();
////                    while (query.moveToNext()) {
////                        for (String columnName : columnNames) {
////                            String result = query.getString(query.getColumnIndex(columnName));
////                            Log.d(TAG, columnName + " ---- >" + result);
////                        }
////                    }
//                    if (query.moveToNext()){
//                        String body = query.getString(0);
//                        Log.d(TAG,"body --- > " + body);
//                        handlerBody(body);
//                    }
//                    query.close();
                    Cursor query = getContentResolver().query(uri, null, null, null, null);
                    String[] columnNames = query.getColumnNames();
                    while (query.moveToNext()) {
                        for (String columnName : columnNames) {
                            Log.d(TAG,columnName + " --- >" + query.getString(query.getColumnIndex(columnName)));
                        }
                    }
                }
            }
        });
    }
    private void handlerBody(String body) {
        if (!TextUtils.isEmpty(body) && body.startsWith("【小鹏汽车】")) {
            Matcher matcher = Pattern.compile("(?<![0-9])([0-9]{5})(?![0-9])]").matcher(body);
            boolean b = matcher.find();
            if (b) {
                Log.d(TAG, "verifyCode --- >" + matcher.group());
                verifyCodeEt.setText(matcher.group());
            }
        }

    }

    private void initEvent() {
        countDownBtn.setOnClickListener(v -> {
            String phoneNumStr = phoneNumEt.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNumStr)) {
                Toast.makeText(this, "手机号码不可以为空！", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        commitBtn.setOnClickListener(v -> {
            String phoneNum = phoneNumEt.getText().toString().trim();
            String verifyNum = verifyCodeEt.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(verifyNum)) {
                Toast.makeText(this, "手机号和验证码不可以为空", Toast.LENGTH_SHORT).show();
                return;
            }

        });
    }


    private void initView() {
        phoneNumEt = findViewById(R.id.phone_num_et);
        verifyCodeEt = findViewById(R.id.verify_code_et);
        countDownBtn = findViewById(R.id.count_down_btn);
        commitBtn = findViewById(R.id.commit_btn);
    }


}
