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

public class FragmentPassA extends Fragment {

    private TextView mTvReceiver;
    private Button mBtnPass;
    private String mData;
    private Button mBtnPassInter;

    public void setData(String data) {
        this.mData = data;
        mTvReceiver.setText(mData);
    }

    public FragmentPassA() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvReceiver = view.findViewById(R.id.tv_a_receiver);
        mBtnPass = view.findViewById(R.id.btn_pass_a);
        mBtnPassInter = view.findViewById(R.id.btn_pass_a_by_interface);
        mTvReceiver.setText(mData);
        mBtnPass.setOnClickListener(v -> {
//           向FragmentA传递数据

            Fragment fragmentB = ((FragmentPassBetweenActivity) getActivity())
                    .getSupportFragmentManager()
                    .findFragmentById(R.id.fcv_b);
            if (fragmentB != null) {
                ((FragmentPassB) fragmentB).setData("这是从FragmentA传递过来的数据!");
            }
        });

        mBtnPassInter.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFragmentAChange("这是通过Interface来自FragmentA的数据");
            }
        });

        ((FragmentPassBetweenActivity) getActivity()).fragmentPassB.setOnFragmentBChangeListener(data -> {
            mData = data;
            mTvReceiver.setText(mData);
        });


    }

    private OnFragmentAChangeListener listener;

    public void setOnFragmentAChangeListener(OnFragmentAChangeListener listener) {
        this.listener = listener;
    }


    public interface OnFragmentAChangeListener {
        void onFragmentAChange(String data);
    }
}