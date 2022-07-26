package com.example.viewpagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.viewpagedemo.activity.VPFragmengBottomActivity;
import com.example.viewpagedemo.activity.VPFragmengBottomNavActivity;
import com.example.viewpagedemo.activity.ViewPagerTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpToViewPage(View view) {
        startActivity(new Intent(this, ViewPagerTestActivity.class));
    }

    public void jumpToViewPagerFragmentBottom(View view) {
        startActivity(new Intent(this, VPFragmengBottomActivity.class));
    }

    public void jumpToViewPagerFragmentBottomNav(View view) {
        startActivity(new Intent(this, VPFragmengBottomNavActivity.class));
    }
}