package com.example.fragmentdeom.viewpage;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.fragmentdeom.R;
import com.example.fragmentdeom.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * [类的说明]
 */
public class ViewPagerActivity extends AppCompatActivity {


    private static final String TAG = "ViewPagerActivity";
    private ViewPager mVp;
    private MyViewPagerAdapter adapter;
    private List<ImageView> mImageView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mVp = findViewById(R.id.vp2);
        initData();
        adapter = new MyViewPagerAdapter(mImageView);
        mVp.setAdapter(adapter);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(ViewPagerActivity.this, "这个是addView"+position+"被殿中的", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.num1);
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.num2);
        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.drawable.num3);
        ImageView imageView4 = new ImageView(this);
        imageView4.setImageResource(R.drawable.num4);
        ImageView imageView5 = new ImageView(this);
        imageView5.setImageResource(R.drawable.num5);
        ImageView imageView6 = new ImageView(this);
        imageView6.setImageResource(R.drawable.num6);
        ImageView imageView7 = new ImageView(this);
        imageView7.setImageResource(R.drawable.num7);
        ImageView imageView8 = new ImageView(this);
        imageView8.setImageResource(R.drawable.num8);
        ImageView imageView9 = new ImageView(this);
        imageView9.setImageResource(R.drawable.num9);
        ImageView imageView10 = new ImageView(this);
        imageView10.setImageResource(R.drawable.num10);

        mImageView = new ArrayList<>();
        mImageView.add(imageView1);
        mImageView.add(imageView2);
        mImageView.add(imageView3);
        mImageView.add(imageView4);
        mImageView.add(imageView5);
        mImageView.add(imageView6);
        mImageView.add(imageView7);
        mImageView.add(imageView8);
        mImageView.add(imageView9);
        mImageView.add(imageView10);
    }

}
