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
 * @Date 18.8.9
 */

public class AppListViewAdapter extends RecyclerView.Adapter<AppListViewHolder> {

    private ArrayList<AppInfo> appInfos;
    private Context context;

    public AppListViewAdapter(ArrayList<AppInfo> appInfos, Context context) {
        this.appInfos = appInfos;
        this.context = context;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public AppListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.al_app_list_item, parent, false);
        AppListViewHolder viewHolder = new AppListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AppListViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onItemClickListener) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (null != onItemClickListener) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, position);
                }
                return true;
            }
        });

        AppInfo appInfo = appInfos.get(position);
        holder.appInfoText.setText(appInfo.getAppName() + "/" + appInfo.getPackageName() + "/" + appInfo.getClassName());
        holder.appIconView.setImageDrawable(appInfo.getAppIcon());
        if (position % 2 == 0) {
            holder.mainLayout.setBackgroundColor(context.getResources().getColor(R.color.color_1));
        } else {
            holder.mainLayout.setBackgroundColor(context.getResources().getColor(R.color.color_2));
        }
    }

    @Override
    public int getItemCount() {
        return null != appInfos ? appInfos.size() : 0;
    }
}
