package com.example.bankservicedemo.actions.impl;

import android.os.RemoteException;
import android.util.Log;

import com.example.bankservicedemo.NormalUserAction;

public class NormalUserAIDLActionImpl extends NormalUserAction.Stub {


    private static final String TAG = "NormalUserAIDLAction";

    @Override
    public void saveMoney(float money) throws RemoteException {
        Log.d(TAG,"save money...");

    }

    @Override
    public float getMoney() throws RemoteException {
        Log.d(TAG,"");
        return 100f;
    }
}
