package com.example.bankservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.bankservicedemo.actions.impl.BankBossImpl;
import com.example.bankservicedemo.actions.impl.BankWorkerImpl;
import com.example.bankservicedemo.actions.impl.NormalUserAIDLActionImpl;
import com.example.bankservicedemo.actions.impl.NormalUserImpl;

public class BankService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            if ("com.example.ACTION_NORMAL_USER".equals(action)) {
                return new NormalUserAIDLActionImpl();
            } else if ("com.example.ACTION_BANK_WORKER".equals(action)) {
                return new BankWorkerImpl();
            } else if ("com.example.ACTION_BANK_BOSS".equals(action)) {
                return new BankBossImpl();
            }
        }
        return null;
    }
}
