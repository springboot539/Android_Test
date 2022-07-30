package com.example.fragmentdeom.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * [类的说明]
 */
public class MyFragmentVPAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragment;

    public MyFragmentVPAdapter(@NonNull @NotNull FragmentManager fm, List<Fragment> mFragment) {
        super(fm);
        this.mFragment = mFragment;
    }

    public MyFragmentVPAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return mFragment == null ? null : mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment == null ? 0 : mFragment.size();
    }
}
