package com.leanderli.android.demo.launcher;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * @Description
 * @Author ls573
 * @Date 18.8.9
 */

public class AppInfo {

    private String appName;

    private Drawable appIcon;

    private String packageName;

    private String className;

    private Bitmap iconBitmap;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Bitmap getIconBitmap() {
        return iconBitmap;
    }

    public void setIconBitmap(Bitmap iconBitmap) {
        this.iconBitmap = iconBitmap;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
