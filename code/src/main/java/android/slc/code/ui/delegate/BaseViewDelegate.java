package android.slc.code.ui.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 基础视图代理
 * 将{@link android.app.Activity#onCreate(Bundle)}或{@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}中设置视图相关的操作交由本类处理
 * 单独拎出来作为一个类是为了降低耦合性，方便将此功能移植到此框架之外的框架中
 *
 * @author slc
 * @date 2021/2/5 15:07
 */
public class BaseViewDelegate implements LifecycleObserver {
    /**
     * 基于activity的delegate
     */
    protected FragmentActivity mActivity;
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
     * 唯一构造函数
     *
     * @param supportView
     */
    public BaseViewDelegate(ISupportView supportView) {
        if (supportView instanceof FragmentActivity) {
            this.mActivity = (FragmentActivity) supportView;
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
     * 初始化方法，接管{@link android.app.Activity}的初始化方法
     * 调用此方法后会回调{@link ISupportView#initViewBefore(),ISupportView#onBindView(),ISupportView#initViewLater()}方法
     *
     * @param savedInstanceState
     */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.mSupportView.initViewBefore();
        initView(new CreateViewAuxiliaryBox(savedInstanceState));
        this.mSupportView.initViewLater();
    }

    /**
     * 初始化方法，接管{@link Fragment}的视图初始化方法
     * 调用此方法后会回调{@link ISupportView#initViewBefore(),ISupportView#onBindView(Bundle),ISupportView#initViewLater()}方法
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
     * 调用此方法后会回调{@link ISupportView#onBindView(Bundle)}方法
     *
     * @param createViewAuxiliaryBox
     */
    protected void initView(@Nullable CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        interfereLoadView(createViewAuxiliaryBox);
        this.mSupportView.onBindView(createViewAuxiliaryBox.getSavedInstanceState());
    }

    /**
     * 干扰视图加载方式
     * 此方法会为{@link android.app.Activity,Fragment}设置视图
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

    /**
     * 生命周期的onDestroy方法
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {

    }
}
