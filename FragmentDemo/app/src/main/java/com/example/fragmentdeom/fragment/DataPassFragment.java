package com.example.fragmentdeom.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentdeom.R;
import com.example.fragmentdeom.datapass.DataPassTestActivit;

import org.jetbrains.annotations.NotNull;


public class DataPassFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mTv_content;
    private String mdata;
    private int mIntData1;
    private int mIntData2;
    private Button mBtn;
    private Button mBtn2;

    public DataPassFragment(String data) {
        // Required empty public constructor
        mParam1 = data;
    }

    public DataPassFragment() {
        // Required empty public constructor
    }

    public void setmParam1(String data) {
        this.mParam1 = data;
        if (!TextUtils.isEmpty(mParam1)) {
            mTv_content.setText(mParam1);
        }
    }

    // TODO: Rename and change types and number of parameters
    public static DataPassFragment newInstance(String param1, String param2) {
        DataPassFragment fragment = new DataPassFragment();
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

            mdata = getArguments().getString("data").toString();
            mIntData2 = Integer.parseInt(getArguments().getString("int_data"));
            mIntData1 = getArguments().getInt("int_data");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_pass, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTv_content = view.findViewById(R.id.tv_content);
//        if (!TextUtils.isEmpty(mParam1)) {
//            mTv_content.setText(mParam1);
//        }
        if (!TextUtils.isEmpty(mdata)) {
            mTv_content.setText(mdata + "整数类型的值是: " + mIntData1 + ".... " + mIntData2);
        }
        ((DataPassTestActivit) getActivity()).setOnDataChangeListener(new DataPassTestActivit.OnDataChangeListener() {
            @Override
            public void onDataChange(String data) {
                if (!TextUtils.isEmpty(data)) {
                    mTv_content.setText(data);
                }
            }
        });

        mBtn = view.findViewById(R.id.btn_pass_data_by_get_activity);
        mBtn.setOnClickListener(view1 -> {
            ((DataPassTestActivit) getActivity()).setReceiver("这是从Fragment中发送出去的数据");
        });

        mBtn2 = view.findViewById(R.id.btn_pass_data_by_interface);
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onFragmentDataChange("这是从Fragment中传递出去" + "\n" + "的数据，如果正常的话，将会在" +
                            "\n" + "Activity中看到");
                }
            }
        });
    }

    private onFragmentDataChangeListener listener;

    public void setFragmentDataChangeListener(onFragmentDataChangeListener listener) {
        this.listener = listener;
    }

    public interface onFragmentDataChangeListener {
        void onFragmentDataChange(String data);
    }
}