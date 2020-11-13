package com.leanderli.android.demo.architecture.mvvm.data;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.leanderli.android.demo.common.http.AsyncDataLoaderCallback;
import com.leanderli.android.demo.common.http.AsyncHttp;
import com.leanderli.android.demo.architecture.mvvm.data.model.Hitokoto;

/**
 * 获取远程调用的数据信息
 */
public class HomeDataSource {
    private final static String HITOKOTO_URL = "https://cloudgw.api.dailypics.cn/release/tu_hitokoto";

    public void getHitokoto(DataSourceCallback callback) {
        new AsyncHttp().get(HITOKOTO_URL, new AsyncDataLoaderCallback<String>() {
            @Override
            public void onSuccess(String data) {
                LogUtils.d(data);
                if (StringUtils.isEmpty(data)) {
                    callback.onError(new Result.Error(new RuntimeException("Error on get hitokoto,data is null")));
                } else {
                    Hitokoto hitokoto = new Gson().fromJson(data, Hitokoto.class);
                    callback.onSuccess(new Result.Success(hitokoto));
                }
            }

            @Override
            public void onFailure(String msg) {
                LogUtils.d(msg);
                callback.onError(new Result.Error(new RuntimeException(msg)));
            }
        });
    }


}
