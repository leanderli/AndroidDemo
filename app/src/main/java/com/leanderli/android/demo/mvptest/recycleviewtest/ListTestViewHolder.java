package com.leanderli.android.demo.mvptest.recycleviewtest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.leanderli.android.demo.R;

/**
 * @Description
 * @Author ls573
 * @Date 18.7.3
 */

public class ListTestViewHolder extends RecyclerView.ViewHolder {

    public TextView username, id;
    public ImageView icon;

    public ListTestViewHolder(View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.tv_username);
        id = itemView.findViewById(R.id.tv_id);

        icon = itemView.findViewById(R.id.iv_icon);
    }
}
