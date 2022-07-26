package com.example.recyclerviewtest.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.domain.ItemBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * [类的说明]
 */
public class ListViewAdapter extends RecyclerViewBaseAdapter {

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_LAOD_MORE = 1;
    private onRefreschListener upPullRefreshListener;


    public ListViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = getSubView(parent, viewType);
        if (viewType == TYPE_NORMAL) {
            return new InnerHolder(view);
        } else {
            return new LoadMoreHolder(view);
        }
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view;

        if (viewType == TYPE_NORMAL) {

            view = View.inflate(parent.getContext(), R.layout.item_view_list, null);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_view_load_more, null);
        }
        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL && holder instanceof InnerHolder) {
            ((InnerHolder) holder).setData(data.get(position), position);
        } else if (getItemViewType(position) == TYPE_LAOD_MORE && holder instanceof LoadMoreHolder) {
            ((LoadMoreHolder) holder).update(LoadMoreHolder.LOADER_STATE_LOADING);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
//            最后一个则返回更多
            return TYPE_LAOD_MORE;
        }
        return TYPE_NORMAL;
    }

    /**
     * 这里设置监听的接口
     */
    public void setOnRefreshListener(onRefreschListener refreshListener) {
        this.upPullRefreshListener = refreshListener;
    }

    //    定义接口
    public interface onRefreschListener {
        void onUpPullRefresh(LoadMoreHolder loadMoreHolder);
    }

    public class LoadMoreHolder extends RecyclerView.ViewHolder {

        public static final int LOADER_STATE_LOADING = 0;
        public static final int LOADER_STATE_RELOAD = 1;
        public static final int LOADER_STATE_NORMAL = 2;

        private LinearLayout loading;
        private TextView reload;

        public LoadMoreHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            loading = itemView.findViewById(R.id.loading);
            reload = itemView.findViewById(R.id.reload);
            reload.setOnClickListener(view -> {
//                这里面要去触发加载事件
                update(LOADER_STATE_LOADING);
            });
        }

        public void update(int state) {

//            重置控件的状态
            loading.setVisibility(View.GONE);
            reload.setVisibility(View.GONE);

            switch (state) {
                case LOADER_STATE_LOADING:
                    loading.setVisibility(View.VISIBLE);
//                    触发加载数据
                    startLoaderMore();
                    break;
                case LOADER_STATE_RELOAD:
                    reload.setVisibility(View.VISIBLE);
                    break;
                case LOADER_STATE_NORMAL:
                    loading.setVisibility(View.GONE);
                    reload.setVisibility(View.GONE);
                    break;
            }
        }

        private void startLoaderMore() {
            if (upPullRefreshListener != null) {
                upPullRefreshListener.onUpPullRefresh(this);
            }
        }

    }
}
