package com.example.fragmentdeom.datapass;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentdeom.fragment.DataPassFragment;
import com.example.fragmentdeom.R;

/**
 * [类的说明]
 */
public class DataPassTestActivit extends AppCompatActivity {

    private static final String TAG = "DataPassTestActivit";
    private TextView mReceiver;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datapass_fragment);

        /**
         * #TODO  这是第一种写法，注意点是，需要使用commitNow()，否则，报fragment为NPE(空指针异常)
         */
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fcv, DataPassFragment.class, null)
//                .commitNow();
//        mReceiver = findViewById(R.id.tv_receiver);
//        Fragment fragment = supportFragmentManager.findFragmentById(R.id.fcv);
//        if (fragment != null) {
//            ((DataPassFragment) fragment).setFragmentDataChangeListener(new DataPassFragment.onFragmentDataChangeListener() {
//                @Override
//                public void onFragmentDataChange(String data) {
//                    mReceiver.setText(data);
//                }
//            });
//        }else{
//            Log.d(TAG,"this is a null Object so come to here");
//        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        DataPassFragment fragment = new DataPassFragment();
        transaction.replace(R.id.fcv,fragment).commit();
        fragment.setFragmentDataChangeListener((data -> {
            if (fragment != null) {
                mReceiver.setText(data);
            }
        }));


    }


    public void setReceiver(String data) {
        mReceiver.setText(data);
    }

    public void passDataByConstruct(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        DataPassFragment dataPassFragment = new DataPassFragment("这个是点击之后更新的内容");
        fragmentTransaction.replace(R.id.fcv, dataPassFragment).commit();
    }

    public void passDataByMethod(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        DataPassFragment fragment = (DataPassFragment) supportFragmentManager.findFragmentById(R.id.fcv);

        if (fragment != null) {
            fragment.setmParam1("通过普通方法传递内容");
        }
    }

    public void passDataByArgument(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        DataPassFragment dataPassFragment = new DataPassFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", "这是通过Argument传递的数据");
        bundle.putString("int_data", "10");
        dataPassFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fcv, dataPassFragment).commit();
    }

    /**
     * 这个是通过接口向Fragment中传递数据
     * @param view
     */
    public void passDataByInterface(View view) {
        if (mDataChangeListener != null) {
            mDataChangeListener.onDataChange("这是通过接口传递的数据");
        }
    }

    private OnDataChangeListener mDataChangeListener;

    public void setOnDataChangeListener(OnDataChangeListener DataChangeListener) {
        this.mDataChangeListener = DataChangeListener;
    }

    public interface OnDataChangeListener {
        void onDataChange(String data);
    }


}
