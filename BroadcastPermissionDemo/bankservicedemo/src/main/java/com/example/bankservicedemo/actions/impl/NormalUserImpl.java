package com.example.bankservicedemo.actions.impl;

import android.os.Binder;
import android.util.Log;

import com.example.bankservicedemo.actions.interfaces.INormalUserAction;

public class NormalUserImpl extends Binder implements INormalUserAction {
    private static final String TAG = "NormalUserImpl";

    @Override
    public void saveMoney(float money) {
        Log.d(TAG, "save moeny ..." + money);
    }

    @Override
    public float getMoney() {
        Log.d(TAG, "get moeny ..." + 100.00);
        return (float) 100.00;
    }

    @Override
    public float loanMoeny() {
        Log.d(TAG, "loan moeny ..." + 100.00f);
        return 100.00f;
    }
}
