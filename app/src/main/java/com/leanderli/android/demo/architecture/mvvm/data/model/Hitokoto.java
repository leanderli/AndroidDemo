package com.leanderli.android.demo.architecture.mvvm.data.model;

import com.google.gson.annotations.SerializedName;

public class Hitokoto {

    @SerializedName(value = "source")
    private String source;

    @SerializedName(value = "hitokoto")
    private String content;

    public Hitokoto(String source, String content) {
        this.source = source;
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public String getContent() {
        return content;
    }
}
