package com.leanderli.android.demo.popup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leanderli.android.demo.R;

import java.util.ArrayList;

import razerdp.basepopup.BasePopupWindow;

public class ShortcutsPopupView extends BasePopupWindow {

    private final Context mContext;

    private ArrayList<ShortcutsInfo> mShortcutsInfos;

    private ShortcutsAdapter mShortcutsAdapter;

    public ShortcutsPopupView(Context context) {
        super(context);
        mContext = context;
    }

    public void setShortcuts(ArrayList<ShortcutsInfo> shortcuts) {
        this.mShortcutsInfos = shortcuts;
        mShortcutsAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateContentView() {
        return createView();
    }

    private View createView() {
        View view = createPopupById(R.layout.shortcuts_view);
        RecyclerView recyclerView = view.findViewById(R.id.shortcuts);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        mShortcutsAdapter = new ShortcutsAdapter();
        recyclerView.setAdapter(mShortcutsAdapter);
        return view;
    }

    private class ShortcutsAdapter extends RecyclerView.Adapter<ShortcutsViewHolder> {

        @Override
        public ShortcutsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.shortcuts_item_view, parent, false);
            return new ShortcutsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ShortcutsViewHolder holder, int position) {
            holder.mLabel.setText(mShortcutsInfos.get(position).label);
        }

        @Override
        public int getItemCount() {
            return null == mShortcutsInfos ? 0 : mShortcutsInfos.size();
        }
    }

    private class ShortcutsViewHolder extends RecyclerView.ViewHolder {

        TextView mLabel;
        ImageView mIcon;

        ShortcutsViewHolder(View itemView) {
            super(itemView);
            mLabel = itemView.findViewById(R.id.label);
            mIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
