package com.example.recyclerviewtest.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.domain.ItemBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * [类的说明]
 */
public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    protected final List<ItemBean> data;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewBaseAdapter(List<ItemBean> data){
        this.data = data;
    }
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = getSubView(parent,viewType);
        return new InnerHolder(view);

    }

    protected abstract View getSubView(ViewGroup parent,int viewType);

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        ((InnerHolder)holder).setData(data.get(position),position);
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public  void setOnItemClickListener(OnItemClickListener listener){
//        设置一个监听，其实就是要设置一个接口，一个回调的接口
        this.onItemClickListener = listener;
    };


    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView title;
        private int position;

        public InnerHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });

        }

        public void setData(ItemBean itemBean, int position) {
            this.position = position;
            icon.setImageResource(itemBean.getIcon());
            title.setText(itemBean.getTitle());
        }
    }
}
