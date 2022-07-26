package com.example.viewpagedemo.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagedemo.R;
import com.example.viewpagedemo.activity.VPFragmengBottomNavTabLayoutActivity;
import com.example.viewpagedemo.adapter.VPFragmengBottomTabLayoutAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerUseTabLayoutFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private TextView mTvContent;
    private ViewPager mVPFrag;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private TabLayout tab;

    public ViewPagerUseTabLayoutFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static ViewPagerUseTabLayoutFragment newInstance(String param1, String param2) {
        ViewPagerUseTabLayoutFragment fragment = new ViewPagerUseTabLayoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_page_use, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVPFrag = view.findViewById(R.id.vp_frag);
        tab = view.findViewById(R.id.tab);
        initData();

        VPFragmengBottomTabLayoutAdapter adapter = new VPFragmengBottomTabLayoutAdapter(
                getChildFragmentManager()
                , fragmentList
                , titleList);
        mVPFrag.setAdapter(adapter);
        tab.setupWithViewPager(mVPFrag);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        ViewPagerUseFragment fragment = ViewPagerUseFragment.newInstance("春", "");
        ViewPagerUseFragment fragment2 = ViewPagerUseFragment.newInstance("夏", "");
        ViewPagerUseFragment fragment3 = ViewPagerUseFragment.newInstance("秋", "");
        ViewPagerUseFragment fragment4 = ViewPagerUseFragment.newInstance("冬", "");
        ViewPagerUseFragment fragment5 = ViewPagerUseFragment.newInstance("阴", "");
        ViewPagerUseFragment fragment6 = ViewPagerUseFragment.newInstance("晴", "");
        ViewPagerUseFragment fragment7 = ViewPagerUseFragment.newInstance("园", "");
        ViewPagerUseFragment fragment8 = ViewPagerUseFragment.newInstance("缺", "");
        fragmentList.add(fragment);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
        fragmentList.add(fragment6);
        fragmentList.add(fragment7);
        fragmentList.add(fragment8);

        String s = "春";
        String s2 = "夏";
        String s3 = "秋";
        String s4 = "冬";
        String s5 = "阴";
        String s6 = "晴";
        String s7 = "园";
        String s8 = "缺";
        titleList.add(s);
        titleList.add(s2);
        titleList.add(s3);
        titleList.add(s4);
        titleList.add(s5);
        titleList.add(s6);
        titleList.add(s7);
        titleList.add(s8);
    }
}