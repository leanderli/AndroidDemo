package com.leanderli.android.demo.architecture.mvvm.data;

import com.leanderli.android.demo.architecture.mvvm.data.model.Hitokoto;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotItem;
import com.leanderli.android.demo.architecture.mvvm.data.model.Weather;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeRepository {

    private static volatile HomeRepository instance;

    private HomeDataSource dataSource;

    private Hitokoto hitokoto = null;
    private HashMap<Integer, ArrayList<HotspotItem>> hotspotsMap = new HashMap<>();
    private Weather currentWeather;
    private ArrayList<Weather> threeDayWeathers;

    private HomeRepository(HomeDataSource homeDataSource) {
        this.dataSource = homeDataSource;
    }

    public static HomeRepository getInstance(HomeDataSource homeDataSource) {
        if (null == instance) {
            instance = new HomeRepository(homeDataSource);
        }
        return instance;
    }

    public void getHitokoto(DataSourceCallback callback) {
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

    public void getHotspot(int hotspotType, DataSourceCallback callback) {
        dataSource.getHotspot(hotspotType, new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> successResult) {
                setHotspot(hotspotType, ((Result.Success<ArrayList<HotspotItem>>) successResult).getData());
                callback.onSuccess(successResult);
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {
                callback.onError(errorResult);
            }
        });
    }

    public void getWeather(int weatherType, int cityCode, DataSourceCallback callback) {
        dataSource.getWeather(weatherType, cityCode, new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> successResult) {
                if (weatherType == Weather.TYPE_CURRENT) {
                    setCurrentWeather(((Result.Success<Weather>) successResult).getData());
                } else if (weatherType == Weather.TYPE_3DAY) {
                    setThreeDayWeathers(((Result.Success<ArrayList<Weather>>) successResult).getData());
                }
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

    private void setHotspot(int type, ArrayList<HotspotItem> hotspotItems) {
        hotspotsMap.put(type, hotspotItems);
    }

    private void setCurrentWeather(Weather weather) {
        this.currentWeather = weather;
    }

    private void setThreeDayWeathers(ArrayList<Weather> weathers) {
        this.threeDayWeathers = weathers;
    }
}
