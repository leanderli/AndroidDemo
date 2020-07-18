package com.leanderli.android.demo.usagestat;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.leanderli.android.demo.R;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class UsageStatTestActivity extends AppCompatActivity {

    private final static String TAG = "UsageStatTestActivity";
    private static final String PACKAGE_NAME_UNKNOWN = "unknown package name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_stat_test);
        checkUsageStateAccessPermission(this);
        Toast.makeText(this, getTopActivityPackageName(this), Toast.LENGTH_LONG).show();
    }

    public static void checkUsageStateAccessPermission(Context context) {
        if (!checkAppUsagePermission(context)) {
            requestAppUsagePermission(context);
        }
    }

    public static boolean checkAppUsagePermission(Context context) {
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        if (usageStatsManager == null) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        // try to get app usage state in last 1 min
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, currentTime - 60 * 1000, currentTime);
        return stats.size() != 0;

    }

    public static void requestAppUsagePermission(Context context) {
        Intent intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.i(TAG, "Start usage access settings activity fail!");
        }
    }

    public static String getTopActivityPackageName(@NonNull Context context) {
        final UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        if (usageStatsManager == null) {
            return PACKAGE_NAME_UNKNOWN;
        }

        String topActivityPackageName = PACKAGE_NAME_UNKNOWN;
        long time = System.currentTimeMillis();
        // 查询最后十秒钟使用应用统计数据
        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 10, time);
        // 以最后使用时间为标准进行排序
        if (usageStatsList != null) {
            SortedMap<Long, UsageStats> sortedMap = new TreeMap<Long, UsageStats>();
            for (UsageStats usageStats : usageStatsList) {
                sortedMap.put(usageStats.getLastTimeUsed(), usageStats);
            }
            if (sortedMap.size() != 0) {
                topActivityPackageName = sortedMap.get(sortedMap.lastKey()).getPackageName();
                Log.d(TAG, "Top activity package name = " + topActivityPackageName);
            }
        }

        return topActivityPackageName;
    }
}
