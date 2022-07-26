package com.example.emptyprofortest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String new1() {
        String S = "WTF";
        return S;
    }

    public String new2(){
        String S = "OHOHOH";
        return S;
    }
    
    public String new3(){
        String S3 = "这个从Git上编辑得到的效果";
        return S3;
    }
}
