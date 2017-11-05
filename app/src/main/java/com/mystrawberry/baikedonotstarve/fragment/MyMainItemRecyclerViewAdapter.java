package com.mystrawberry.baikedonotstarve.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mystrawberry.baikedonotstarve.R;
import com.mystrawberry.baikedonotstarve.adapter.BaseDataBindingAdapter;
import com.mystrawberry.baikedonotstarve.databinding.FragmentMainitemBinding;
import com.mystrawberry.baikedonotstarve.info.BaseInfo;
import com.mystrawberry.baikedonotstarve.interfaces.OnListFragmentInteractionListener;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BaseInfo} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 *
 */
class MyMainItemRecyclerViewAdapter extends BaseDataBindingAdapter<BaseInfo,FragmentMainitemBinding> {


    private final OnListFragmentInteractionListener mListener;
    private final int mSelectedPos;

    MyMainItemRecyclerViewAdapter(ArrayList<BaseInfo> items, OnListFragmentInteractionListener listener,int selectedPos) {
        super(R.layout.fragment_mainitem,items);
        mSelectedPos = selectedPos;
        mListener = listener;
    }

    @Override
    protected void convert(FragmentMainitemBinding binding, final BaseInfo item, int position) {

        binding.setBaseInfo(item);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(item,mSelectedPos );
                }
            }
        });
    }


}
