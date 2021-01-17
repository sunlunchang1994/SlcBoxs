package android.slc.toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.BaseActionProvider;
import androidx.core.view.ActionProvider;
import androidx.core.view.MenuItemCompat;

import com.google.android.material.appbar.AppBarLayout;


/**
 * @author slc
 * @date 2019/12/9 15:01
 */
public class SlcToolBarDelegate implements ISlcToolBarDelegate {
    protected static final CharSequence KEY_TITLE = "SlcToolBarDelegateTitle";
    protected static final CharSequence KEY_SUB_TITLE = "SlcToolBarDelegateSubTitle";
    protected Context mContext;
    protected View mContentView;

    private int mStatusBarHeight = -1;
    protected float mElevation;

    protected AppBarLayout mAppBarLayout;
    protected Toolbar mToolbar;

    protected TextView mTitleTextView;
    protected TextView mSubtitleTextView;
    protected ActionMenuView mActionMenuView;

    protected MenuItem.OnMenuItemClickListener mOnMenuItemClickListener;
    protected final Toolbar.OnMenuItemClickListener toolbarOnMenuItemClickListener =
            new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (mOnMenuItemClickListener != null) {
                        return mOnMenuItemClickListener.onMenuItemClick(item);
                    }
                    return false;
                }
            };

    public SlcToolBarDelegate(View view, int appBarLayoutId, int toolbarId) {
        this.mContext = view.getContext();
        this.mContentView = view;
        init(appBarLayoutId, toolbarId);
    }

    /**
     * 初始化
     */
    protected void init(int appBarLayoutId, int toolbarId) {
        mAppBarLayout = mContentView.findViewById(appBarLayoutId);
        mToolbar = mContentView.findViewById(toolbarId);
    }

    @Override
    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }

    @Override
    public Toolbar getToolBar() {
        return mToolbar;
    }

    @Override
    public TextView getTitleTextView() {
        if (mTitleTextView == null) {
            CharSequence title = mToolbar.getTitle();
            if (TextUtils.isEmpty(title)) {
                title = KEY_TITLE;
            }
            mToolbar.setTitle(KEY_TITLE);
            mTitleTextView = getTargetViewByViewGroup(mToolbar, view -> {
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    return KEY_TITLE.equals(textView.getText());
                }
                return false;
            });
            if (title.equals(KEY_TITLE)) {
                title = "";
            }
            mToolbar.setTitle(title);
        }
        if (mTitleTextView == null) {
            throw new NullPointerException("TitleTextView should not be empty");
        }
        return mTitleTextView;
    }

    @Override
    public TextView getSubTitleTextView() {
        if (mSubtitleTextView == null) {
            CharSequence subTitle = mToolbar.getSubtitle();
            if (TextUtils.isEmpty(subTitle)) {
                subTitle = KEY_SUB_TITLE;
            }
            mToolbar.setSubtitle(KEY_SUB_TITLE);
            mSubtitleTextView = getTargetViewByViewGroup(mToolbar, view -> {
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    return KEY_SUB_TITLE.equals(textView.getText());
                }
                return false;
            });
            if (subTitle.equals(KEY_SUB_TITLE)) {
                subTitle = "";
            }
            mToolbar.setSubtitle(subTitle);
        }
        if (mSubtitleTextView == null) {
            throw new NullPointerException("SubtitleTextView should not be empty");
        }
        return mSubtitleTextView;
    }

    /*@Override
    public void inflateMenu(int resId) {
        mToolbar.inflateMenu(resId);
    }*/

    /**
     * 此方法不能保证获取的ActionMenuView为MenuView，如果想确获取ActionMenuView为MenuView，需再次之前调用
     *
     * @return
     */
    @Override
    public ActionMenuView getActionMenuView() {
        if (mActionMenuView == null) {
            mActionMenuView = getTargetViewByViewGroup(mToolbar, view -> {
                if (view instanceof ActionMenuView) {
                    return true;
                }
                return false;
            });
        }
        return mActionMenuView;
    }

    /**
     * 在调用此方法前请确认是否调用{@link Toolbar inflateMenu()}方法
     *
     * @param listener
     */
    @Override
    public void setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener listener) {
        this.mOnMenuItemClickListener = listener;
        mToolbar.setOnMenuItemClickListener(toolbarOnMenuItemClickListener);
        Menu menu = mToolbar.getMenu();
        if (mOnMenuItemClickListener != null && menu.size() != 0) {
            MenuItem menuItem = null;
            ActionProvider actionProvider = null;
            for (int i = 0; i < menu.size(); i++) {
                menuItem = menu.getItem(i);
                actionProvider = MenuItemCompat.getActionProvider(menuItem);
                if (actionProvider instanceof BaseActionProvider) {
                    BaseActionProvider baseActionProvider = (BaseActionProvider) actionProvider;
                    baseActionProvider.setSubMenuItem(menuItem.getSubMenu());
                    baseActionProvider.setOnMenuItemClickListener(mOnMenuItemClickListener);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends View> T getTargetViewByViewGroup(ViewGroup viewGroup, ViewFilter viewFilter) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = viewGroup.getChildAt(i);
            if (viewFilter.onFilter(childView)) {
                return (T) childView;
            }
        }
        return null;
    }

    @Override
    public void setToolBarElevation(float elevation) {
        this.mElevation = elevation;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && mAppBarLayout != null) {
            mAppBarLayout.setElevation(elevation);
            if (elevation == 0) {
                mAppBarLayout.setTargetElevation(0f);
            }
        }
    }

    @Override
    public int getStatusBarHeight() {
        if (mStatusBarHeight == -1) {
            Resources resource = mContext.getResources();
            int resourceId = resource.getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId != 0) {
                mStatusBarHeight = resource.getDimensionPixelSize(resourceId);
                return mStatusBarHeight;
            } else {
                try {
                    Class<?> clazz = Class.forName("com.android.internal.R$dimen");
                    Object object = clazz.newInstance();
                    int statusBarHeightId = Integer.parseInt(clazz.getField("status_bar_height")
                            .get(object).toString());
                    mStatusBarHeight = resource.getDimensionPixelSize(statusBarHeightId);
                    return mStatusBarHeight;
                } catch (Exception e) {
                    return SlcToolBarConstant.dip2px(mContext, 24);
                }
            }
        } else {
            return mStatusBarHeight;
        }
    }

    public static class Builder<T extends Builder> {
        protected View mContentView;
        protected int mAppBarLayoutId = R.id.appBarLayout;
        protected int mToolbarId = R.id.toolBar;

        public Builder(Activity activity) {
            this(activity.getWindow().getDecorView());
        }

        public Builder(View view) {
            this.mContentView = view;
        }

        /**
         * 设置appBarLayout id
         *
         * @param appBarLayoutId
         */
        @SuppressWarnings("unchecked")
        public T setAppBarLayoutId(int appBarLayoutId) {
            this.mAppBarLayoutId = appBarLayoutId;
            return (T) this;
        }

        /**
         * 设置toolbar Id
         *
         * @param toolbarId
         */
        @SuppressWarnings("unchecked")
        public T setToolbarId(int toolbarId) {
            this.mToolbarId = toolbarId;
            return (T) this;
        }

        public ISlcToolBarDelegate build() {
            return new SlcToolBarDelegate(mContentView, mAppBarLayoutId, mToolbarId);
        }
    }
}
