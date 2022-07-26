package com.example.recyclerviewtest.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.domain.MoreTypeBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * [类的说明]
 */
public class MoreTypeAdapter extends RecyclerView.Adapter {

    private final List<MoreTypeBean> data;
    public static final int TYPE_FULL_IMAGE = 0;
    public static final int TYPE_RIGHT_IMAGE = 1;
    public static final int TYPE_THREE_IMAGE = 2;

    public MoreTypeAdapter(List<MoreTypeBean> data) {
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view;
//        创建ViewHolder
        if (viewType == TYPE_FULL_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_full_image, null);
            return new FullIamgeHolder(view);
        } else if (viewType == TYPE_RIGHT_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_left_title_right_image, null);
            return new RightIamgeHolder(view);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_type_three_iamge, null);
            return new ThreeIamgeHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

//    创建三种类型的Holder

//    复写方法，根据条件来返回条目类型

    @Override
    public int getItemViewType(int position) {
        MoreTypeBean moreTypeBean = data.get(position);
        if (moreTypeBean.type == 0) {
            return TYPE_FULL_IMAGE;
        } else if (moreTypeBean.type == 1) {
            return TYPE_RIGHT_IMAGE;
        } else {
            return TYPE_THREE_IMAGE;
        }
    }

    private class FullIamgeHolder extends RecyclerView.ViewHolder {

        public FullIamgeHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

    private class RightIamgeHolder extends RecyclerView.ViewHolder {

        public RightIamgeHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

    private class ThreeIamgeHolder extends RecyclerView.ViewHolder {

        public ThreeIamgeHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
