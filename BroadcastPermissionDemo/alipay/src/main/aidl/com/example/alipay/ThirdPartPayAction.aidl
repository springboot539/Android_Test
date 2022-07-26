
package com.example.alipay;
import com.example.alipay.ThirdPartPayResult;

interface ThirdPartPayAction {


    void requestPay(String orderInfo, float payMoney, ThirdPartPayResult callback);
}