package com.leanderli.android.demo.component.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.leanderli.android.demo.R;

import java.util.List;

/**
 * @author leanderli
 * @date 2019.09.08
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mDatas;

    public ListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.common_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.label.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView label;
        public ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
