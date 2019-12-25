package com.leanderli.android.demo.launcher;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.common.OnItemClickListener;
import com.leanderli.android.demo.customizeviews.LlDrawableTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LauncherAppTestActivity extends AppCompatActivity {

    RecyclerView appListView;
    AppListViewAdapter appListViewAdapter;
    RecyclerView.LayoutManager layoutManager;

    private Context mContext;
    private PackageManager mPackageManager;

    private static final Rect sOldBounds = new Rect();
    private static final Canvas sCanvas = new Canvas();

    static {
        sCanvas.setDrawFilter(new PaintFlagsDrawFilter(Paint.DITHER_FLAG,
                Paint.FILTER_BITMAP_FLAG));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mPackageManager = this.getPackageManager();

        setContentView(R.layout.activity_launcher_app_test);
        appListView = findViewById(R.id.rv_app_list);

        final ArrayList<AppInfo> appInfos = getSystemApp(this);
        setUpView(appInfos);

//        if (null != appInfos && appInfos.size() > 0) {
//            layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
//            appListViewAdapter = new AppListViewAdapter(appInfos, this);
//            appListView.setAdapter(appListViewAdapter);
//            appListView.setLayoutManager(layoutManager);
//
//            appListViewAdapter.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, int position) {
//                    AppInfo appInfo = appInfos.get(position);
//                    ComponentName componentName = new ComponentName(appInfo.getPackageName(), appInfo.getClassName());
//                    Intent intent = new Intent();
//                    intent.setComponent(componentName);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
//
//                @Override
//                public void onItemLongClick(View view, int position) {
//
//                }
//            });
//        }

    }

    private void setUpView(final ArrayList<AppInfo> appInfos) {
        layoutManager = new GridLayoutManager(this, 4, OrientationHelper.VERTICAL, false);
        AppGridViewAdapter adapter = new AppGridViewAdapter(this);
        adapter.setDataSetChanged(appInfos);
        appListView.setLayoutManager(layoutManager);
        appListView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AppInfo appInfo = appInfos.get(position);
                ComponentName componentName = new ComponentName(appInfo.getPackageName(), appInfo.getClassName());
                startLaunchIntent(view, componentName);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    public void startLaunchIntent(View view, ComponentName componentName) {
        try {
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ActivityCompat.startActivity(this, intent, getActivityLaunchOptions(view));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public Bundle getActivityLaunchOptions(View v) {
        if (Utilities.ATLEAST_MARSHMALLOW) {
            int left = 0, top = 0;
            int width = v.getMeasuredWidth(), height = v.getMeasuredHeight();
            if (v instanceof LlDrawableTextView) {
                // Launch from center of icon, not entire view
                Drawable icon = ((LlDrawableTextView) v).getIcon();
                if (icon != null) {
                    Rect bounds = icon.getBounds();
                    left = (width - bounds.width()) / 2;
                    top = v.getPaddingTop();
                    width = bounds.width();
                    height = bounds.height();
                }
            }
            return ActivityOptions.makeClipRevealAnimation(v, left, top, width, height).toBundle();
        } else {
            return null;
        }
    }

    public ArrayList<AppInfo> getSystemApp(Context context) {
        PackageManager mPackageManager = context.getApplicationContext().getPackageManager();
        LauncherApps mLauncherApps = (LauncherApps) context.getSystemService(Context.LAUNCHER_APPS_SERVICE);
        UserManager mUserManager = (UserManager) context.getSystemService(Context.USER_SERVICE);
        List<UserHandle> users = mUserManager.getUserProfiles();
        List<UserHandle> profiles = users == null ? Collections.<UserHandle>emptyList() : users;

        //根据手机所有用户获取每个用户下的应用
        for (UserHandle user : profiles) {
            // Query for the set of apps
            final List<LauncherActivityInfo> apps = mLauncherApps.getActivityList(null, user);
            // Fail if we don't have any apps
            // TODO: Fix this. Only fail for the current user.
            if (apps == null || apps.isEmpty()) {
                continue;
            }
            // Create the ApplicationInfos

            ArrayList<AppInfo> appInfos = new ArrayList<AppInfo>(16);
            for (int i = 0; i < apps.size(); i++) {
                LauncherActivityInfo app = apps.get(i);
                // This builds the icon bitmaps.
                ComponentName componentName = app.getComponentName();
                String appName = getSystemApplicationName(componentName.getPackageName(), mPackageManager);
                if (!TextUtils.isEmpty(appName)) {
                    AppInfo appInfo = new AppInfo();
                    appInfo.setAppName(appName);
                    Drawable iconDrawable = app.getIcon(640);
                    appInfo.setAppIcon(iconDrawable);
                    appInfo.setPackageName(componentName.getPackageName());
                    appInfo.setClassName(componentName.getClassName());
                    appInfos.add(appInfo);
                }
            }
            return appInfos;
        }
        return null;
    }

    private static final float ICON_SIZE_DEFINED_IN_APP_DP = 48;

    private int getLauncherIconDensity(int requiredSize) {
        // Densities typically defined by an app.
        int[] densityBuckets = new int[]{
                DisplayMetrics.DENSITY_LOW,
                DisplayMetrics.DENSITY_MEDIUM,
                DisplayMetrics.DENSITY_TV,
                DisplayMetrics.DENSITY_HIGH,
                DisplayMetrics.DENSITY_XHIGH,
                DisplayMetrics.DENSITY_XXHIGH,
                DisplayMetrics.DENSITY_XXXHIGH
        };

        int density = DisplayMetrics.DENSITY_XXXHIGH;
        for (int i = densityBuckets.length - 1; i >= 0; i--) {
            float expectedSize = ICON_SIZE_DEFINED_IN_APP_DP * densityBuckets[i]
                    / DisplayMetrics.DENSITY_DEFAULT;
            if (expectedSize >= requiredSize) {
                density = densityBuckets[i];
            }
        }

        return density;
    }

    public static String getSystemApplicationName(String packageName, PackageManager packageManager) {
        String applicationName = null;
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            //filter system app
//            if ((applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0 ||
//                    (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
//                applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
//            }
            applicationName = (String) packageManager.getApplicationLabel(applicationInfo);

        } catch (PackageManager.NameNotFoundException e) {

        }
        return applicationName;
    }

    private int mIconDpi = 500;

    private Drawable getFullResDefaultActivityIcon() {
        return getFullResIcon(Resources.getSystem(), Utilities.ATLEAST_OREO ?
                android.R.drawable.sym_def_app_icon : android.R.mipmap.sym_def_app_icon);
    }

    private Drawable getFullResIcon(Resources resources, int iconId) {
        Drawable d;
        try {
            d = resources.getDrawableForDensity(iconId, mIconDpi);
        } catch (Resources.NotFoundException e) {
            d = null;
        }

        return (d != null) ? d : getFullResDefaultActivityIcon();
    }

    public Drawable getFullResIcon(String packageName, int iconId) {
        Resources resources;
        try {
            resources = mPackageManager.getResourcesForApplication(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            resources = null;
        }
        if (resources != null) {
            if (iconId != 0) {
                return getFullResIcon(resources, iconId);
            }
        }
        return getFullResDefaultActivityIcon();
    }

    public Drawable getFullResIcon(ActivityInfo info) {
        Resources resources;
        try {
            resources = mPackageManager.getResourcesForApplication(
                    info.applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            resources = null;
        }
        if (resources != null) {
            int iconId = info.getIconResource();
            if (iconId != 0) {
                return getFullResIcon(resources, iconId);
            }
        }

        return getFullResDefaultActivityIcon();
    }

    public Drawable getFullResIcon(LauncherActivityInfo info) {
        return getFullResIcon(info, true);
    }

    public Drawable getFullResIcon(LauncherActivityInfo info, boolean flattenDrawable) {
        return getIcon(info, mIconDpi, flattenDrawable);
    }

    public Drawable getIcon(LauncherActivityInfo info, int iconDpi, boolean flattenDrawable) {
        return info.getIcon(iconDpi);
    }

    protected Bitmap makeDefaultIcon(UserHandle user) {
        Drawable unbadged = getFullResDefaultActivityIcon();
        return createBadgedIconBitmap(unbadged, user, mContext, Build.VERSION_CODES.O);
    }

    public static Bitmap createBadgedIconBitmap(
            Drawable icon, UserHandle user, Context context, int iconAppTargetSdk) {

        IconNormalizer normalizer;
        float scale = 1f;
        if (!FeatureFlags.LAUNCHER3_DISABLE_ICON_NORMALIZATION) {
            normalizer = IconNormalizer.getInstance(context);
            if (Utilities.ATLEAST_OREO && iconAppTargetSdk >= Build.VERSION_CODES.O) {
                boolean[] outShape = new boolean[1];
                AdaptiveIconDrawable dr = (AdaptiveIconDrawable)
                        context.getDrawable(R.drawable.adaptive_icon_drawable_wrapper).mutate();
                dr.setBounds(0, 0, 1, 1);
                scale = normalizer.getScale(icon, null, dr.getIconMask(), outShape);
                if (FeatureFlags.LEGACY_ICON_TREATMENT &&
                        !outShape[0]) {
                    Drawable wrappedIcon = wrapToAdaptiveIconDrawable(context, icon, scale);
                    if (wrappedIcon != icon) {
                        icon = wrappedIcon;
                        scale = normalizer.getScale(icon, null, null, null);
                    }
                }
            } else {
                scale = normalizer.getScale(icon, null, null, null);
            }
        }
        Bitmap bitmap = createIconBitmap(icon, context, scale);
        if (FeatureFlags.ADAPTIVE_ICON_SHADOW && Utilities.ATLEAST_OREO &&
                icon instanceof AdaptiveIconDrawable) {
            bitmap = ShadowGenerator.getInstance(context).recreateIcon(bitmap);
        }
        return badgeIconForUser(bitmap, user, context);
    }

    public static Bitmap badgeIconForUser(Bitmap icon, UserHandle user, Context context) {
        if (user != null && !Process.myUserHandle().equals(user)) {
            BitmapDrawable drawable = new FixedSizeBitmapDrawable(icon);
            Drawable badged = context.getPackageManager().getUserBadgedIcon(
                    drawable, user);
            if (badged instanceof BitmapDrawable) {
                return ((BitmapDrawable) badged).getBitmap();
            } else {
                return createIconBitmap(badged, context);
            }
        } else {
            return icon;
        }
    }

    private static class FixedSizeBitmapDrawable extends BitmapDrawable {

        public FixedSizeBitmapDrawable(Bitmap bitmap) {
            super(null, bitmap);
        }

        @Override
        public int getIntrinsicHeight() {
            return getBitmap().getWidth();
        }

        @Override
        public int getIntrinsicWidth() {
            return getBitmap().getWidth();
        }
    }

    public static Bitmap createIconBitmap(Drawable icon, Context context) {
        float scale = 1f;
        if (FeatureFlags.ADAPTIVE_ICON_SHADOW && Utilities.ATLEAST_OREO &&
                icon instanceof AdaptiveIconDrawable) {
            scale = ShadowGenerator.getScaleForBounds(new RectF(0, 0, 0, 0));
        }
        Bitmap bitmap = createIconBitmap(icon, context, scale);
        if (FeatureFlags.ADAPTIVE_ICON_SHADOW && Utilities.ATLEAST_OREO &&
                icon instanceof AdaptiveIconDrawable) {
            bitmap = ShadowGenerator.getInstance(context).recreateIcon(bitmap);
        }
        return bitmap;
    }

    @SuppressLint("WrongConstant")
    public static Bitmap createIconBitmap(Drawable icon, Context context, float scale) {
        synchronized (sCanvas) {
            final int iconBitmapSize = 120;
            int width = iconBitmapSize;
            int height = iconBitmapSize;

            if (icon instanceof PaintDrawable) {
                PaintDrawable painter = (PaintDrawable) icon;
                painter.setIntrinsicWidth(width);
                painter.setIntrinsicHeight(height);
            } else if (icon instanceof BitmapDrawable) {
                // Ensure the bitmap has a density.
                BitmapDrawable bitmapDrawable = (BitmapDrawable) icon;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap != null && bitmap.getDensity() == Bitmap.DENSITY_NONE) {
                    bitmapDrawable.setTargetDensity(context.getResources().getDisplayMetrics());
                }
            }

            int sourceWidth = icon.getIntrinsicWidth();
            int sourceHeight = icon.getIntrinsicHeight();
            if (sourceWidth > 0 && sourceHeight > 0) {
                // Scale the icon proportionally to the icon dimensions
                final float ratio = (float) sourceWidth / sourceHeight;
                if (sourceWidth > sourceHeight) {
                    height = (int) (width / ratio);
                } else if (sourceHeight > sourceWidth) {
                    width = (int) (height * ratio);
                }
            }
            // no intrinsic size --> use default size
            int textureWidth = iconBitmapSize;
            int textureHeight = iconBitmapSize;

            Bitmap bitmap = Bitmap.createBitmap(textureWidth, textureHeight,
                    Bitmap.Config.ARGB_8888);
            final Canvas canvas = sCanvas;
            canvas.setBitmap(bitmap);

            final int left = (textureWidth - width) / 2;
            final int top = (textureHeight - height) / 2;

            sOldBounds.set(icon.getBounds());
            if (Utilities.ATLEAST_OREO && icon instanceof AdaptiveIconDrawable) {
                int offset = Math.max((int) (ShadowGenerator.BLUR_FACTOR * iconBitmapSize),
                        Math.min(left, top));
                int size = Math.max(width, height);
                icon.setBounds(offset, offset, size, size);
            } else {
                icon.setBounds(left, top, left + width, top + height);
            }
            canvas.save();
            canvas.scale(scale, scale, textureWidth / 2, textureHeight / 2);
            icon.draw(canvas);
            canvas.restore();
            icon.setBounds(sOldBounds);
            canvas.setBitmap(null);

            return bitmap;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static Drawable wrapToAdaptiveIconDrawable(Context context, Drawable drawable, float scale) {
        if (!(FeatureFlags.LEGACY_ICON_TREATMENT && Utilities.ATLEAST_OREO)) {
            return drawable;
        }

        try {
            if (!(drawable instanceof AdaptiveIconDrawable)) {
                AdaptiveIconDrawable iconWrapper = (AdaptiveIconDrawable)
                        context.getDrawable(R.drawable.adaptive_icon_drawable_wrapper).mutate();
                FixedScaleDrawable fsd = ((FixedScaleDrawable) iconWrapper.getForeground());
                fsd.setDrawable(drawable);
                fsd.setScale(scale);
                return (Drawable) iconWrapper;
            }
        } catch (Exception e) {
            return drawable;
        }
        return drawable;
    }

}
