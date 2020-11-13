package com.leanderli.android.demo.architecture.mvvm.data;

import com.leanderli.android.demo.architecture.mvvm.data.model.Hitokoto;

public class HomeRepository {

    private static volatile HomeRepository instance;

    private HomeDataSource dataSource;

    private Hitokoto hitokoto = null;

    private HomeRepository(HomeDataSource homeDataSource) {
        this.dataSource = homeDataSource;
    }

    public static HomeRepository getInstance(HomeDataSource homeDataSource) {
        if (null == instance) {
            instance = new HomeRepository(homeDataSource);
        }
        return instance;
    }

    public void loadHitokoto(DataSourceCallback callback) {
        dataSource.getHitokoto(new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> successResult) {
                setHitokoto(((Result.Success<Hitokoto>) successResult).getData());
                callback.onSuccess(successResult);
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {
                callback.onError(errorResult);
            }
        });
    }

    private void setHitokoto(Hitokoto hitokoto) {
        this.hitokoto = hitokoto;
    }
}
