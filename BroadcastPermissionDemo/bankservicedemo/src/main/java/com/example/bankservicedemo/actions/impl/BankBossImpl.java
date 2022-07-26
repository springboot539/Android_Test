package com.example.bankservicedemo.actions.impl;

import android.os.Binder;
import android.util.Log;

import com.example.bankservicedemo.actions.interfaces.IBankBossAction;

public class BankBossImpl extends Binder implements IBankBossAction {
    private static final String TAG = "BankBossImpl";

    @Override
    public void modifyUserAccountMoney(float money) {
        Log.d(TAG, "modifyuseraccountmoney  -> 100000");
    }


    @Override
    public void checkUserCredit() {
        Log.d(TAG, "checkUserCredit ..." + 790);
    }

    @Override
    public void freezeUserAccount() {
        Log.d(TAG, "freezeuseraccount ... -> 0");
    }

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
