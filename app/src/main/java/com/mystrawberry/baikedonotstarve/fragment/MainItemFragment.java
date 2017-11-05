package com.mystrawberry.baikedonotstarve.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mystrawberry.baikedonotstarve.R;
import com.mystrawberry.baikedonotstarve.info.BaseInfo;
import com.mystrawberry.baikedonotstarve.interfaces.OnListFragmentInteractionListener;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MainItemFragment extends Fragment {


    private static final String COLUMN_COUNT = "column-count";
    private static final String INFOS = "infos";
    private static final String SELECTED_POS = "selectedPos";
    private int mColumnCount = 3;
    private OnListFragmentInteractionListener mListener;
    private ArrayList<BaseInfo> mBaseInfos;
    private int mSelectedPos;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainItemFragment() {
    }


    @SuppressWarnings("unused")
    public static MainItemFragment newInstance(int columnCount,  ArrayList<? extends BaseInfo> baseInfos,int selectedPos) {
        MainItemFragment fragment = new MainItemFragment();
        Bundle args = new Bundle();
        args.putInt(COLUMN_COUNT, columnCount);
        args.putParcelableArrayList(INFOS,baseInfos);
        args.putInt(SELECTED_POS,selectedPos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mColumnCount = bundle.getInt(COLUMN_COUNT);
            mBaseInfos = bundle.getParcelableArrayList(INFOS);
            mSelectedPos = bundle.getInt(SELECTED_POS);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyMainItemRecyclerViewAdapter(mBaseInfos, mListener,mSelectedPos));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
