package com.example.fragmentdeom;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentdeom.fragment.DynamicFragment;

/**
 * [类的说明]
 */
public class DynamicFragmentActivity extends AppCompatActivity {

    private static final String TAG = "DynamicFragmentActivity";

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);

        Log.d(TAG, "onCreate : saveInstance " + savedInstanceState);
        if (savedInstanceState == null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString(DynamicFragment.ARG_PARAM1, "这是要给一个parameter是 ->动态Fragment");
            fragmentTransaction.add(R.id.fcv, DynamicFragment.class, bundle)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }


    }
}
