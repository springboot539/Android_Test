package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView batteryLevelText;
    private BatteryLevleReceiver batteryLevelReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerBatteryReceiver();
    }

    private void initView() {
        batteryLevelText = findViewById(R.id.battery_level);
    }

    private void registerBatteryReceiver() {
        //        负责监听电量变化
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        batteryLevelReceiver = new BatteryLevleReceiver();
        this.registerReceiver(batteryLevelReceiver, intentFilter);
    }

    /**
     * 创建广播接收者
     */
    private class BatteryLevleReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                Log.d(TAG, "action is =====" + action);
                int currentLevel = intent.getIntExtra("level", 0);
                Log.d(TAG, "当前电量 ==== " + currentLevel);
                if (batteryLevelText != null) {
                    batteryLevelText.setText("当前的电量为：" + currentLevel);
                }
                int maxLevel = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);

//            拿到当前的电量，再除以最大值
                float percent = currentLevel * 1.0f / maxLevel;
                Log.d(TAG, "当前电量的百分比事" + percent + "====");
            } else if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
                Log.d(TAG, "USB连接线已经连接" + "====");

            } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
                Log.d(TAG, "USB连接线未连接" + "====");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        q取消广播，否则会造成内存泄露
        if (batteryLevelReceiver != null) {
            this.unregisterReceiver(batteryLevelReceiver);
        }
    }
}