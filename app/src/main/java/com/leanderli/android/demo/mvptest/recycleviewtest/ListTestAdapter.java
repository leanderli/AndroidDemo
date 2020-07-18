package com.leanderli.android.demo.mvptest.recycleviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.mvptest.domain.User;

import java.util.ArrayList;

/**
 * @Description
 * @Author ls573
 * @Date 18.7.3
 */

public class ListTestAdapter extends RecyclerView.Adapter<ListTestViewHolder> {

    private ArrayList<User> users;
    private Context context;

    public ListTestAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public ListTestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_list_test_item, parent, false);
        ListTestViewHolder viewHolder = new ListTestViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListTestViewHolder holder, int position) {
        holder.username.setText(users.get(position).getUsername());
        holder.id.setText(users.get(position).getId());
        holder.icon.setImageDrawable(context.getDrawable(users.get(position).getIcon()));
    }

    @Override
    public int getItemCount() {
        return null != users ? users.size() : 0;
    }
}
