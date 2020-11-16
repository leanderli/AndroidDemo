package com.leanderli.android.demo.architecture.mvvm.ui.home.weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.leanderli.android.demo.R;
import com.leanderli.android.demo.architecture.mvvm.data.model.Weather;
import com.leanderli.android.demo.common.Utilities;

import java.util.ArrayList;
import java.util.Date;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder> {

    private final Context mContext;
    private final LayoutInflater layoutInflater;

    private ArrayList<Weather> weathers;

    public DailyWeatherAdapter(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setWeathers(ArrayList<Weather> weathers) {
        this.weathers = weathers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DailyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.mvvm_home_daily_weather_item, parent, false);
        return new DailyWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyWeatherViewHolder holder, int position) {
        Weather weather = weathers.get(position);
        holder.dateTextView.setText(Utilities.getLocaleDayOfWeek(TimeUtils.date2String(weather.getDate())));
        holder.maxTempTextView.setText(String.valueOf(weather.getTemperatureMax()));
        holder.minTempTextView.setText(String.valueOf(weather.getTemperatureMin()));
        Bitmap iconBm = null;
        Date sunsetTime = TimeUtils.string2Date(weather.getSunset(), "HH:ss");
        Date sunriseTime = TimeUtils.string2Date(weather.getSunrise(), "HH:ss");
        if (TimeUtils.getNowDate().after(sunsetTime) && TimeUtils.getNowDate().before(sunriseTime)) {
            iconBm = Utilities.getBitmapFromAsset(mContext, "weather_icons/" + weather.getIconNightCode() + ".png");
        } else {
            iconBm = Utilities.getBitmapFromAsset(mContext, "weather_icons/" + weather.getIconDayCode() + ".png");
        }
        if (null != iconBm) {
            holder.weatherIconImageView.setImageBitmap(iconBm);
        }
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.isEmpty(weathers) ? 0 : weathers.size();
    }

    public static class DailyWeatherViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView, minTempTextView, maxTempTextView;
        ImageView weatherIconImageView;

        public DailyWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.tv_date);
            minTempTextView = itemView.findViewById(R.id.tv_temperature_min);
            maxTempTextView = itemView.findViewById(R.id.tv_temperature_max);
            weatherIconImageView = itemView.findViewById(R.id.iv_weather_icon);
        }
    }
}
