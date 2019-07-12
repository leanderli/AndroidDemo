package com.leanderli.android.demo.mvptest.recycleviewtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.mvptest.base.BaseActivity;
import com.leanderli.android.demo.mvptest.domain.User;

import java.util.ArrayList;

public class ListTestActivity extends BaseActivity implements ListTestView {

    RecyclerView userListView;
    private ListTestAdapter listTestAdapter;
    private ListTestPresenter listTestPresenter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        userListView = findViewById(R.id.rv_list);

        listTestPresenter = new ListTestPresenter();
        listTestPresenter.attachView(this);

        listTestPresenter.getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listTestPresenter.detachView();
    }

    @Override
    public void setAdapter(ArrayList<User> users) {
        listTestAdapter = new ListTestAdapter(this, users);
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        userListView.setLayoutManager(layoutManager);
        userListView.setAdapter(listTestAdapter);
    }

    @Override
    public void notifyUpdateData() {
        listTestAdapter.notifyDataSetChanged();
    }

    public void addData(View view) {
        listTestPresenter.addData();
    }

    public void removeData(View view) {
    }
}
