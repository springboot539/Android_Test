package com.example.fragmentdeom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.fragmentdeom.adapter.MyFragmentVPAdapter;
import com.example.fragmentdeom.fragment.TestTmpFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentActivity extends AppCompatActivity {

    private ViewPager mvp;

    private MyFragmentVPAdapter adapter;
    private List<Fragment> mFragmentList;
    private FragmentManager supportFragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_fragment);

        mvp = findViewById(R.id.vp);
        initData();
        adapter = new MyFragmentVPAdapter(getSupportFragmentManager(), mFragmentList);
        mvp.setAdapter(adapter);
    }

    private void initData() {
        mFragmentList = new ArrayList<>();
        TestTmpFragment fragment = TestTmpFragment.newInstance("这是fargment1", "");
        TestTmpFragment fragment2 = TestTmpFragment.newInstance("这是fargment2", "");
        TestTmpFragment fragment3 = TestTmpFragment.newInstance("这是fargment3", "");
        mFragmentList.add(fragment);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
    }
}