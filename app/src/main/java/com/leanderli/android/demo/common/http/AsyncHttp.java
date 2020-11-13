package com.leanderli.android.demo.common.http;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Description
 * @Author ls573
 * @Date 18.7.11
 */

public class AsyncHttp {
    public final static int CONNECT_TIMEOUT = 7000;
    public final static int READ_TIMEOUT = 7000;
    public final static int WRITE_TIMEOUT = 7000;

    public final static MediaType JSON = MediaType.get("application/json;charset=UTF-8");

    private static OkHttpClient okHttpClient;

    static {
        if (null == okHttpClient) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
    }

    public void get(String url, final AsyncDataLoaderCallback<String> callback) {
        final Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().string());
                }
            }
        });
    }

    public void post(String url, String postJson, final AsyncDataLoaderCallback<String> callback) {
        LogUtils.d(url + "," + postJson);
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON, postJson))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onSuccess(response.body().string());
            }
        });
    }

    public void download(String url, final String storagePath, final AsyncDownloadCallback callback) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileIOUtils.writeFileFromIS(storagePath, inputStream);
                callback.onDone();
            }
        });
    }

}
