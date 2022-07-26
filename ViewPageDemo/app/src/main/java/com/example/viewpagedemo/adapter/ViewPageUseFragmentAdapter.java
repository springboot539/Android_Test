package com.example.viewpagedemo.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * [类的说明]
 */
public class ViewPageUseFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public ViewPageUseFragmentAdapter(@NonNull @NotNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList == null ? null : fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}
