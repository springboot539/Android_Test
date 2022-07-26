package com.example.viewpagedemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagedemo.R;
import com.example.viewpagedemo.adapter.VPFragmengBottomAdapter;
import com.example.viewpagedemo.fragment.ViewPagerUseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * [类的说明]
 */
public class VPFragmengBottomActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llHome;
    private LinearLayout llFind;
    private LinearLayout llMine;
    private ImageView ivHome;
    private ImageView ivFind;
    private ImageView ivMine;
    private TextView tvHome;
    private TextView tvFind;
    private TextView tvMine;
    private ViewPager mVp;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_bottom_fragment);
        initView();
        initData();
        VPFragmengBottomAdapter adapter = new VPFragmengBottomAdapter(getSupportFragmentManager(), fragmentList);
        mVp.setAdapter(adapter);

        onViewPagerSelected(1);
        llHome.setOnClickListener(this);
        llFind.setOnClickListener(this);
        llMine.setOnClickListener(this);
        initListener();
    }


    @Override
    public void onClick(View view) {
        resetBottomState();
        switch (view.getId()) {
            case R.id.ll_home:
                mVp.setCurrentItem(0);
                ivHome.setSelected(true);
                tvHome.setTextColor(Color.RED);
                break;
            case R.id.ll_find:
                mVp.setCurrentItem(1);
                ivFind.setSelected(true);
                tvFind.setTextColor(Color.RED);
                break;
            case R.id.ll_mine:
                mVp.setCurrentItem(2);
                ivMine.setSelected(true);
                tvMine.setTextColor(Color.RED);
                break;
            default:
                break;
        }
    }

    private void resetBottomState() {
        ivHome.setSelected(false);
        tvHome.setTextColor(Color.GRAY);
        ivFind.setSelected(false);
        tvFind.setTextColor(Color.GRAY);
        ivMine.setSelected(false);
        tvMine.setTextColor(Color.GRAY);
    }

    private void initView() {
        llHome = findViewById(R.id.ll_home);
        llFind = findViewById(R.id.ll_find);
        llMine = findViewById(R.id.ll_mine);

        ivHome = findViewById(R.id.iv_home);
        ivFind = findViewById(R.id.iv_find);
        ivMine = findViewById(R.id.iv_mine);

        tvHome = findViewById(R.id.tv_home);
        tvFind = findViewById(R.id.tv_find);
        tvMine = findViewById(R.id.tv_mine);

        mVp = findViewById(R.id.vp);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        ViewPagerUseFragment fragment = ViewPagerUseFragment.newInstance("这是一个新的页面 No1", "");
        ViewPagerUseFragment fragment2 = ViewPagerUseFragment.newInstance("这是一个新的页面 No2", "");
        ViewPagerUseFragment fragment3 = ViewPagerUseFragment.newInstance("这是一个新的页面 No3", "");
        fragmentList.add(fragment);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
    }


    private void initListener() {
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onViewPagerSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void onViewPagerSelected(int position) {
        resetBottomState();
        switch (position) {
            case 0:
                ivHome.setSelected(true);
                tvHome.setTextColor(Color.RED);
                break;
            case 1:
                ivFind.setSelected(true);
                tvFind.setTextColor(Color.RED);
                break;
            case 2:
                ivMine.setSelected(true);
                tvMine.setTextColor(Color.RED);
                break;
            default:
                break;
        }
    }

}
