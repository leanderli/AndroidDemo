package com.leanderli.android.demo.gridview;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author leanderli
 * @date 2019.11.10
 */
public class MyDragViewAdapter extends DragAdapter {

    private final Context mContext;

    private ArrayList<String> mDatas;

    public MyDragViewAdapter(Context context) {
        mContext = context;
    }

    public void setDataAndRefresh(ArrayList<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public void onDataModelMove(int from, int to) {
        String str = mDatas.remove(from);
        mDatas.add(to, str);
    }

    @Override
    public int getCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    @Override
    public String getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        TextView textView;
        if (convertView == null) {
            FrameLayout frameLayout = new FrameLayout(mContext);
            convertView = frameLayout;
            textView = new TextView(mContext);
            frameLayout.setPadding(20, 20, 20, 20);
            textView.setPadding(20, 100, 20, 100);
            frameLayout.addView(textView);
            textView.setBackgroundColor(0x33ff00ff);
            textView.setGravity(Gravity.CENTER);
        } else {
            textView = (TextView) ((FrameLayout) convertView).getChildAt(0);
        }
        textView.setText(getItem(position));
        return convertView;
    }

}

