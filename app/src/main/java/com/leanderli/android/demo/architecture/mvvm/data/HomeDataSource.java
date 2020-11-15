package com.leanderli.android.demo.architecture.mvvm.data;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotItem;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotType;
import com.leanderli.android.demo.architecture.mvvm.data.model.Weather;
import com.leanderli.android.demo.common.http.AsyncDataLoaderCallback;
import com.leanderli.android.demo.common.http.AsyncHttp;
import com.leanderli.android.demo.architecture.mvvm.data.model.Hitokoto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 获取远程调用的数据信息
 */
public class HomeDataSource {
    private final static String HITOKOTO_URL = "https://cloudgw.api.dailypics.cn/release/tu_hitokoto";
    private final static String WEIBO_HOTSPOT = "https://tenapi.cn/resou";
    private final static String ZHIHU_HOTSPOT = "https://tenapi.cn/zhihuresou";
    private final static String DOUYIN_HOTSPOT = "https://tenapi.cn/douyinresou";
    private final static String CURRENT_WEATHER_URL = "https://devapi.qweather.com/v7/weather/now?location=#{cityCode}&key=ddf7eca1b5fa4ffc9db89becfc0c53b4";
    private final static String _3DAY_WEATHER_URL = "https://devapi.qweather.com/v7/weather/3d?location=#{cityCode}&key=ddf7eca1b5fa4ffc9db89becfc0c53b4";

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

    public void getHotspot(int hotspotType, DataSourceCallback callback) {
        String url = "";
        switch (hotspotType) {
            case HotspotType.TYPE_WEIBO:
                url = WEIBO_HOTSPOT;
                break;
            case HotspotType.TYPE_ZHIHU:
                url = ZHIHU_HOTSPOT;
                break;
            case HotspotType.TYPE_DOUYIN:
                url = DOUYIN_HOTSPOT;
                break;
        }
        new AsyncHttp().get(url, new AsyncDataLoaderCallback<String>() {
            @Override
            public void onSuccess(String data) {
                LogUtils.d(data);
                try {
                    JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
                    int dataCode = jsonObject.get("data").getAsInt();
                    if (200 == dataCode) {
                        ArrayList<HotspotItem> hotspotItems = new ArrayList<>();
                        JsonArray jsonArray = jsonObject.getAsJsonArray("list");
                        if (null != jsonArray && jsonArray.size() > 0) {
                            HotspotItem hotspotItem = null;
                            for (JsonElement jsonElement : jsonArray) {
                                if (jsonElement.isJsonObject()) {
                                    JsonObject arrayItemObject = jsonElement.getAsJsonObject();
                                    hotspotItem = new HotspotItem();
                                    hotspotItem.setType(hotspotType);
                                    if (hotspotType == HotspotType.TYPE_ZHIHU) {
                                        hotspotItem.setName(arrayItemObject.get("query").getAsString());
                                    } else {
                                        hotspotItem.setName(arrayItemObject.get("name").getAsString());
                                        hotspotItem.setHot(arrayItemObject.get("hot").getAsInt());
                                    }
                                    if (hotspotType != HotspotType.TYPE_DOUYIN) {
                                        hotspotItem.setUrl(arrayItemObject.get("url").getAsString());
                                    }
                                    hotspotItems.add(hotspotItem);
                                }
                            }
                        }
                        callback.onSuccess(new Result.Success<>(hotspotItems));
                    } else {
                        callback.onError(new Result.Error(new RuntimeException("Unknown error.Error code " + dataCode)));
                    }
                } catch (Exception e) {
                    LogUtils.d(e);
                    callback.onError(new Result.Error(new RuntimeException("Unknown error ")));
                }
            }

            @Override
            public void onFailure(String msg) {
                LogUtils.d(msg);
                callback.onError(new Result.Error(new RuntimeException(msg)));
            }
        });
    }

    public void getWeather(int weatherType, int cityCode, DataSourceCallback callback) {
        String url = "";
        if (weatherType == Weather.TYPE_CURRENT) {
            url = CURRENT_WEATHER_URL.replace("#{cityCode}", cityCode + "");
        } else if (weatherType == Weather.TYPE_3DAY) {
            url = _3DAY_WEATHER_URL.replace("#{cityCode}", cityCode + "");
        }
        new AsyncHttp().get(url, new AsyncDataLoaderCallback<String>() {
            @Override
            public void onSuccess(String data) {
                LogUtils.d(data);
                try {
                    JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
                    String code = jsonObject.get("code").getAsString();
                    if ("200".equals(code)) {
                        if (weatherType == Weather.TYPE_CURRENT) {
//                            String updateTimeStr = jsonObject.get("updateTime").getAsString();
//                            @SuppressLint("SimpleDateFormat")
//                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                            Date updateTime = simpleDateFormat.parse(updateTimeStr);

                            Weather weather = new Gson().fromJson(jsonObject.get("now"), Weather.class);
//                            weather.setUpdateTime(updateTime);
                            weather.setCityCode(cityCode);
                            callback.onSuccess(new Result.Success<>(weather));
                        } else if (weatherType == Weather.TYPE_3DAY) {
                            ArrayList<Weather> weathers = new Gson().fromJson(jsonObject.get("daily"), new TypeToken<ArrayList<Weather>>() {
                            }.getType());
                            callback.onSuccess(new Result.Success<>(weathers));
                        }
                    } else {
                        callback.onError(new Result.Error(new RuntimeException("Unknown error.Error code " + code)));
                    }
                } catch (Exception e) {
                    LogUtils.d(e);
                    callback.onError(new Result.Error(new RuntimeException("Unknown error ")));
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
