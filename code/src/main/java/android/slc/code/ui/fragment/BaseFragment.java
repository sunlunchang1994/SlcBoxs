package android.slc.code.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.slc.code.contract.MvpContract;
import android.slc.code.ui.views.BaseActivityDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import android.slc.toolbar.ISlcToolBarDelegate;

/**
 * Created by on the way on 2017/12/6.
 */

public abstract class BaseFragment<P extends MvpContract.BasePresenter> extends MvpFragment<P> {

    protected BaseActivityDelegate mBaseActivityDelegate;
    protected ISlcToolBarDelegate mSlcToolBarDelegate;
    private View mContentView;

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (mBaseActivityDelegate != null) {
            mBaseActivityDelegate.setBarLightModel(barIsLight());//初始化状态栏风格
        }
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        initViewBefore();
        Object layoutObj = setContentView();
        if (layoutObj instanceof Integer) {
            mContentView = inflater.inflate((Integer) layoutObj, container, false);
        } else if (layoutObj instanceof View) {
            mContentView = (View) layoutObj;
        } else {
            throw new ClassCastException("setContentView() type must be int or View");
        }
        mSlcToolBarDelegate = initSlcToolBarDelegate();
        onBindView(savedInstanceState);
        initViewLater();
        initPresenter();
        return mContentView;
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

    /**
     * 设置布局
     *
     * @return 返回一个继承鱼View对象的视图或布局的资源文件
     */
    protected abstract Object setContentView();

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

    /**
     * 获取状态栏颜色
     *
     * @return
     */
    protected boolean barIsLight() {
        return false;
    }


    /*@Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/


    /**
     * 在销毁时，销毁Presenter对象，避免由于Presenter持有上下问环境时造成的资源浪费
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mBaseActivityDelegate = null;
    }
}
