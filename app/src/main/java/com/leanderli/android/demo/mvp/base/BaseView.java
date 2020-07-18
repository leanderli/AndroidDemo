package com.leanderli.android.demo.mvp.base;

import android.content.Context;

/**
 * Created by Administrator on 2018-07-03.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showToast(String msg);

    void showError();

    Context getContext();
}
