package com.leanderli.android.demo.architecture.mvvm.data.model;

import android.graphics.drawable.Drawable;

public class HotspotType {

    public final static int TYPE_WEIBO = 0;
    public final static int TYPE_ZHIHU = 1;
    public final static int TYPE_DOUYIN = 2;

    private int type;
    private String name;
    private Drawable icon;

    public HotspotType(int type, String name, Drawable icon) {
        this.type = type;
        this.name = name;
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
