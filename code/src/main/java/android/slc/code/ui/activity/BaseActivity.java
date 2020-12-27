package android.slc.code.ui.activity;

import android.os.Bundle;
import android.slc.code.ui.CreateViewAuxiliaryBox;
import android.slc.code.ui.views.BaseActivityDelegate;
import android.slc.commonlibrary.util.compat.SlcBarCompatUtils;
import android.slc.toolbar.ISlcToolBarDelegate;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * Created by on the way on 2017/12/6.
 */

public abstract class BaseActivity extends EnhanceActivity implements BaseActivityDelegate {
    protected boolean mBarIsLight = false;//是否为高亮Bar
    protected ISlcToolBarDelegate mSlcToolBarDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBefore();
        initView(savedInstanceState);
        initViewLater();
    }

    public void initView(@Nullable Bundle savedInstanceState) {
        Object layoutObj = setContentView();
        interfereLoadView(new CreateViewAuxiliaryBox(layoutObj, savedInstanceState));
        mBarIsLight = initBarStyle();
        syncBarStyle();
        mSlcToolBarDelegate = initSlcToolBarDelegate();
        onBindView(savedInstanceState);
    }

    /**
     * 设置主内容视图
     *
     * @return
     */
    protected abstract Object setContentView();

    /**
     * 干扰视图加载方式
     *
     * @param createViewAuxiliaryBox
     */
    protected void interfereLoadView(CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        Object layoutObj = createViewAuxiliaryBox.getLayoutObj();
        if (layoutObj instanceof Integer) {
            setContentView((Integer) layoutObj);
        } else if (layoutObj instanceof View) {
            setContentView((View) layoutObj);
        } else {
            throw new ClassCastException("setContentView() type must be int or View");
        }
    }

    /**
     * 获取 ISlcToolBarDelegate
     *
     * @return
     */
    protected ISlcToolBarDelegate initSlcToolBarDelegate() {
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

}
