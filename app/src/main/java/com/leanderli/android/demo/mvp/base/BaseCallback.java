package com.leanderli.android.demo.mvp.base;

/**
 * Created by Administrator on 2018-07-03.
 */

public interface BaseCallback<T> {

    void onSuccess(T data);

    void onFailure(String msg);

    void onError();

    void onComplete();
}
