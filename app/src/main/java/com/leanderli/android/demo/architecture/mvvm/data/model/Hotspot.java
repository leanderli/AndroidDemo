package com.leanderli.android.demo.architecture.mvvm.data.model;

public class Hotspot {

    public final static int TYPE_WEIBO = 0;
    public final static int TYPE_ZHIHU = 1;
    public final static int TYPE_DOUYIN = 2;

    private String name;
    private String url;
    private String type;
    private Integer hot;

    public Hotspot(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
