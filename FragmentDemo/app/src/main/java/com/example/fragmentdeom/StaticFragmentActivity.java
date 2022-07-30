package com.example.fragmentdeom;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * [类的说明]
 */
public class StaticFragmentActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_fragment);
    }

    public void back(View view) {
        finish();
    }
}
