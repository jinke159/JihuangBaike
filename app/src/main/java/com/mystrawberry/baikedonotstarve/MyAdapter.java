package com.mystrawberry.baikedonotstarve;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mystrawberry.baikedonotstarve.databinding.RvMainItemBinding;

/**
 * Created by jk on 2017/10/8.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


    private LayoutInflater mInflater;
    private String[] name = {"人物","食物","生物","自然","物品"};
    private int[] pic = {0};

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        View view = mInflater.inflate(R.layout.rv_main_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mBind.setDrawableId(R.mipmap.ic_launcher);
        holder.mBind.setMainName(name[position]);

    }

    public static class MyViewHolder extends ViewHolder{


        private final RvMainItemBinding mBind;

        public MyViewHolder(View itemView) {
            super(itemView);
            mBind = RvMainItemBinding.bind(itemView);

        }


    }


}
