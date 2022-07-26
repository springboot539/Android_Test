package com.example.recyclerviewtest.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.domain.ItemBean;

import java.util.List;

/**
 * [类的说明]
 */
public class StaggeredViewAdapter extends RecyclerViewBaseAdapter{

    public StaggeredViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_staggered_list,null);
        return view;
    }
}
