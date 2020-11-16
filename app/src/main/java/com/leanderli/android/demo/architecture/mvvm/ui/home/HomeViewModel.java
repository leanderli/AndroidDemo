package com.leanderli.android.demo.architecture.mvvm.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.leanderli.android.demo.architecture.mvvm.data.DataSourceCallback;
import com.leanderli.android.demo.architecture.mvvm.data.HomeRepository;
import com.leanderli.android.demo.architecture.mvvm.data.Result;
import com.leanderli.android.demo.architecture.mvvm.data.model.Hitokoto;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotItem;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotType;
import com.leanderli.android.demo.architecture.mvvm.data.model.Weather;

import java.util.ArrayList;
import java.util.Date;

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
                Weather current = (Weather) ((Result.Success) successResult).getData();
                if (null == currentWeatherLiveData.getValue()) {
                    current.setCompleteLoad(false);
                } else {
                    Weather partDataOfWeather = currentWeatherLiveData.getValue();
                    current.setTemperatureMax(partDataOfWeather.getTemperatureMax());
                    current.setTemperatureMin(partDataOfWeather.getTemperatureMin());
                    current.setSunrise(partDataOfWeather.getSunrise());
                    current.setSunset(partDataOfWeather.getSunset());
                    current.setMoonrise(partDataOfWeather.getMoonrise());
                    current.setMoonset(partDataOfWeather.getMoonset());
                    current.setCompleteLoad(true);
                }
                currentWeatherLiveData.postValue(current);
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
                ArrayList<Weather> weathers = (ArrayList<Weather>) ((Result.Success) successResult).getData();
                updateCurrentWeather(weathers);
                threeDayWeathers.postValue(weathers);
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {

            }
        });
        return threeDayWeathers;
    }

    private void updateCurrentWeather(ArrayList<Weather> weathers) {
        Weather currentWeather = null;
        boolean isNewData = false;
        if (null == currentWeatherLiveData.getValue()) {
            currentWeather = new Weather();
            isNewData = true;
        } else {
            currentWeather = currentWeatherLiveData.getValue();
        }
        if (!CollectionUtils.isEmpty(weathers)) {
            for (Weather weather : weathers) {
                Date date = weather.getDate();
                if (TimeUtils.isToday(date)) {
                    currentWeather.setTemperatureMax(weather.getTemperatureMax());
                    currentWeather.setTemperatureMin(weather.getTemperatureMin());
                    currentWeather.setSunrise(weather.getSunrise());
                    currentWeather.setSunset(weather.getSunset());
                    currentWeather.setMoonrise(weather.getMoonrise());
                    currentWeather.setMoonset(weather.getMoonset());
                    if (!isNewData) {
                        currentWeather.setCompleteLoad(true);
                    }
                    break;
                }
            }
        }
        currentWeatherLiveData.postValue(currentWeather);
    }


}
