package com.example.fragmentdeom.modify;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentdeom.R;
import com.example.fragmentdeom.fragment.DynamicFragment;

/**
 * [类的说明]
 */
public class ModifyFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_fragment);
    }

    public void addFragment1(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fcv, DynamicFragment.class, null).commit();

    }

    public void addFragment2(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fcv, DynamicFragment.class, null,"myFragment")
                .addToBackStack("myBackStack")
                .setReorderingAllowed(true)
                .commit();

    }

    public void addFragment3(View view) {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        DynamicFragment fragment = new DynamicFragment();
        fragmentTransaction.add(R.id.fcv, fragment)
                .addToBackStack("myBackStack")
                .setReorderingAllowed(true)
                .commit();
    }

    public void findFragmentById(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragmentById = supportFragmentManager.findFragmentById(R.id.fcv);
        if (fragmentById != null) {
            Toast.makeText(this, "找到了Fragment " + fragmentById.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "没有找到了Fragment " , Toast.LENGTH_SHORT).show();
        }

    }

    public void findFragmentByTag(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragmentById = supportFragmentManager.findFragmentByTag("myFragment");
        if (fragmentById != null) {
            Toast.makeText(this, "找到了Fragment " + fragmentById.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "没有找到了Fragment " , Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        finish();
    }

    public void removeFragment(View view) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Fragment fragment = supportFragmentManager.findFragmentByTag("myFragment");
        if (fragment != null) {
            Toast.makeText(this, "找到了Fragment " + fragment.toString(), Toast.LENGTH_SHORT).show();
            fragmentTransaction.remove(fragment).commit();
        } else {
            Toast.makeText(this, "没有找到了Fragment " , Toast.LENGTH_SHORT).show();
        }
    }
}
