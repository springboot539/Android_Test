package com.example.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recyclerviewtest.adapters.GridViewAdapter;
import com.example.recyclerviewtest.adapters.ListViewAdapter;
import com.example.recyclerviewtest.adapters.RecyclerViewBaseAdapter;
import com.example.recyclerviewtest.adapters.StaggeredViewAdapter;
import com.example.recyclerviewtest.domain.Datas;
import com.example.recyclerviewtest.domain.ItemBean;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ItemBean> mData;
    private RecyclerViewBaseAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle_list);
        refreshLayout = findViewById(R.id.refresh_layout);
        initData();
        showList(true, false);

//        处理下拉刷新事件
        handlerDownPullUpdate();
    }

    private void handlerDownPullUpdate() {
        refreshLayout.setColorSchemeResources(R.color.design_default_color_primary, R.color.design_default_color_primary_dark, R.color.black);
        refreshLayout.setEnabled(true);
        refreshLayout.setOnRefreshListener(() -> {
            //                当我们在顶部下拉的时候，这个方法就可以被触发
//                但是是在MainThread中，不可以执行耗时操作
//                新开一个新城去获取数据
//                添加数据
            ItemBean data = new ItemBean();
            data.setTitle("我是新添加的数据.... " + new Random().nextInt() + "　");
            data.setIcon(R.mipmap.num10);
            mData.add(data);
//                更新UI
            new Handler().postDelayed(() -> {
//                        一是停止刷新,二是更新列表
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }, 3000);
//                就是这样的生活 我感到十分的开心 我喜欢这样的日子 我要开心的做一个程序员感到快乐感到充实~

        });
    }

    private void initListener() {
        adapter.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                这里处理点击事件，该干嘛就干嘛
                Toast.makeText(MainActivity.this, "您点击的是 ： " + position + "位置的内容", Toast.LENGTH_SHORT).show();
            }
        });

//        这里面处理加载更多
        if (adapter instanceof ListViewAdapter) {
            ((ListViewAdapter) adapter).setOnRefreshListener(new ListViewAdapter.onRefreschListener() {
                @Override
                public void onUpPullRefresh(final ListViewAdapter.LoadMoreHolder loadMoreHolder) {
//                    去加载更多的数据,需要在子线程中完成
//                    添加数据

//                更新UI
                    new Handler().postDelayed(() -> {
//                        一是停止刷新,二是更新列表
                        Random random = new Random();
                        if (random.nextInt() % 2 == 0) {
                            ItemBean data = new ItemBean();
                            data.setTitle("我是新添加的数据.... " + new Random().nextInt() + "　");
                            data.setIcon(R.mipmap.num10);
                            mData.add(data);
                            adapter.notifyDataSetChanged();
                            loadMoreHolder.update(ListViewAdapter.LoadMoreHolder.LOADER_STATE_NORMAL);
                        } else {
                            loadMoreHolder.update(ListViewAdapter.LoadMoreHolder.LOADER_STATE_RELOAD);

                        }
                }, 1000);
                }
            });
        }
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < Datas.icons.length; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.setIcon(Datas.icons[i]);
            itemBean.setTitle("我是第 " + i + "个条目。");
            mData.add(itemBean);
        }
        showList(true, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.list_view_vertical_stander:
                showList(true, false);
                break;
            case R.id.list_view_vertical_reverse:
                showList(true, true);
                break;
            case R.id.list_view_horizontal_stander:
                showList(false, false);
                break;
            case R.id.list_view_horizontal_reverse:
                showList(false, true);
                break;
            case R.id.grid_view_vertical_stander:
                showGrid(true, false);
                break;
            case R.id.grid_view_vertical_reverse:
                showGrid(true, true);
                break;
            case R.id.grid_view_horizontal_stander:
                showGrid(false, false);
                break;
            case R.id.grid_view_horizontal_reverse:
                showGrid(false, true);
                break;
            case R.id.stagger_view_vertical_stander:
                showStagger(true, false);
                break;
            case R.id.stagger_view_vertical_reverse:
                showStagger(true, true);
                break;
            case R.id.stagger_view_horizontal_stander:
                showStagger(false, false);
                break;
            case R.id.stagger_view_horizontal_reverse:
                showStagger(false, true);
                break;
            case R.id.more_type:
//                跳转到一个Activity里面实现这个功能
                Intent intent = new Intent(this, MoreTypeActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showList(boolean isVertical, boolean isReverse) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(isVertical ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL);
        linearLayoutManager.setReverseLayout(isReverse);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ListViewAdapter(mData);
        recyclerView.setAdapter(adapter);
        //        初始化事件
        initListener();
    }

    /**
     * 瀑布流效果
     */
    private void showStagger(boolean isVertical, boolean isReverse) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL
        );
        staggeredGridLayoutManager.setReverseLayout(isReverse);

        adapter = new StaggeredViewAdapter(mData);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
        //        初始化事件
        initListener();
    }

    private void showGrid(boolean isVertical, boolean isReverse) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3,
                isVertical ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL,
                isReverse);

        adapter = new GridViewAdapter(mData);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        //        初始化事件
        initListener();
    }
}