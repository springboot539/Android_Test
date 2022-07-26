package com.example.sobclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alipay.ThirdPartPayAction;
import com.example.alipay.ThirdPartPayResult;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button buySobBtn;
    private TextView sobCountTv;
    private AlipayConnection alipayConnection;
    private boolean isBind;
    private ThirdPartPayAction thirdPartPayAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        绑定支付宝的服务，在现实开发中动作是由支付宝的SDK完成的
        bindAlipayService();

        initView();
        setListener();
    }

    private void bindAlipayService() {
        Intent intent = new Intent();
        intent.setAction("com.example.alipay.THIRD_PART_PAY");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.example.alipay");
        alipayConnection = new AlipayConnection();
        isBind = bindService(intent, alipayConnection, BIND_AUTO_CREATE);

    }

    private class AlipayConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected ..." + service);
            thirdPartPayAction = ThirdPartPayAction.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected ..." + name);
        }
    }


    private void setListener() {
        buySobBtn.setOnClickListener(v -> {
            //TODO 进行充值
            try {
                if (thirdPartPayAction != null) {
                    thirdPartPayAction.requestPay("充值100sob", 100.00f, new PayCallBack());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    private class PayCallBack extends ThirdPartPayResult.Stub {

        @Override
        public void onPaySuccess() throws RemoteException {
//            支付成功，修改UI上面的内容，实际上修改的是服务器上数据库
            sobCountTv.setText("100");
            Toast.makeText(MainActivity.this, "充值成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPayFailed(int errorCode, String msg) throws RemoteException {
            Log.d(TAG, "error code is ==== > " + errorCode + "error msg === > " + msg);
            Toast.makeText(MainActivity.this, "充值失败了！！！", Toast.LENGTH_SHORT).show();
        }
    }


    private void initView() {
        sobCountTv = findViewById(R.id.sob_count_tv);
        buySobBtn = findViewById(R.id.buy_sob_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind && alipayConnection != null) {
            Log.d(TAG, "这里解绑");
            unbindService(alipayConnection);
            alipayConnection = null;
            isBind = false;
        }
    }
}