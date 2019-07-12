package com.leanderli.android.demo.launcher;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.common.util.DensityUtils;
import com.leanderli.android.demo.customizeviews.LlDrawableTextView;

/**
 * @Description
 * @Author ls573
 * @Date 18.9.24
 */

public class AppGridViewHolder extends RecyclerView.ViewHolder {

    public LlDrawableTextView appInfoView;

    public AppGridViewHolder(View itemView) {
        super(itemView);
        appInfoView = itemView.findViewById(R.id.app_info_view);

        appInfoView.setTextColor(Color.WHITE);
        appInfoView.setIconSize(DensityUtils.dip2px(itemView.getContext(), 50));
    }
}
