package com.mystrawberry.baikedonotstarve;


import android.support.annotation.Nullable;

import com.mystrawberry.baikedonotstarve.adapter.BaseDataBindingAdapter;
import com.mystrawberry.baikedonotstarve.databinding.RvMainItemBinding;

import java.util.List;

/**
 * Created by jk on 2017/10/8.
 */

public class MyAdapter extends BaseDataBindingAdapter<String,RvMainItemBinding> {

    private int selectedPos = 0;
    public MyAdapter( @Nullable List<String> data) {
        super(R.layout.rv_main_item, data);
    }

    @Override
    protected void convert(RvMainItemBinding binding, String item,int position) {
        binding.setDrawableId(R.drawable.good);
        binding.setName(item);
        binding.getRoot().setSelected(selectedPos == position);
    }




}
