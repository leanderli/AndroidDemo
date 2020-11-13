package com.leanderli.android.demo.architecture.mvvm.data;

public interface DataSourceCallback {

    void onSuccess(Result<Result.Success> successResult);

    void onError(Result<Result.Error> errorResult);
}
