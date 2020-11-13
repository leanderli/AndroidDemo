package com.leanderli.android.demo.common.http;

/**
 * @Description
 * @Author ls573
 * @Date 3/10/2019
 */
public interface AsyncDownloadCallback {

    void onDone();

    void onFailure(String msg);
}
