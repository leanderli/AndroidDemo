package com.leanderli.android.demo.architecture.mvvm.ui.home.hotspot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.MapUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leanderli.android.demo.R;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotItem;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotType;
import com.leanderli.android.demo.common.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class HotspotAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private final LayoutInflater layoutInflater;

    private ArrayList<HotspotType> mHotspotTypes;
    private HashMap<Integer, ArrayList<HotspotItem>> mHotspotItems;

    public HotspotAdapter(Context context, ArrayList<HotspotType> hotspotTypes, HashMap<Integer, ArrayList<HotspotItem>> hotspotItems) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);

        this.mHotspotTypes = hotspotTypes;
        this.mHotspotItems = hotspotItems;
    }

    @Override
    public int getGroupCount() {
        return CollectionUtils.isEmpty(mHotspotTypes) ? 0 : mHotspotTypes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (MapUtils.isEmpty(mHotspotItems)) {
            return 0;
        } else {
            if (CollectionUtils.isEmpty(mHotspotItems.get(mHotspotTypes.get(groupPosition).getType()))) {
                return 0;
            } else {
                return mHotspotItems.get(mHotspotTypes.get(groupPosition).getType()).size();
            }
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHotspotTypes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mHotspotItems.get(mHotspotTypes.get(groupPosition).getType()).get(childPosition);
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
            convertView = layoutInflater.inflate(R.layout.mvvm_home_hotspot_type_item, parent, false);
        }
        TextView typeNameTextView = convertView.findViewById(R.id.tv_title);
        typeNameTextView.setText(mHotspotTypes.get(groupPosition).getName());

        ImageView typeIconImageView = convertView.findViewById(R.id.iv_icon);
        typeIconImageView.setImageDrawable(mHotspotTypes.get(groupPosition).getIcon());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        HotspotType hotspotType = mHotspotTypes.get(groupPosition);
        HotspotItem hotspotItem = mHotspotItems.get(hotspotType.getType()).get(childPosition);
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.mvvm_home_hotspot_item_item, parent, false);
        }
        TextView hotspotNameTextView = convertView.findViewById(R.id.tv_title);
        hotspotNameTextView.setText(hotspotItem.getName());

        ImageView hotspotShareImageView = convertView.findViewById(R.id.iv_share_icon);
        hotspotShareImageView.setOnClickListener((v) -> {
            String shareText = hotspotItem.getName() + "\n" + hotspotItem.getUrl();
            ActivityUtils.startActivity(IntentUtils.getShareTextIntent(shareText, true));
        });

        convertView.setOnClickListener((v) -> {
            ToastUtils.showShort(hotspotItem.getName());
            if (!StringUtils.isEmpty(hotspotItem.getUrl())) {
                // open url in browser
                Utilities.openLinkByBrowser(mContext, hotspotItem.getUrl());
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
