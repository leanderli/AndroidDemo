package com.leanderli.android.demo.mvp.test;

import com.leanderli.android.demo.mvp.base.BaseCallback;
import com.leanderli.android.demo.mvp.base.BasePresenter;

/**
 * Created by Administrator on 2018-07-03.
 */

public class TestPresenter extends BasePresenter<TestView> {

    public void getData(String params) {
        if (!isViewAttached()) {
            return;
        }

        getView().showLoading();
        TestModel.getData(params, new BaseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                if (isViewAttached()) {
                    getView().showData(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()) {
                    getView().showToast(msg);
                }
            }

            @Override
            public void onError() {
                if (isViewAttached()) {
                    getView().showError();
                }
            }

            @Override
            public void onComplete() {
                if (isViewAttached()) {
                    getView().hideLoading();
                }
            }
        });

    }
}
