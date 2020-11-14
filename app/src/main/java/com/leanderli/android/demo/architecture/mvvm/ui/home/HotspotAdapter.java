package com.leanderli.android.demo.architecture.mvvm.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.MapUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leanderli.android.demo.R;
import com.leanderli.android.demo.architecture.mvvm.data.model.Hotspot;

import java.util.ArrayList;
import java.util.HashMap;

public class HotspotAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private final LayoutInflater layoutInflater;

    private ArrayList<String> mHotspotGroups;
    private HashMap<String, ArrayList<Hotspot>> mHotspotGroupItems;

    public HotspotAdapter(Context context, ArrayList<String> hotspotGroups, HashMap<String, ArrayList<Hotspot>> hotspotGroupItems) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);

        this.mHotspotGroups = hotspotGroups;
        this.mHotspotGroupItems = hotspotGroupItems;
    }

    @Override
    public int getGroupCount() {
        return CollectionUtils.isEmpty(mHotspotGroups) ? 0 : mHotspotGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return MapUtils.isEmpty(mHotspotGroupItems) ? 0 : mHotspotGroupItems.get(mHotspotGroups.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHotspotGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mHotspotGroupItems.get(mHotspotGroups.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.mvvm_home_hotspot_group_item, parent, false);
        }
        String groupName = mHotspotGroups.get(groupPosition);
        TextView groupNameTextView = convertView.findViewById(R.id.tv_title);
        groupNameTextView.setText(groupName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Hotspot hotspot = mHotspotGroupItems.get(mHotspotGroups.get(groupPosition)).get(childPosition);
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.mvvm_home_hotspot_item_item, parent, false);
        }
        TextView hotspotNameTextView = convertView.findViewById(R.id.tv_title);
        hotspotNameTextView.setText(hotspot.getName());

        convertView.setOnClickListener((v) -> {
            ToastUtils.showShort(hotspot.getName());
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
