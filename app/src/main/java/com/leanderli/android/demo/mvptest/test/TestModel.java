package com.leanderli.android.demo.mvptest.test;

import android.os.Handler;

import com.leanderli.android.demo.mvptest.base.BaseCallback;

/**
 * Created by Administrator on 2018-07-03.
 */

public class TestModel {

    public static void getData(final String param, final BaseCallback<String> callback) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (param) {
                    case "success":
                        callback.onSuccess("请求成功，参数：" + param);
                        break;
                    case "failed":
                        callback.onFailure("请求失败，参数：" + param);
                        break;
                    case "error":
                        callback.onError();
                        break;
                }
                callback.onComplete();
            }
        }, 3000);
    }
}
