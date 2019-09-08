package com.leanderli.android.demo.tablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leanderli.android.demo.R;

import java.util.ArrayList;

/**
 * @author leanderli
 * @date 2019.08.27
 */
public class DetailInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM = "param";
    private static final String ARG_DATA = "datas";

    // TODO: Rename and change types of parameters

    //用来表示当前需要展示的是哪一页
    private int mParam;
    private ArrayList<String> mDatas;

    private RecyclerView mListView;
    private RecyclerViewAdapter mAdapter;

    public DetailInfoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetailInfoFragment newInstance(int param, ArrayList<String> datas) {
        DetailInfoFragment fragment = new DetailInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, param);
        args.putStringArrayList(ARG_DATA, datas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getInt(ARG_PARAM);
            mDatas = getArguments().getStringArrayList(ARG_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_info, container, false);
        mListView = view.findViewById(R.id.list_view);
        mAdapter = new RecyclerViewAdapter(getContext());
        mListView.setAdapter(mAdapter);
        mListView.setLayoutManager(mAdapter.getLayoutManager());
        mAdapter.setData(mDatas);
        return view;
    }

}
