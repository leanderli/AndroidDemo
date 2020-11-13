package com.leanderli.android.demo.architecture.mvp.recycleviewtest;

import android.os.Handler;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.architecture.mvp.base.BaseCallback;
import com.leanderli.android.demo.architecture.mvp.domain.User;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-07-03.
 */

public class ListTestModel {

    public void getData(final BaseCallback<ArrayList<User>> callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<User> users = new ArrayList<User>(10);
                    for (int count = 0; count < 10; count++) {
                        User user = new User();
                        user.setId(String.valueOf(count));
                        user.setUsername("User" + count);
                        user.setEmail("Email" + count + "@qq.com");
                        user.setIcon(R.mipmap.ic_launcher);
                        users.add(user);
                    }
                    callback.onSuccess(users);
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFailure(e.getMessage());
                }
                callback.onComplete();
            }
        }, 1500);
    }
}
