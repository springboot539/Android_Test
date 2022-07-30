package com.example.fragmentdeom;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentdeom.fragment.FragmentPassA;
import com.example.fragmentdeom.fragment.FragmentPassB;

/**
 * [类的说明]
 */
public class FragmentPassBetweenActivity extends AppCompatActivity {

    public FragmentPassA fragmentPassA;
    public FragmentPassB fragmentPassB;

    public FragmentPassA getFragmentPassA(){
        return fragmentPassA;
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_between_fragment);
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransactionA = supportFragmentManager.beginTransaction();
        FragmentTransaction fragmentTransactionB = supportFragmentManager.beginTransaction();
        fragmentPassA = new FragmentPassA();
        fragmentPassB = new FragmentPassB();

        fragmentTransactionA.replace(R.id.fcv_a,fragmentPassA).commit();
        fragmentTransactionB.replace(R.id.fcv_b,fragmentPassB).commit();

    }

}
