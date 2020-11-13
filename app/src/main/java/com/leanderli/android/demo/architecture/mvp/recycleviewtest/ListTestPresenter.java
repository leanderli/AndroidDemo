package com.leanderli.android.demo.architecture.mvp.recycleviewtest;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.architecture.mvp.base.BaseCallback;
import com.leanderli.android.demo.architecture.mvp.base.BasePresenter;
import com.leanderli.android.demo.architecture.mvp.domain.User;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-07-03.
 */

public class ListTestPresenter extends BasePresenter<ListTestView> {

    public void getData() {

        if (!isViewAttached()) {
            return;
        }

        getView().showLoading();
        new ListTestModel().getData(new BaseCallback<ArrayList<User>>() {
            @Override
            public void onSuccess(ArrayList<User> data) {
                if (isViewAttached()) {
                    getView().setAdapter(data);
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

    public void addData() {

        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        if (isViewAttached()) {
            ArrayList<User> users = new ArrayList<User>(1);
            User user = new User();
            user.setId("111");
            user.setUsername("test");
            user.setIcon(R.mipmap.ic_launcher_round);
            users.add(user);
            getView().setAdapter(users);
            getView().hideLoading();
        }


    }


}
