package com.example.recyclerviewtest;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.adapters.MoreTypeAdapter;
import com.example.recyclerviewtest.domain.Datas;
import com.example.recyclerviewtest.domain.MoreTypeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * [类的说明]
 */
public class MoreTypeActivity extends Activity {

    private RecyclerView recyclerView;
    private List<MoreTypeBean> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_type_activity);

        initView();
//        初始化数据
        initData();
//        创建和设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        创建适配器
        MoreTypeAdapter adapter = new MoreTypeAdapter(datas);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        Random random = new Random();
        datas = new ArrayList<>();
        for (int i = 0; i < Datas.icons.length; i++) {
            MoreTypeBean data = new MoreTypeBean();
            data.pic = Datas.icons[i];
            data.type = random.nextInt(3);
            datas.add(data);
        }
    }

    private void initView() {
        recyclerView = findViewById(R.id.more_type_list);
    }
}
