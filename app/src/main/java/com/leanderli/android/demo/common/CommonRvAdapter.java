package com.leanderli.android.demo.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.CollectionUtils;
import com.leanderli.android.demo.R;

import java.util.ArrayList;

public class CommonRvAdapter extends RecyclerView.Adapter<CommonRvAdapter.CommonRvViewHolder> {
    private final Context mContext;
    private ArrayList<RvItem> mRvItems;
    private OnItemClickListener mClickListener;

    public CommonRvAdapter(Context context, ArrayList<RvItem> rvItems) {
        this.mContext = context;
        this.mRvItems = rvItems;
    }

    public void setItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    @NonNull
    @Override
    public CommonRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommonRvViewHolder(LayoutInflater.from(mContext).inflate(R.layout.common_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommonRvViewHolder holder, int position) {
        holder.mIcon.setBackground(mRvItems.get(position).icon);
        holder.mLabel.setText(mRvItems.get(position).title);
        if (null != mClickListener) {
            holder.itemView.setOnClickListener((v) -> mClickListener.onItemClick(v, position));
            holder.mIcon.setOnLongClickListener((v) -> {
                mClickListener.onItemLongClick(v, position);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.isEmpty(mRvItems) ? 0 : mRvItems.size();
    }

    public static class CommonRvViewHolder extends RecyclerView.ViewHolder {
        public TextView mLabel;
        public ImageView mIcon;

        public CommonRvViewHolder(@NonNull View itemView) {
            super(itemView);
            mLabel = itemView.findViewById(R.id.label);
            mIcon = itemView.findViewById(R.id.icon);
        }
    }
}
