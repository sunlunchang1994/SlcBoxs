package android.slc.code.ui.activity;

import android.os.Bundle;
import android.slc.toolbar.ISlcToolBarDelegate;
import android.view.View;

import androidx.annotation.Nullable;

import android.slc.code.contract.MvpContract;
import android.slc.code.ui.views.BaseActivityDelegate;

import android.slc.commonlibrary.util.compat.SlcBarCompatUtils;


/**
 * Created by on the way on 2017/12/6.
 */

public abstract class BaseActivity<P extends MvpContract.BasePresenter> extends MvpActivity<P> implements BaseActivityDelegate {
    protected boolean mBarIsLight = false;//是否为高亮Bar
    protected ISlcToolBarDelegate mSlcToolBarDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBefore();
        Object layoutObj = setContentView();
        if (layoutObj instanceof Integer) {
            setContentView((Integer) layoutObj);
        } else if (layoutObj instanceof View) {
            setContentView((View) layoutObj);
        } else {
            throw new ClassCastException("setContentView() type must be int or View");
        }
        mBarIsLight = initBarStyle();
        syncBarStyle();
        mSlcToolBarDelegate = initSlcToolBarDelegate();
        onBindView(savedInstanceState);
        initViewLater();
        initPresenter();
    }


    protected abstract Object setContentView();

    /**
     * 获取 ISlcToolBarDelegate
     *
     * @return
     */
    protected ISlcToolBarDelegate initSlcToolBarDelegate() {
        /*return new SlcToolBarNormalDelegate.Builder()
                .setThemeMode(barIsLight() ?
                SlcToolBarDelegate.ThemeMode.Light : SlcToolBarDelegate.ThemeMode.Dark)
                .setFitsSystemWindows(true)
                .create(this);*/
        return null;
    }

    /**
     * 绑定试图
     *
     * @param savedInstanceState
     */
    protected abstract void onBindView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化视图之前调用此方法
     */
    protected void initViewBefore() {
    }

    /**
     * 初始化试图之后调用此方法
     */
    protected void initViewLater() {
    }

    protected void initPresenter() {

    }

    @Override
    public final boolean barIsLight() {
        return mBarIsLight;
    }

    @Override
    public final void setBarLightModel(boolean isLight) {
        if (this.mBarIsLight != isLight) {
            this.mBarIsLight = isLight;
            syncBarStyle();
        }
    }

    /**
     * 同步syncBarStyle
     */
    protected void syncBarStyle() {
        SlcBarCompatUtils.setStatusBarLightMode(this, mBarIsLight);
    }

    /**
     * 初始化BarStyle
     */
    protected boolean initBarStyle() {
        return mBarIsLight;
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/

}
