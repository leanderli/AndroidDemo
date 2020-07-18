package com.leanderli.android.demo.tablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leanderli.android.demo.R;

import java.util.ArrayList;

/**
 * @author leanderli
 * @date 2019.08.25
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> datas;
    private GridLayoutManager mLayoutManager;

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
        mLayoutManager = new GridLayoutManager(context, 5, LinearLayoutManager.VERTICAL, false);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public void setData(ArrayList<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tablayout_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItemText.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mItemText;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemText = itemView.findViewById(R.id.item_name);
        }
    }
}
