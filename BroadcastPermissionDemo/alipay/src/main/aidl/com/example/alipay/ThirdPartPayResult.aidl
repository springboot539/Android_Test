// ThirdPartPayResult.aidl
package com.example.alipay;

// Declare any non-default types here with import statements

interface ThirdPartPayResult {

    void onPaySuccess();
    void onPayFailed(in int errorCode, in String msg);

}