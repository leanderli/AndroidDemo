package com.leanderli.android.demo.mvptest.base;

/**
 * Created by Administrator on 2018-07-03.
 */

public class BasePresenter<V extends BaseView> {

    protected V view;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
    }

}
