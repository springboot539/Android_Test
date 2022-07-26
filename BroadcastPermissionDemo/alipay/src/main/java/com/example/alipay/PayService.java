package com.example.alipay;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class PayService extends Service {
    private static final String TAG = "PayService";
    private ThirdPartPlayImpl thirdPartPlay;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        String action = intent.getAction();
        Log.d(TAG, "绑定成功！！");
        if (action != null && "com.example.alipay.THIRD_PART_PAY".equals(action)) {
            //说明这个是第三方要求我们支付宝进行支付
            thirdPartPlay = new ThirdPartPlayImpl();
            return thirdPartPlay;
        }
        return new PayAction();
    }

    public class PayAction extends Binder {

        public void pay(float payMoney) {
//            支付的方法
            Log.d(TAG, "pay money is === >" + payMoney);
            if (thirdPartPlay != null) {
                thirdPartPlay.paySuccess();
            }
        }

        public void onUserCancel() {
//            用户点击界面上的取消、退出
            if (thirdPartPlay != null) {
                thirdPartPlay.payFailed(1, "User Cancel Pay!");
            }
        }
    }

    private class ThirdPartPlayImpl extends ThirdPartPayAction.Stub {

        private ThirdPartPayResult callback;

        @Override
        public void requestPay(String orderInfo, float payMoney, ThirdPartPayResult callback) throws RemoteException {

            this.callback = callback;
            //第三方发起请求，打开一个支付界面
            Intent intent = new Intent();
            intent.setClass(PayService.this, PayActivity.class);
            intent.putExtra(Constants.KEY_BILL_INFO, orderInfo);
            intent.putExtra(Constants.KEY_PAY_MONEY, payMoney);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        public void paySuccess() {
            if (callback != null) {
                try {
                    callback.onPaySuccess();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public void payFailed(int errorCode, String errorMsg) {
            if (callback != null) {
                try {
                    callback.onPayFailed(errorCode, errorMsg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
