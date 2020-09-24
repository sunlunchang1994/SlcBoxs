package android.slc.code.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.slc.code.ui.CreateViewAuxiliaryBox;
import android.slc.code.ui.views.BaseActivityDelegate;
import android.slc.toolbar.ISlcToolBarDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

/**
 * Created by on the way on 2017/12/6.
 */

public abstract class BaseFragment extends EnhanceFragment {
    protected BaseActivityDelegate mBaseActivityDelegate;
    protected ISlcToolBarDelegate mSlcToolBarDelegate;
    private View mContentView;


    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewBefore();
        Object layoutObj = setContentView();
        mContentView = interfereLoadView(new CreateViewAuxiliaryBox(layoutObj, inflater, container, savedInstanceState));
        mSlcToolBarDelegate = initSlcToolBarDelegate();
        onBindView(savedInstanceState);
        initViewLater();
        return mContentView;
    }

    /**
     * 设置布局
     *
     * @return 返回一个继承鱼View对象的视图或布局的资源文件
     */
    protected abstract Object setContentView();

    /**
     * 干扰视图加载方式
     *
     * @param createViewAuxiliaryBox
     */
    protected View interfereLoadView(CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        View contentView = null;
        Object layoutObj = createViewAuxiliaryBox.getLayoutObj();
        if (layoutObj instanceof Integer) {
            contentView = createViewAuxiliaryBox.getInflater().inflate((Integer) layoutObj, createViewAuxiliaryBox.getContainer(), false);
        } else if (layoutObj instanceof View) {
            contentView = (View) layoutObj;
        } else {
            throw new ClassCastException("setContentView() type must be int or View");
        }
        return contentView;
    }

    protected View getContentView() {
        return mContentView;
    }

    protected <T extends View> T findViewById(@IdRes int id) {
        return this.mContentView.findViewById(id);
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

    /**
     * 获取状态栏颜色
     *
     * @return
     */
    protected boolean barIsLight() {
        return false;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivityDelegate) {
            mBaseActivityDelegate = (BaseActivityDelegate) activity;
        } else {
            throw new ClassCastException("activity type must be extends BaseActivityDelegate");
        }
        Log.i(TAG, "onAttach()");
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (mBaseActivityDelegate != null) {
            mBaseActivityDelegate.setBarLightModel(barIsLight());//初始化状态栏风格
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBaseActivityDelegate = null;
    }
}
