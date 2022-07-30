package com.example.fragmentdeom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentdeom.fragment.TestTmpFragment;

/**
 * [类的说明]
 */
public class FragmentBottomNaviActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llHome;
    private LinearLayout llFind;
    private LinearLayout llMine;
    private ImageView ivHome;
    private ImageView ivFind;
    private ImageView ivMine;
    private TextView tvHome;
    private TextView tvFind;
    private TextView tvMine;
    private FragmentManager supportFragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navi_fragment);
        initView();
        initEvent();
    }

    /**
     * 添加Fragment
     */
    private void initEvent() {
        supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = supportFragmentManager.beginTransaction();
        TestTmpFragment fragment = TestTmpFragment.newInstance("这是首页", "");
        fragmentTransaction.replace(R.id.fcv_fragment, fragment).commit();
        ivHome.setSelected(true);
        tvHome.setTextColor(Color.RED);

        llHome.setOnClickListener(this);
        llFind.setOnClickListener(this);
        llMine.setOnClickListener(this);
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
    }

    private void resetBottomState() {
        ivHome.setSelected(false);
        tvHome.setTextColor(Color.GRAY);
        ivFind.setSelected(false);
        tvFind.setTextColor(Color.GRAY);
        ivMine.setSelected(false);
        tvMine.setTextColor(Color.GRAY);
    }

    @Override
    public void onClick(View view) {
        resetBottomState();
        switch (view.getId()) {
            case R.id.ll_home:
                supportFragmentManager = getSupportFragmentManager();
                fragmentTransaction = supportFragmentManager.beginTransaction();
                TestTmpFragment fragment = TestTmpFragment.newInstance("这是首页", "");
                fragmentTransaction.replace(R.id.fcv_fragment, fragment).commit();
                ivHome.setSelected(true);
                tvHome.setTextColor(Color.RED);
                break;
            case R.id.ll_find:
                supportFragmentManager = getSupportFragmentManager();
                fragmentTransaction = supportFragmentManager.beginTransaction();
                TestTmpFragment findFragment = TestTmpFragment.newInstance("这是发现页面", "");
                fragmentTransaction.replace(R.id.fcv_fragment, findFragment).commit();
                ivFind.setSelected(true);
                tvFind.setTextColor(Color.RED);
                break;
            case R.id.ll_mine:
                supportFragmentManager = getSupportFragmentManager();
                fragmentTransaction = supportFragmentManager.beginTransaction();
                TestTmpFragment mineFragment = TestTmpFragment.newInstance("这是个人主页", "");
                fragmentTransaction.replace(R.id.fcv_fragment, mineFragment).commit();
                ivMine.setSelected(true);
                tvMine.setTextColor(Color.RED);
                break;
            default:
                break;
        }
    }
}
