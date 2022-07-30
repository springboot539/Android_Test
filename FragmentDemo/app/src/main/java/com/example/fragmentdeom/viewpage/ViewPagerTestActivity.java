package com.example.fragmentdeom.viewpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

;import com.example.fragmentdeom.R;
import com.example.fragmentdeom.ViewPagerFragmentActivity;

/**
 * [类的说明]
 */
public class ViewPagerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage_test_fragment);
    }

    public void toViewPager(View view) {
        startActivity(new Intent(this,ViewPagerActivity.class));
    }

    public void toViewPagerFragment(View view) {
        startActivity(new Intent(this, ViewPagerFragmentActivity.class));
    }
}
