package com.leanderli.android.demo.architecture.mvvm.data.model;

public class HotspotItem {

    private String name;
    private String url;
    private int type;
    private Integer hot;

    public HotspotItem() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
