package com.example.fragmentdeom.Navi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentdeom.FragmentBottomNaviActivity;
import com.example.fragmentdeom.R;

/**
 * [类的说明]
 */
public class FragmentNaviTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_test_fragment);
    }

    public void toFragmentNormalBottom(View view) {
        startActivity(new Intent(this, FragmentBottomNaviActivity.class));
    }
}
