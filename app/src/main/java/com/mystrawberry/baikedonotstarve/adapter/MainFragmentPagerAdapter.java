package com.mystrawberry.baikedonotstarve.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mystrawberry.baikedonotstarve.fragment.MainItemFragment;
import com.mystrawberry.baikedonotstarve.info.Biomes;

import java.util.ArrayList;

/**
 * Created by jk on 2017/10/28.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {


    protected ArrayList<Biomes.BiomesListBean> mList;
    private final int mSelectedPos;

    public MainFragmentPagerAdapter(FragmentManager fm,int selectedPos) {
        super(fm);
        mSelectedPos = selectedPos;
    }
    public void setList(ArrayList<Biomes.BiomesListBean> list) {

        mList = list;
    }


    @Override
    public Fragment getItem(int position) {

        return MainItemFragment.newInstance(3, ( mList).get(position).biomesDataLists,mSelectedPos);
    }




    @Override
    public int getCount() {
        return mList.size();
    }




    @Override
    public CharSequence getPageTitle(int position) {

        return mList.get(position).categoryName;
    }

}
