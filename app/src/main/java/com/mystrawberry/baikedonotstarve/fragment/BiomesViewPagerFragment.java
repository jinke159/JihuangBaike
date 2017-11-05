package com.mystrawberry.baikedonotstarve.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mystrawberry.baikedonotstarve.R;
import com.mystrawberry.baikedonotstarve.adapter.MainFragmentPagerAdapter;
import com.mystrawberry.baikedonotstarve.info.BaseTabName;
import com.mystrawberry.baikedonotstarve.info.Biomes;

import java.util.ArrayList;


public class BiomesViewPagerFragment extends Fragment {

    private static final String TAG = "BiomesViewPagerFragment";
    private static final String DATA_KEY = "data";
    private static final String SELECTED_POS = "selectedPos";
    //    private OnFragmentInteractionListener mListener;
    private ArrayList<Biomes.BiomesListBean> mDataList;
    private int mSelectedPos;

    public BiomesViewPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BiomesViewPagerFragment.
     */
    public static BiomesViewPagerFragment newInstance(ArrayList<? extends BaseTabName> biomesList, int selectedPos) {
        BiomesViewPagerFragment fragment = new BiomesViewPagerFragment();


        Bundle args = new Bundle();
        args.putParcelableArrayList(DATA_KEY, biomesList);
        args.putInt(SELECTED_POS, selectedPos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mDataList = arguments.getParcelableArrayList(DATA_KEY);
            mSelectedPos = arguments.getInt(SELECTED_POS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewPager rootView = (ViewPager) inflater.inflate(R.layout.fragment_biomes_view_pager, container, false);
        onButtonPressed(rootView);


        return rootView;
    }


    public void onButtonPressed(ViewPager viewPager) {
//        if (mListener != null) {
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getChildFragmentManager(),mSelectedPos);
        adapter.setList(mDataList);
        viewPager.setAdapter(adapter);


//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//
//        void onFragmentInteraction();
//    }


}
