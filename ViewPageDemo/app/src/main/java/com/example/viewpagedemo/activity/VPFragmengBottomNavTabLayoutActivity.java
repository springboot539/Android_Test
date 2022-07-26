package com.example.viewpagedemo.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagedemo.R;
import com.example.viewpagedemo.adapter.VPFragmengBottomAdapter;
import com.example.viewpagedemo.fragment.ViewPagerUseFragment;
import com.example.viewpagedemo.fragment.ViewPagerUseTabLayoutFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * [类的说明]
 */
public class VPFragmengBottomNavTabLayoutActivity extends AppCompatActivity {

    private ViewPager mVp;
    private BottomNavigationView bnv;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_bottomnavtab_fragment);

        mVp = findViewById(R.id.vp);
        bnv = findViewById(R.id.bnv);

        initData();
        VPFragmengBottomAdapter adapter = new VPFragmengBottomAdapter(getSupportFragmentManager(), fragmentList);
        mVp.setAdapter(adapter);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        mVp.setCurrentItem(0);
                        break;
                    case R.id.menu_find:
                        mVp.setCurrentItem(1);
                        break;
                    case R.id.menu_mine:
                        mVp.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(VPFragmengBottomNavTabLayoutActivity.this, "第" + position + "页", Toast.LENGTH_SHORT).show();
                onViewPagerSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        BadgeDrawable badge = bnv.getOrCreateBadge(R.id.menu_mine);
        badge.setNumber(1000);
        badge.setMaxCharacterCount(3);

    }

    private void initData() {
        fragmentList = new ArrayList<>();
        ViewPagerUseTabLayoutFragment fragment = ViewPagerUseTabLayoutFragment.newInstance("主页", "");
        ViewPagerUseFragment fragment2 = ViewPagerUseFragment.newInstance("发现", "");
        ViewPagerUseFragment fragment3 = ViewPagerUseFragment.newInstance("我的", "");
        fragmentList.add(fragment);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
    }


    private void onViewPagerSelected(int position) {
        switch (position) {
            case 0:
                bnv.setSelectedItemId(R.id.menu_home);
                break;
            case 1:
                bnv.setSelectedItemId(R.id.menu_find);
                break;
            case 2:
                bnv.setSelectedItemId(R.id.menu_mine);
                bnv.removeBadge(R.id.menu_mine);
                break;
            default:
                break;
        }
    }
}
