package android.slc.commonlibrary.util.compat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.drawerlayout.widget.DrawerLayout;

import java.lang.reflect.Method;

import android.slc.commonlibrary.R;

import com.blankj.utilcode.util.Utils;

import static android.Manifest.permission.EXPAND_STATUS_BAR;

/**
 * 状态栏和导航栏状态操作工具
 */
public final class SlcBarCompatUtils {

    ///////////////////////////////////////////////////////////////////////////
    // status bar
    ///////////////////////////////////////////////////////////////////////////
    public static final int DEFAULT_STATUS_BAR_ALPHA = 64;

    private static final int FAKE_STATUS_BAR_VIEW_ID = R.id.statusbarutil_fake_status_bar_view;
    private static final int FAKE_TRANSLUCENT_VIEW_ID = R.id.statusbarutil_translucent_view;
    private static final String TAG_STATUS_BAR = "TAG_STATUS_BAR";
    private static final String TAG_OFFSET = "TAG_OFFSET";
    private static final int KEY_OFFSET = -123;

    private SlcBarCompatUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取状态栏高度
     *
     * @return 返回高度像素
     */
    public static int getStatusBarHeight() {
        Resources resources = Utils.getApp().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 设置状态栏是否可见
     *
     * @param activity  当前activity
     * @param isVisible 是否可见 true可见，false不可见
     */
    public static void setStatusBarVisibility(@NonNull final Activity activity,
                                              final boolean isVisible) {
        setStatusBarVisibility(activity.getWindow(), isVisible);
    }

    /**
     * 设置状态栏是否可见
     *
     * @param window    当前window
     * @param isVisible 是否可见 true可见，false不可见
     */
    public static void setStatusBarVisibility(@NonNull final Window window,
                                              final boolean isVisible) {
        if (isVisible) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 状态栏是否可见
     *
     * @param activity 当前activity
     * @return 返回是否可见，true代表可见，false反之
     */
    public static boolean isStatusBarVisible(@NonNull final Activity activity) {
        int flags = activity.getWindow().getAttributes().flags;
        return (flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == 0;
    }

    /**
     * 设置状态栏为高亮模式
     * （此处高亮模式指状态栏背景为亮色，字体为黑色）
     *
     * @param activity    当前activity
     * @param isLightMode 是否为高亮模式
     */
    public static void setStatusBarLightMode(@NonNull final Activity activity,
                                             final boolean isLightMode) {
        setStatusBarLightMode(activity.getWindow(), isLightMode);
    }

    /**
     * 设置状态栏为高亮模式
     * （此处高亮模式指状态栏背景为亮色，字体为黑色）
     *
     * @param window      当前window
     * @param isLightMode 是否为高亮模式
     */
    public static void setStatusBarLightMode(@NonNull final Window window,
                                             final boolean isLightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            int vis = decorView.getSystemUiVisibility();
            if (isLightMode) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(vis);
        }
    }

    /**
     * 判断状态栏是否为高亮模式
     *
     * @param activity 当前activity
     * @return 返回true为高亮模式，false则反之
     */
    public static boolean isStatusBarLightMode(@NonNull final Activity activity) {
        return isStatusBarLightMode(activity.getWindow());
    }

    /**
     * @param window
     * @return
     */
    public static boolean isStatusBarLightMode(@NonNull final Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            int vis = decorView.getSystemUiVisibility();
            return (vis & View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) != 0;
        }
        return false;
    }

    /**
     * 设置布局全屏
     *
     * @param activity           The activity.
     * @param isLayoutFullscreen
     */
    public static void setLayoutFullscreen(@NonNull final Activity activity, final boolean isLayoutFullscreen) {
        setLayoutFullscreen(activity.getWindow(), isLayoutFullscreen);
    }

    /**
     * 设置布局全屏
     *
     * @param window             The window.
     * @param isLayoutFullscreen
     */
    public static void setLayoutFullscreen(@NonNull final Window window, final boolean isLayoutFullscreen) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return;
        View decorView = window.getDecorView();
        int vis = decorView.getSystemUiVisibility();
        if (isLayoutFullscreen) {
            vis |= (View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            vis &= ~(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        decorView.setSystemUiVisibility(vis);
    }

    /**
     * 是否是布局全屏
     *
     * @param activity
     * @return
     */
    public static boolean isLayoutFullscreen(@NonNull final Activity activity) {
        return isLayoutFullscreen(activity.getWindow());
    }

    /**
     * 是否是布局全屏
     *
     * @param window
     * @return
     */
    public static boolean isLayoutFullscreen(@NonNull final Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = window.getDecorView();
            int vis = decorView.getSystemUiVisibility();
            return (vis & (View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)) != 0;
        }
        return false;
    }

    /**
     * Add the top margin size equals status bar's height for view.
     *
     * @param view The view.
     */
    public static void addMarginTopEqualStatusBarHeight(@NonNull View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        view.setTag(TAG_OFFSET);
        Object haveSetOffset = view.getTag(KEY_OFFSET);
        if (haveSetOffset != null && (Boolean) haveSetOffset) return;
        MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin,
                layoutParams.topMargin + getStatusBarHeight(),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
        view.setTag(KEY_OFFSET, true);
    }

    /**
     * Subtract the top margin size equals status bar's height for view.
     *
     * @param view The view.
     */
    public static void subtractMarginTopEqualStatusBarHeight(@NonNull View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        Object haveSetOffset = view.getTag(KEY_OFFSET);
        if (haveSetOffset == null || !(Boolean) haveSetOffset) return;
        MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin,
                layoutParams.topMargin - getStatusBarHeight(),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
        view.setTag(KEY_OFFSET, false);
    }

    /*private static void addMarginTopEqualStatusBarHeight(final Window window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        View withTag = window.getDecorView().findViewWithTag(TAG_OFFSET);
        if (withTag == null) return;
        addMarginTopEqualStatusBarHeight(withTag);
    }

    private static void subtractMarginTopEqualStatusBarHeight(final Window window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        View withTag = window.getDecorView().findViewWithTag(TAG_OFFSET);
        if (withTag == null) return;
        subtractMarginTopEqualStatusBarHeight(withTag);
    }*/

    /**
     * 设置状态栏透明
     */
    public static void setStatusBarTransparent(Activity activity) {
        setStatusBarColor(activity, Color.TRANSPARENT);
    }

    /**
     * 设置状态栏透明
     */
    public static void setStatusBarTransparent(Window window) {
        setStatusBarColor(window, Color.TRANSPARENT);
    }

    /**
     * 设置状态栏半透明
     */
    public static void setStatusBarTranslucent(Activity activity) {
        setStatusBarTranslucent(activity, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏半透明
     */
    public static void setStatusBarTranslucent(Activity activity, @IntRange(from = 0, to = 255) int alpha) {
        setStatusBarTranslucent(activity.getWindow(), alpha);
    }

    /**
     * 设置状态栏半透明
     */
    public static void setStatusBarTranslucent(Window window) {
        setStatusBarTranslucent(window, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏半透明
     */
    public static void setStatusBarTranslucent(Window window, @IntRange(from = 0, to = 255) int alpha) {
        setStatusBarColor(window, calculateStatusColor(Color.TRANSPARENT, alpha));
    }

    /**
     * Set the status bar's color.
     *
     * @param activity The activity.
     * @param color    The status bar's color.
     */
    public static void setStatusBarColor(@NonNull final Activity activity,
                                         @ColorInt final int color) {
        setStatusBarColor(activity, color, false);
    }

    /**
     * Set the status bar's color.
     *
     * @param activity The activity.
     * @param color    The status bar's color.
     * @param isDecor  True to add fake status bar in DecorView,
     *                 false to add fake status bar in ContentView.
     */
    public static void setStatusBarColor(@NonNull final Activity activity,
                                         @ColorInt final int color,
                                         final boolean isDecor) {
        setStatusBarColor(activity.getWindow(), color, isDecor);
    }


    /**
     * Set the status bar's color.
     *
     * @param window The window.
     * @param color  The status bar's color.
     */
    public static void setStatusBarColor(@NonNull final Window window,
                                         @ColorInt final int color) {
        setStatusBarColor(window, color, false);
    }

    /**
     * Set the status bar's color.
     *
     * @param window  The window.
     * @param color   The status bar's color.
     * @param isDecor True to add fake status bar in DecorView,
     *                false to add fake status bar in ContentView.
     */
    public static void setStatusBarColor(@NonNull final Window window,
                                         @ColorInt final int color,
                                         final boolean isDecor) {
        applyStatusBarColor(window, color, isDecor);
    }

    /**
     * Set the status bar's color.
     *
     * @param fakeStatusBar The fake status bar view.
     * @param color         The status bar's color.
     */
    @Deprecated
    public static void setStatusBarColor(@NonNull final View fakeStatusBar,
                                         @ColorInt final int color) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        Activity activity = getActivityByView(fakeStatusBar);
        if (activity == null) return;
        //transparentStatusBar(activity);
        fakeStatusBar.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = fakeStatusBar.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = getStatusBarHeight();
        fakeStatusBar.setBackgroundColor(color);
    }

    /**
     * Set the custom status bar.
     *
     * @param fakeStatusBar The fake status bar view.
     */
    @Deprecated
    public static void setStatusBarCustom(@NonNull final View fakeStatusBar) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        Activity activity = getActivityByView(fakeStatusBar);
        if (activity == null) return;
        //transparentStatusBar(activity);
        fakeStatusBar.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = fakeStatusBar.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight()
            );
            fakeStatusBar.setLayoutParams(layoutParams);
        } else {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = getStatusBarHeight();
        }
    }

    /**
     * 设置状态栏颜色关于DrawerLayout
     *
     * @param drawer
     * @param color
     */
    public static void setStatusBarColor4Drawer(@NonNull final DrawerLayout drawer, @ColorInt final int color) {
        setStatusBarColor4Drawer(((Activity) drawer.getContext()).getWindow(), drawer, color);
    }

    /**
     * 设置状态栏颜色关于DrawerLayout
     *
     * @param window
     * @param drawer
     * @param color
     */
    public static void setStatusBarColor4Drawer(@NonNull final Window window, @NonNull final DrawerLayout drawer, @ColorInt final int color) {
        setStatusBarColor4DrawerBaseSetting(window);
        drawer.setStatusBarBackgroundColor(color);
    }

    /**
     * 设置状态栏Drawable关于DrawerLayout
     *
     * @param drawer
     * @param drawable
     */
    public static void setStatusBarColor4Drawer4Drawable(@NonNull final DrawerLayout drawer, final Drawable drawable) {
        setStatusBarColor4Drawer4Drawable(((Activity) drawer.getContext()).getWindow(), drawer, drawable);
    }

    /**
     * 设置状态栏Drawable关于DrawerLayout
     *
     * @param window
     * @param drawer
     * @param drawable
     */
    public static void setStatusBarColor4Drawer4Drawable(@NonNull final Window window, @NonNull final DrawerLayout drawer, final Drawable drawable) {
        setStatusBarColor4DrawerBaseSetting(window);
        drawer.setStatusBarBackground(drawable);
    }

    /**
     * 设置状态栏资源文件关于DrawerLayout
     *
     * @param drawer
     * @param resId
     */
    public static void setStatusBarColor4Drawer4Res(@NonNull final DrawerLayout drawer, @IdRes final int resId) {
        setStatusBarColor4Drawer4Res(((Activity) drawer.getContext()).getWindow(), drawer, resId);
    }

    /**
     * 设置状态栏资源文件关于DrawerLayout
     *
     * @param window
     * @param drawer
     * @param resId
     */
    public static void setStatusBarColor4Drawer4Res(@NonNull final Window window, @NonNull final DrawerLayout drawer, @IdRes final int resId) {
        setStatusBarColor4DrawerBaseSetting(window);
        drawer.setStatusBarBackground(resId);
    }

    /**
     * 设置状态栏颜色关于DrawerLayout的基础设置
     *
     * @param window
     */
    private static void setStatusBarColor4DrawerBaseSetting(Window window) {
        SlcBarCompatUtils.setStatusBarTransparent(window);
        SlcBarCompatUtils.setLayoutFullscreen(window, true);
    }

    private static void applyStatusBarColor(final Window window,
                                            final int color,
                                            boolean isDecor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (color != Color.TRANSPARENT) {
                ViewGroup decorView = isDecor ? (ViewGroup) window.getDecorView() : (ViewGroup) window.findViewById(android.R.id.content);
                View fakeStatusBarView = decorView.findViewById(FAKE_STATUS_BAR_VIEW_ID);
                if (fakeStatusBarView != null) {
                    if (fakeStatusBarView.getVisibility() == View.GONE) {
                        fakeStatusBarView.setVisibility(View.VISIBLE);
                    }
                    fakeStatusBarView.setBackgroundColor(color);
                } else {
                    decorView.addView(createStatusBarView(window.getContext(), color));
                }
            }
            applyRootViewFitsSystemWindows(window);
        }
    }

    /**
     * 创建状态栏填充视图
     *
     * @param context
     * @param color
     * @return
     */
    private static View createStatusBarView(final Context context,
                                            final int color) {
        View statusBarView = new View(context);
        statusBarView.setId(FAKE_STATUS_BAR_VIEW_ID);
        statusBarView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight()));
        statusBarView.setBackgroundColor(color);
        statusBarView.setTag(TAG_STATUS_BAR);
        return statusBarView;
    }

    /**
     * 设置根布局参数
     */
    private static void applyRootViewFitsSystemWindows(Window window) {
        ViewGroup parent = window.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }


    /**
     * 计算根据color和alpha计算最终颜色值
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    public static int calculateStatusColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }
    ///////////////////////////////////////////////////////////////////////////
    // action bar
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Return the action bar's height.
     *
     * @return the action bar's height
     */
    public static int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        if (Utils.getApp().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(
                    tv.data, Utils.getApp().getResources().getDisplayMetrics()
            );
        }
        return 0;
    }

    ///////////////////////////////////////////////////////////////////////////
    // notification bar
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Set the notification bar's visibility.
     * <p>Must hold {@code <uses-permission android:name="android.permission.EXPAND_STATUS_BAR" />}</p>
     *
     * @param isVisible True to set notification bar visible, false otherwise.
     */
    @RequiresPermission(EXPAND_STATUS_BAR)
    public static void setNotificationBarVisibility(final boolean isVisible) {
        String methodName;
        if (isVisible) {
            methodName = (Build.VERSION.SDK_INT <= 16) ? "expand" : "expandNotificationsPanel";
        } else {
            methodName = (Build.VERSION.SDK_INT <= 16) ? "collapse" : "collapsePanels";
        }
        invokePanels(methodName);
    }

    private static void invokePanels(final String methodName) {
        try {
            @SuppressLint("WrongConstant")
            Object service = Utils.getApp().getSystemService("statusbar");
            @SuppressLint("PrivateApi")
            Class<?> statusBarManager = Class.forName("android.app.StatusBarManager");
            Method expand = statusBarManager.getMethod(methodName);
            expand.invoke(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // navigation bar
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Return the navigation bar's height.
     *
     * @return the navigation bar's height
     */
    public static int getNavBarHeight() {
        Resources res = Utils.getApp().getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return res.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * Set the navigation bar's visibility.
     *
     * @param activity  The activity.
     * @param isVisible True to set navigation bar visible, false otherwise.
     */
    public static void setNavBarVisibility(@NonNull final Activity activity, boolean isVisible) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        setNavBarVisibility(activity.getWindow(), isVisible);
    }

    /**
     * Set the navigation bar's visibility.
     *
     * @param window    The window.
     * @param isVisible True to set navigation bar visible, false otherwise.
     */
    public static void setNavBarVisibility(@NonNull final Window window, boolean isVisible) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        final ViewGroup decorView = (ViewGroup) window.getDecorView();
        for (int i = 0, count = decorView.getChildCount(); i < count; i++) {
            final View child = decorView.getChildAt(i);
            final int id = child.getId();
            if (id != View.NO_ID) {
                String resourceEntryName = Utils.getApp()
                        .getResources()
                        .getResourceEntryName(id);
                if ("navigationBarBackground".equals(resourceEntryName)) {
                    child.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
                }
            }
        }
        final int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (isVisible) {
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~uiOptions);
        } else {
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | uiOptions);
        }
    }

    /**
     * Return whether the navigation bar visible.
     * <p>Call it in onWindowFocusChanged will get right result.</p>
     *
     * @param activity The activity.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNavBarVisible(@NonNull final Activity activity) {
        return isNavBarVisible(activity.getWindow());
    }

    /**
     * Return whether the navigation bar visible.
     * <p>Call it in onWindowFocusChanged will get right result.</p>
     *
     * @param window The window.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNavBarVisible(@NonNull final Window window) {
        boolean isVisible = false;
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        for (int i = 0, count = decorView.getChildCount(); i < count; i++) {
            final View child = decorView.getChildAt(i);
            final int id = child.getId();
            if (id != View.NO_ID) {
                String resourceEntryName = Utils.getApp()
                        .getResources()
                        .getResourceEntryName(id);
                if ("navigationBarBackground".equals(resourceEntryName)
                        && child.getVisibility() == View.VISIBLE) {
                    isVisible = true;
                    break;
                }
            }
        }
        if (isVisible) {
            int visibility = decorView.getSystemUiVisibility();
            isVisible = (visibility & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;
        }
        return isVisible;
    }

    /**
     * Set the navigation bar's color.
     *
     * @param activity The activity.
     * @param color    The navigation bar's color.
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setNavBarColor(@NonNull final Activity activity, @ColorInt final int color) {
        setNavBarColor(activity.getWindow(), color);
    }

    /**
     * Set the navigation bar's color.
     *
     * @param window The window.
     * @param color  The navigation bar's color.
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setNavBarColor(@NonNull final Window window, @ColorInt final int color) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(color);
    }

    /**
     * Return the color of navigation bar.
     *
     * @param activity The activity.
     * @return the color of navigation bar
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static int getNavBarColor(@NonNull final Activity activity) {
        return getNavBarColor(activity.getWindow());
    }

    /**
     * Return the color of navigation bar.
     *
     * @param window The window.
     * @return the color of navigation bar
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static int getNavBarColor(@NonNull final Window window) {
        return window.getNavigationBarColor();
    }

    /**
     * Return whether the navigation bar visible.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isSupportNavBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            WindowManager wm = (WindowManager) Utils.getApp().getSystemService(Context.WINDOW_SERVICE);
            if (wm == null) return false;
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y || realSize.x != size.x;
        }
        boolean menu = ViewConfiguration.get(Utils.getApp()).hasPermanentMenuKey();
        boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        return !menu && !back;
    }

    /**
     * Set the nav bar's light mode.
     *
     * @param activity    The activity.
     * @param isLightMode True to set nav bar light mode, false otherwise.
     */
    public static void setNavBarLightMode(@NonNull final Activity activity,
                                          final boolean isLightMode) {
        setStatusBarLightMode(activity.getWindow(), isLightMode);
    }

    /**
     * Set the nav bar's light mode.
     *
     * @param window      The window.
     * @param isLightMode True to set nav bar light mode, false otherwise.
     */
    public static void setNavBarLightMode(@NonNull final Window window,
                                          final boolean isLightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            View decorView = window.getDecorView();
            int vis = decorView.getSystemUiVisibility();
            if (isLightMode) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            decorView.setSystemUiVisibility(vis);
        }
    }

    /**
     * Is the nav bar light mode.
     *
     * @param activity The activity.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNavBarLightMode(@NonNull final Activity activity) {
        return isStatusBarLightMode(activity.getWindow());
    }

    /**
     * Is the nav bar light mode.
     *
     * @param window The window.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNavBarLightMode(@NonNull final Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            View decorView = window.getDecorView();
            int vis = decorView.getSystemUiVisibility();
            return (vis & View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR) != 0;
        }
        return false;
    }

    private static Activity getActivityByView(@NonNull final View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        Log.e("BarUtils", "the view's Context is not an Activity.");
        return null;
    }
}