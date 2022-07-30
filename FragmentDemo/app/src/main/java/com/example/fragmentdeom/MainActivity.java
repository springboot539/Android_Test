package com.example.fragmentdeom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.fragmentdeom.Navi.FragmentNaviTestActivity;
import com.example.fragmentdeom.datapass.DataPassTestActivit;
import com.example.fragmentdeom.modify.ModifyFragmentActivity;
import com.example.fragmentdeom.viewpage.ViewPagerTestActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpToStaticFragment(View view) {
        startActivity(new Intent(this, StaticFragmentActivity.class));
    }

    public void jumpToDynamicFragment(View view) {
        startActivity(new Intent(this, DynamicFragmentActivity.class));
    }

    public void jumpToFragmentModify(View view) {
        startActivity(new Intent(this, ModifyFragmentActivity.class));
    }

    public void jumpToFragmentDataPass(View view) {
        startActivity(new Intent(this, DataPassTestActivit.class));

    }

    public void jumpToFragmentDataPassByActivity(View view) {
        startActivity(new Intent(this, FragmentPassBetweenActivity.class));
    }

    public void jumpToNormalNaviActivity(View view) {
        startActivity(new Intent(this, FragmentNaviTestActivity.class));
    }

    public void jumpToViewPager(View view) {
        startActivity(new Intent(this, ViewPagerTestActivity.class));
    }
}