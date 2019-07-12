package com.leanderli.android.demo.launcher;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @Description
 * @Author ls573
 * @Date 18.8.9
 */

public class AppListViewHolder extends RecyclerView.ViewHolder {

    public TextView appInfoText;
    public ImageView appIconView;
    public RelativeLayout mainLayout;

    public AppListViewHolder(View itemView) {
        super(itemView);
//        appIconView = itemView.findViewById(R.id.iv_app_icon);
//        appInfoText = itemView.findViewById(R.id.tv_app_info);
//        mainLayout = itemView.findViewById(R.id.rel_main);

        appIconView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
