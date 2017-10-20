package com.mystrawberry.baikedonotstarve.adapter;


import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mystrawberry.baikedonotstarve.R;
import com.mystrawberry.baikedonotstarve.databinding.RvMainItemBinding;
import com.mystrawberry.baikedonotstarve.info.TextAndDrawable;

/**
 * 侧滑菜单recyclerView 的适配器
 * Created by jk on 2017/10/8.
 */

public class MyAdapter extends BaseDataBindingAdapter<TextAndDrawable,RvMainItemBinding> {

    @SuppressWarnings("WeakerAccess")
    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
    }

    public int getSelectedPos() {

        return selectedPos;
    }

    private int selectedPos = 0;

    public MyAdapter(final DrawerLayout drawerLayout, int pos) {
        super(R.layout.rv_main_item);

            selectedPos = pos;


        setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                setSelectedPos(position);
               //不能用notifyItemChanged   改成普通图可以
                notifyDataSetChanged();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    protected void convert(RvMainItemBinding binding, TextAndDrawable item, int position) {
        binding.setDrawable(item.mDrawable);
        binding.setName(item.mString);
        binding.getRoot().setSelected(selectedPos == position);
    }


}
