package com.example.viewpagedemo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagedemo.R;
import com.example.viewpagedemo.adapter.ViewPagerUseFragmentAdapter;
import com.example.viewpagedemo.fragment.ViewPagerUseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * [类的说明]
 */
public class ViewPagerTestActivity extends AppCompatActivity {

    private ViewPager mVp;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpage);
        mVp = findViewById(R.id.vp);
        initData();
        ViewPagerUseFragmentAdapter adapter = new ViewPagerUseFragmentAdapter(getSupportFragmentManager(), fragmentList);
        mVp.setAdapter(adapter);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        ViewPagerUseFragment fragment = ViewPagerUseFragment.newInstance("这个是第一个界面内文字", "");
        ViewPagerUseFragment fragment2 = ViewPagerUseFragment.newInstance("这个是第二个界面内文字", "");
        ViewPagerUseFragment fragment3 = ViewPagerUseFragment.newInstance("这个是第三个界面内文字", "");
        fragmentList.add(fragment);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
    }
}
