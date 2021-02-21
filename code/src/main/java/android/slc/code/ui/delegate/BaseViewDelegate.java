package android.slc.code.ui.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author slc
 * @date 2021/2/5 15:07
 */
public class BaseViewDelegate implements LifecycleObserver {
    /**
     * 基于activity的delegate
     */
    protected AppCompatActivity mActivity;
    /**
     * 基于fragment的delegate
     */
    protected Fragment mFragment;
    /**
     * fragment的根视图
     */
    protected View mContentView;
    /**
     * 视图信息桥接接口
     */
    protected ISupportView mSupportView;

    /**
     * @param supportView
     */
    public BaseViewDelegate(ISupportView supportView) {
        if (supportView instanceof AppCompatActivity) {
            this.mActivity = (AppCompatActivity) supportView;
            this.mSupportView = supportView;
            this.mActivity.getLifecycle().addObserver(this);
            return;
        }
        if (supportView instanceof Fragment) {
            this.mFragment = (Fragment) supportView;
            this.mSupportView = supportView;
            this.mFragment.getLifecycle().addObserver(this);
            return;
        }
        throw new IllegalStateException("supportView必须是 AppCompatActivity 或 Fragment");
    }

    /**
     * 创建
     *
     * @param savedInstanceState
     */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.mSupportView.initViewBefore();
        initView(new CreateViewAuxiliaryBox(savedInstanceState));
        this.mSupportView.initViewLater();
    }

    /**
     * 创建
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    public void onCreate(@Nullable LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        this.mSupportView.initViewBefore();
        initView(new CreateViewAuxiliaryBox(inflater, container, savedInstanceState));
        this.mSupportView.initViewLater();
    }

    /**
     * 初始化视图
     *
     * @param createViewAuxiliaryBox
     */
    protected void initView(@Nullable CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        interfereLoadView(createViewAuxiliaryBox);
        this.mSupportView.onBindView(createViewAuxiliaryBox.getSavedInstanceState());
    }

    /**
     * 干扰视图加载方式
     *
     * @param createViewAuxiliaryBox
     */
    protected void interfereLoadView(CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        if (this.mActivity != null) {
            Object layoutObj = this.mSupportView.setContentView();
            if (layoutObj instanceof Integer) {
                this.mActivity.setContentView((Integer) layoutObj);
            } else if (layoutObj instanceof View) {
                this.mActivity.setContentView((View) layoutObj);
            } else {
                throw new ClassCastException("setContentView() type must be int or View");
            }
            return;
        }
        if (this.mFragment != null) {
            Object layoutObj = this.mSupportView.setContentView();
            if (layoutObj instanceof Integer) {
                this.mContentView = createViewAuxiliaryBox.getInflater().inflate((Integer) layoutObj, createViewAuxiliaryBox.getContainer(), false);
            } else if (layoutObj instanceof View) {
                this.mContentView = (View) layoutObj;
            } else {
                throw new ClassCastException("setContentView() type must be int or View");
            }
        }
    }

    /**
     * 获取contentView
     * 一般是给contentView使用
     *
     * @return
     */
    public View getContentView() {
        return this.mContentView;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {

    }
}
