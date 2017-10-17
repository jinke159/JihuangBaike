package com.mystrawberry.baikedonotstarve;


import android.support.annotation.Nullable;

import com.mystrawberry.baikedonotstarve.adapter.BaseDataBindingAdapter;
import com.mystrawberry.baikedonotstarve.databinding.RvMainItemBinding;
import com.mystrawberry.baikedonotstarve.info.TextAndDrawable;

import java.util.List;

/**
 * Created by jk on 2017/10/8.
 */

public class MyAdapter extends BaseDataBindingAdapter<TextAndDrawable,RvMainItemBinding> {

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
    }

    public int getSelectedPos() {

        return selectedPos;
    }

    private int selectedPos = 0;
    public MyAdapter( @Nullable List<TextAndDrawable> data) {
        super(R.layout.rv_main_item, data);
    }

    @Override
    protected void convert(RvMainItemBinding binding, TextAndDrawable item, int position) {
        binding.setDrawable(item.mDrawable);
        binding.setName(item.mString);
        binding.getRoot().setSelected(selectedPos == position);
    }


}
