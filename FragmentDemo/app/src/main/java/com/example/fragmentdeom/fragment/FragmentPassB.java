package com.example.fragmentdeom.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentdeom.FragmentPassBetweenActivity;
import com.example.fragmentdeom.R;

import org.jetbrains.annotations.NotNull;

public class FragmentPassB extends Fragment {

    private TextView mTvReceiver;
    private Button mBtnPass;
    private String mData;
    private Button mBtnPassInter;

    public void setData(String data) {
        this.mData = data;
        mTvReceiver.setText(mData);
    }

    public FragmentPassB() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvReceiver = view.findViewById(R.id.tv_b_receiver);
        mBtnPass = view.findViewById(R.id.btn_pass_b);
        mBtnPassInter = view.findViewById(R.id.btn_pass_b_by_interface);
//        mTvReceiver.setText(mData);
        mBtnPass.setOnClickListener(v -> {
//           向FragmentA传递数据
            ((FragmentPassBetweenActivity) getActivity())
                    .fragmentPassA.setData("这是从FragmentB向A传过来的数据");
        });

        mBtnPassInter.setOnClickListener(v -> {
//            通过接口向FragmentA中传递数据
            if (listener != null) {
                listener.onFragmentBChange("这是通过接口向FragmentA中传递的数据");
            }
        });

        ((FragmentPassBetweenActivity) getActivity())
                .fragmentPassA
                .setOnFragmentAChangeListener(data -> {
                    this.mData = data;
                    mTvReceiver.setText(data);
                });
    }

    private OnFragmentBChangeListener listener;

    public void setOnFragmentBChangeListener(OnFragmentBChangeListener listener){
        this.listener = listener;
    }

    public interface OnFragmentBChangeListener{
        void onFragmentBChange(String data);
    }
}