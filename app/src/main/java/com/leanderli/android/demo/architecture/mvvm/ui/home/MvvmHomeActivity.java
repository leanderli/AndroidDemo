package com.leanderli.android.demo.architecture.mvvm.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.leanderli.android.demo.R;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotItem;
import com.leanderli.android.demo.architecture.mvvm.data.model.HotspotType;
import com.leanderli.android.demo.architecture.mvvm.data.model.Weather;
import com.leanderli.android.demo.architecture.mvvm.view.NestedExpandableListView;
import com.leanderli.android.demo.common.Utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MvvmHomeActivity extends AppCompatActivity {

    private SwipeRefreshLayout mainSwipeRefreshLayout;
    private TextView hitokotoContentTextView, hitikotoSourceTextView;
    private NestedExpandableListView hotspotExListView;
    private TextView locationTextView, weatherDescTextView, temperatureRangeTextView,
            temperatureTextView, moreWeatherInfoTextView;
    private ImageView locationImageView, weatherIconImageView;
    private RecyclerView threeDayWeatherRv;

    private HotspotAdapter hotspotAdapter;
    private DailyWeatherAdapter dailyWeatherAdapter;

    private HomeViewModel homeViewModel;

    private final ArrayList<HotspotType> hotspotTypes = new ArrayList<>();
    private final HashMap<Integer, ArrayList<HotspotItem>> hotspotItems = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_home);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        initData();
        bindView();
        setView();
        bindData();
        updateView();
    }

    private void initData() {
        hotspotTypes.clear();
        hotspotTypes.add(new HotspotType(HotspotType.TYPE_WEIBO, getString(R.string.weibo_hotspot), getDrawable(R.drawable.ic_weibo_128)));
        hotspotTypes.add(new HotspotType(HotspotType.TYPE_ZHIHU, getString(R.string.zhihu_hotspot), getDrawable(R.drawable.ic_zhihu_128)));
        hotspotTypes.add(new HotspotType(HotspotType.TYPE_DOUYIN, getString(R.string.douyin_hotspot), getDrawable(R.drawable.ic_douyin)));

        hotspotItems.clear();
    }

    private void bindView() {
        mainSwipeRefreshLayout = findViewById(R.id.srl_main);

        hitokotoContentTextView = findViewById(R.id.tv_hitokoto_content);
        hitikotoSourceTextView = findViewById(R.id.tv_hitokoto_source);
        hotspotExListView = findViewById(R.id.elv_hotspot);

        locationTextView = findViewById(R.id.tv_location);
        weatherDescTextView = findViewById(R.id.tv_weather_description);
        temperatureRangeTextView = findViewById(R.id.tv_temperature_range);
        temperatureTextView = findViewById(R.id.tv_temperature);
        locationImageView = findViewById(R.id.iv_location_icon);
        weatherIconImageView = findViewById(R.id.iv_weather_icon);
        threeDayWeatherRv = findViewById(R.id.rv_3daily_broadcast);
        moreWeatherInfoTextView = findViewById(R.id.tv_more_weather_info);
    }

    private void setView() {
        /*final ImageView hitokotoRefreshImageView = findViewById(R.id.iv_refresh);
        hitokotoRefreshImageView.setOnClickListener(v -> {
            updateHitokotoUi();
        });*/
        setHotspotView();
        setWeatherView();
        mainSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mainSwipeRefreshLayout.setOnRefreshListener(() -> {
            updateView();
            mainSwipeRefreshLayout.setRefreshing(false);
        });

    }

    private void setWeatherView() {
        threeDayWeatherRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        dailyWeatherAdapter = new DailyWeatherAdapter(this);
        threeDayWeatherRv.setAdapter(dailyWeatherAdapter);

        moreWeatherInfoTextView.setOnClickListener((v) -> {
            Utilities.openLinkByBrowser(this, getString(R.string.more_weather_info_link));
        });
    }

    private void setHotspotView() {
        hotspotAdapter = new HotspotAdapter(this, hotspotTypes, hotspotItems);
        hotspotExListView.setAdapter(hotspotAdapter);
    }

    private void bindData() {
        homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory()).get(HomeViewModel.class);
    }

    private void updateView() {
        updateHitokotoUi();
        updateHotspotUi();
        updateCurrentWeatherUi();
    }

    private void updateHitokotoUi() {
        homeViewModel.getHitokoto().observe(this, hitokoto -> {
            hitokotoContentTextView.setText(hitokoto.getContent());
            hitikotoSourceTextView.setText(hitokoto.getSource());
        });
    }

    private void updateHotspotUi() {
        homeViewModel.getHotspots(HotspotType.TYPE_WEIBO);
        homeViewModel.getWeiboHotspot().observe(this, hotspots -> {
            hotspotItems.put(HotspotType.TYPE_WEIBO, hotspots);
            hotspotAdapter.notifyDataSetChanged();
        });

        homeViewModel.getHotspots(HotspotType.TYPE_ZHIHU);
        homeViewModel.getZhihuHotspot().observe(this, hotspots -> {
            hotspotItems.put(HotspotType.TYPE_ZHIHU, hotspots);
            hotspotAdapter.notifyDataSetChanged();
        });

        homeViewModel.getHotspots(HotspotType.TYPE_DOUYIN);
        homeViewModel.getDouyinHotspot().observe(this, hotspots -> {
            hotspotItems.put(HotspotType.TYPE_DOUYIN, hotspots);
            hotspotAdapter.notifyDataSetChanged();
        });
    }

    private void updateCurrentWeatherUi() {
        homeViewModel.getCurrentWeather(101020100).observe(this, weather -> {
            locationTextView.setText("上海市");
            weatherDescTextView.setText(weather.getDescription());
            temperatureTextView.setText(weather.getTemperature() + getString(R.string.celsius_symbol));
            weatherIconImageView.setImageBitmap(Utilities.getBitmapFromAsset(this, "weather_icons/" + weather.getIconCode() + ".png"));
        });
        homeViewModel.getThreeDayWeather(101020100).observe(this, weathers -> {
            if (!CollectionUtils.isEmpty(weathers)) {
                for (Weather weather : weathers) {
                    Date date = weather.getDate();
                    if (TimeUtils.isToday(date)) {
                        String todayTempRange = getString(R.string.temperature_max) + weather.getTemperatureMax() + getString(R.string.celsius_symbol)
                                + " "
                                + getString(R.string.temperature_min) + weather.getTemperatureMin() + getString(R.string.celsius_symbol);
                        temperatureRangeTextView.setText(todayTempRange);
                        break;
                    }
                }
                dailyWeatherAdapter.setWeathers(weathers);
            }
        });
    }
}