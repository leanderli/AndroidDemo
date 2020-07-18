package com.leanderli.android.demo.gridview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leanderli.android.demo.R;

import java.util.ArrayList;

public class DragSortGridViewTestActivity extends AppCompatActivity {

    private DragSortGridView mGridView;
    private MyDragViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_sort_grid_view_test);

        mGridView = findViewById(R.id.app_grid_view);
        mGridView.setDragModel(DragSortGridView.DRAG_BY_LONG_CLICK);
        mAdapter = new MyDragViewAdapter(this);
        mGridView.setAdapter(mAdapter);
        mGridView.setNumColumns(5);

        //设置前面多少个位置固定,不能拖动
        mGridView.setNoPositionChangeItemCount(2);
        //设置尾部多少个位置固定,不能拖动
        mGridView.setFootNoPositionChangeItemCount(1);
        //修改item响应拖动时的效果,默认是放大到120%
        mGridView.setOnDragSelectListener(new DragSortGridView.OnDragSelectListener() {
            @Override
            public void onDragSelect(View mirror) {
                //当item开始拖动时调用该方法
            }

            @Override
            public void onPutDown(View itemView) {
                //当item被放时是调用该方法
            }
        });
        //修改长按拖动的响应时间
        mGridView.setDragLongPressTime(500);
        bindData();
    }

    public void bindData() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            datas.add("App " + i);
        }
        mAdapter.setDataAndRefresh(datas);
    }
}
