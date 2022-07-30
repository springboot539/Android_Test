package com.example.fragmentdeom.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentdeom.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaticFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tvLike;
    private RadioButton rbLike;
    private RatingBar rbStar;
    private RadioButton rbDisLike;

    public StaticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StaticFragment newInstance(String param1, String param2) {
        StaticFragment fragment = new StaticFragment();
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
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        绑定控件
        tvLike = view.findViewById(R.id.tv_like);
        rbLike = view.findViewById(R.id.rb_like);
        rbDisLike = view.findViewById(R.id.rb_disLike);
        rbStar = view.findViewById(R.id.rb_star);

//        点击
        rbLike.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                tvLike.setText("app喜欢");
            }
        });
        rbDisLike.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b){
                tvLike.setText("app不喜欢");
            }
        }));
        rbStar.setOnRatingBarChangeListener(((ratingBar, v, b) -> {
            if (b){
                Toast.makeText(getActivity(), "评分是： "+v+"分！", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}