package com.leanderli.android.demo.architecture.mvvm.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leanderli.android.demo.architecture.mvvm.data.DataSourceCallback;
import com.leanderli.android.demo.architecture.mvvm.data.HomeRepository;
import com.leanderli.android.demo.architecture.mvvm.data.Result;
import com.leanderli.android.demo.architecture.mvvm.data.model.Hitokoto;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotItem;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotType;
import com.leanderli.android.demo.architecture.mvvm.data.model.Weather;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Hitokoto> hitokotoLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<HotspotItem>> weiboHotspotLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<HotspotItem>> zhihuHotspotLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<HotspotItem>> douyinHotspotLiveData = new MutableLiveData<>();
    private MutableLiveData<Weather> currentWeatherLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Weather>> threeDayWeathers = new MutableLiveData<>();

    private HomeRepository repository;

    HomeViewModel(HomeRepository homeRepository) {
        this.repository = homeRepository;
    }

    LiveData<Hitokoto> getHitokoto() {
        repository.getHitokoto(new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> successResult) {
                hitokotoLiveData.postValue((Hitokoto) ((Result.Success) successResult).getData());
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {

            }
        });
        return hitokotoLiveData;
    }

    LiveData<ArrayList<HotspotItem>> getWeiboHotspot() {
        return this.weiboHotspotLiveData;
    }

    LiveData<ArrayList<HotspotItem>> getZhihuHotspot() {
        return this.zhihuHotspotLiveData;
    }

    LiveData<ArrayList<HotspotItem>> getDouyinHotspot() {
        return this.douyinHotspotLiveData;
    }

    public void getHotspots(int hotspotType) {
        repository.getHotspot(hotspotType, new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> successResult) {
                switch (hotspotType) {
                    case HotspotType.TYPE_WEIBO:
                        weiboHotspotLiveData.postValue((ArrayList<HotspotItem>) ((Result.Success) successResult).getData());
                        break;
                    case HotspotType.TYPE_ZHIHU:
                        zhihuHotspotLiveData.postValue((ArrayList<HotspotItem>) ((Result.Success) successResult).getData());
                        break;
                    case HotspotType.TYPE_DOUYIN:
                        douyinHotspotLiveData.postValue((ArrayList<HotspotItem>) ((Result.Success) successResult).getData());
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown hotspot type");
                }
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {

            }
        });
    }

    LiveData<Weather> getCurrentWeather(int cityCode) {
        repository.getWeather(Weather.TYPE_CURRENT, cityCode, new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> successResult) {
                currentWeatherLiveData.postValue((Weather) ((Result.Success) successResult).getData());
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {

            }
        });
        return currentWeatherLiveData;
    }

    LiveData<ArrayList<Weather>> getThreeDayWeather(int cityCode) {
        repository.getWeather(Weather.TYPE_3DAY, cityCode, new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> successResult) {
                threeDayWeathers.postValue((ArrayList<Weather>) ((Result.Success) successResult).getData());
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {

            }
        });
        return threeDayWeathers;
    }


}
