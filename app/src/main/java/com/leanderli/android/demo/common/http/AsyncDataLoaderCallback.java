package com.leanderli.android.demo.common.http;

/**
 * @Description
 * @Author ls573
 * @Date 18.7.11
 */

public interface AsyncDataLoaderCallback<T> {

    void onSuccess(T data);

    void onFailure(String msg);
}
