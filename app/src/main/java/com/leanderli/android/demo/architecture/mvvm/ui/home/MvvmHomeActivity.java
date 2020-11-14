package com.leanderli.android.demo.architecture.mvvm.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.architecture.mvvm.data.model.Hotspot;
import com.leanderli.android.demo.architecture.mvvm.view.NestedExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MvvmHomeActivity extends AppCompatActivity {

    private SwipeRefreshLayout mainSwipeRefreshLayout;
    private TextView hitokotoContentTextView, hitikotoSourceTextView;
    private NestedExpandableListView hotspotExListView;

    private HotspotAdapter hotspotAdapter;

    private HomeViewModel homeViewModel;

    private final ArrayList<String> hotspotGroups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_home);
        initData();
        bindView();
        setView();
        bindData();
        updateView();
    }

    private void initData() {
        hotspotGroups.clear();
        hotspotGroups.add(getString(R.string.weibo_hotspot));
        hotspotGroups.add(getString(R.string.zhihu_hotspot));
        hotspotGroups.add(getString(R.string.douyin_hotspot));
    }

    private void bindView() {
        mainSwipeRefreshLayout = findViewById(R.id.srl_main);
        hitokotoContentTextView = findViewById(R.id.tv_hitokoto_content);
        hitikotoSourceTextView = findViewById(R.id.tv_hitokoto_source);
        hotspotExListView = findViewById(R.id.elv_hotspot);
    }

    private void setView() {
        /*final ImageView hitokotoRefreshImageView = findViewById(R.id.iv_refresh);
        hitokotoRefreshImageView.setOnClickListener(v -> {
            updateHitokotoUi();
        });*/

        mainSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mainSwipeRefreshLayout.setOnRefreshListener(() -> {
            updateView();
            mainSwipeRefreshLayout.setRefreshing(false);
        });

        setHotspotView();
    }

    private void setHotspotView() {
        HashMap<String, ArrayList<Hotspot>> hotspotGroupItems = new HashMap<>();
        ArrayList<Hotspot> weiboHotspots = new ArrayList<>();
        weiboHotspots.add(new Hotspot("钟南山儿子希望父亲能看到的视频", "https://s.weibo.com/weibo?q=%23%E9%92%9F%E5%8D%97%E5%B1%B1%E5%84%BF%E5%AD%90%E5%B8%8C%E6%9C%9B%E7%88%B6%E4%BA%B2%E8%83%BD%E7%9C%8B%E5%88%B0%E7%9A%84%E8%A7%86%E9%A2%91%23&Refer=index"));
        weiboHotspots.add(new Hotspot("任敏哭着说压力好大太可爱了", "https://s.weibo.com/weibo?q=%23%E4%BB%BB%E6%95%8F%E5%93%AD%E7%9D%80%E8%AF%B4%E5%8E%8B%E5%8A%9B%E5%A5%BD%E5%A4%A7%E5%A4%AA%E5%8F%AF%E7%88%B1%E4%BA%86%23&Refer=index"));
        weiboHotspots.add(new Hotspot("特朗普金色头发变得花白", "https://s.weibo.com/weibo?q=%23%E7%89%B9%E6%9C%97%E6%99%AE%E9%87%91%E8%89%B2%E5%A4%B4%E5%8F%91%E5%8F%98%E5%BE%97%E8%8A%B1%E7%99%BD%23&Refer=index"));

        ArrayList<Hotspot> zhihuHotspots = new ArrayList<>();
        zhihuHotspots.add(new Hotspot("美媒测算大选全美票数", "https://www.zhihu.com/search?q=%E7%BE%8E%E5%9B%BD%E5%A4%A7%E9%80%89&utm_content=search_hot&type=content"));
        zhihuHotspots.add(new Hotspot("外交部向拜登表示祝贺", "https://www.zhihu.com/search?q=%E5%A4%96%E4%BA%A4%E9%83%A8%E7%A5%9D%E8%B4%BA%E6%8B%9C%E7%99%BB&utm_content=search_hot&type=content"));
        zhihuHotspots.add(new Hotspot("特朗普禁止投资部分中企", "https://www.zhihu.com/search?q=%E7%89%B9%E6%9C%97%E6%99%AE&utm_content=search_hot&type=content"));

        ArrayList<Hotspot> douyinspots = new ArrayList<>();
        douyinspots.add(new Hotspot("拜登特朗普支持者持枪对峙", null));
        douyinspots.add(new Hotspot("任敏哭着说压力好大太可爱了", null));
        douyinspots.add(new Hotspot("丁真说不想当明星", null));

        hotspotGroupItems.put(getString(R.string.weibo_hotspot), weiboHotspots);
        hotspotGroupItems.put(getString(R.string.zhihu_hotspot), zhihuHotspots);
        hotspotGroupItems.put(getString(R.string.douyin_hotspot), douyinspots);

        hotspotAdapter = new HotspotAdapter(this, hotspotGroups, hotspotGroupItems);
        hotspotExListView.setAdapter(hotspotAdapter);
    }

    private void bindData() {
        homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory()).get(HomeViewModel.class);
    }

    private void updateView() {
        updateHitokotoUi();

    }

    private void updateHitokotoUi() {
        homeViewModel.getHitokoto().observe(this, hitokoto -> {
            hitokotoContentTextView.setText(hitokoto.getContent());
            hitikotoSourceTextView.setText(getString(R.string.middle_line) + hitokoto.getSource());
        });
    }
}