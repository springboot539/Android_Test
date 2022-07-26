package com.example.alipay;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class PayActivity extends Activity {

    private static final String TAG = "PayActivity";
    private boolean isBind;
    private EditText passwordBox;
    private PayService.PayAction playAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
//        因为Activity也要跟服务进行通讯，告诉支付结果，所以也要绑定服务
        doBindService();
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String orderInfo = intent.getStringExtra(Constants.KEY_BILL_INFO);
        float payMoney = intent.getFloatExtra(Constants.KEY_PAY_MONEY, 0f);
        TextView orderInfoTv = findViewById(R.id.order_info_tv);
        orderInfoTv.setText("支付信息：" + orderInfo);
        TextView payMoneyTv = findViewById(R.id.pay_money);
        payMoneyTv.setText("支付金额：" + payMoney + "元");
        passwordBox = findViewById(R.id.pay_password_input);
        Button commitBtn = findViewById(R.id.pay_commit);
        commitBtn.setOnClickListener(v -> {
//            提交点击
            String trim = passwordBox.getText().toString().trim();
            if ("123456".equals(trim) && playAction != null) {
                playAction.pay(payMoney);
                Toast.makeText(this, "支付成功！！", Toast.LENGTH_SHORT).show();
                finish();
                Log.d(TAG, "支付成功了");
            } else {
                Toast.makeText(this, "密码错误！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (playAction != null) {
            playAction.onUserCancel();
        }
    }

    private void doBindService() {
        Intent intent = new Intent(this, PayService.class);

        isBind = bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            playAction = (PayService.PayAction) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            playAction = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind && mConnection != null) {
            isBind = false;
            unbindService(mConnection);
            mConnection = null;
        }
    }
}
