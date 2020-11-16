package com.leanderli.android.demo.architecture.mvvm.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Weather {
    public final static int TYPE_CURRENT = 0, TYPE_3DAY = 1;

    @SerializedName(value = "temp")
    private int temperature;
    @SerializedName(value = "tempMin")
    private int temperatureMin;
    @SerializedName(value = "tempMax")
    private int temperatureMax;
    @SerializedName(value = "icon")
    private int iconCode;
    @SerializedName(value = "iconDay")
    private int iconDayCode;
    @SerializedName(value = "iconNight")
    private int iconNightCode;
    @SerializedName(value = "text")
    private String description;
    @SerializedName(value = "fxDate")
    private Date date;
    @SerializedName(value = "sunrise")
    private String sunrise;
    @SerializedName(value = "sunset")
    private String sunset;
    @SerializedName(value = "moonrise")
    private String moonrise;
    @SerializedName(value = "moonset")
    private String moonset;
    @SerializedName(value = "windSpeed")
    private int windSpeed;

    private Date updateTime;
    private int cityCode;

    private boolean completeLoad = false;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(int temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public int getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(int temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public int getIconDayCode() {
        return iconDayCode;
    }

    public void setIconDayCode(int iconDayCode) {
        this.iconDayCode = iconDayCode;
    }

    public int getIconNightCode() {
        return iconNightCode;
    }

    public void setIconNightCode(int iconNightCode) {
        this.iconNightCode = iconNightCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getIconCode() {
        return iconCode;
    }

    public void setIconCode(int iconCode) {
        this.iconCode = iconCode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public boolean isCompleteLoad() {
        return completeLoad;
    }

    public void setCompleteLoad(boolean completeLoad) {
        this.completeLoad = completeLoad;
    }
}
