package com.leanderli.android.demo.launcher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.common.OnItemClickListener;

import java.util.ArrayList;

/**
 * @Description
 * @Author ls573
 * @Date 18.9.24
 */

public class AppGridViewAdapter extends RecyclerView.Adapter<AppGridViewHolder> {

    private Context mContext;
    private ArrayList<AppInfo> appInfos;
    private OnItemClickListener onItemClickListener;

    public AppGridViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setDataSetChanged(ArrayList<AppInfo> appInfos) {
        this.appInfos = appInfos;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public AppGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.al_app_list_item, parent, false);
        AppGridViewHolder viewHolder = new AppGridViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AppGridViewHolder holder, int position) {
        holder.appInfoView.setIcon(appInfos.get(position).getAppIcon());
//        holder.appInfoView.setText(appInfos.get(position).getAppName());
        holder.appInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onItemClickListener) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.appInfoView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == appInfos ? 0 : appInfos.size();
    }
}
