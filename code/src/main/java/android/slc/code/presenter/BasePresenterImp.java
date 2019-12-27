package android.slc.code.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.slc.code.ui.activity.MvpActivity;
import android.util.Log;

import android.slc.code.contract.MvpContract;
import android.slc.code.exception.MvpNullPointerException;
import android.slc.code.exception.MvpUninitializedException;

/**
 * Created by on the way on 2017/12/19.
 */

public class BasePresenterImp<V extends MvpContract.BaseMvpView> implements MvpContract.BasePresenter<V> {
    public final String TAG = getClass().getSimpleName();
    private MvpActivity mContext;
    private V mView;
    private boolean isInitView;//是否初始化Model

    public BasePresenterImp(V view) {
        setView(view);
    }

    /**
     * 获取mvp模式的上下文环境
     *
     * @return
     */
    protected MvpActivity getMvpContext() {
        if (mContext != null) {
            return mContext;
        } else {
            throw new MvpNullPointerException("mContext destroyed");
        }
    }

    @Override
    public void init() {
        //TODO 开始方法
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        //
    }

    /**
     * 设置视图
     *
     * @param view
     */
    @Override
    public void setView(V view) {
        long a = System.currentTimeMillis();
        this.mView = view;
        mContext = mView.getMvpContext();
        mView.setPresenter(this);
        isInitView = true;
        Log.i("BasePresenter", System.currentTimeMillis() - a + "*");
    }

    /**
     * 获取view
     *
     * @return
     */
    protected V getView() {
        if (mView != null) {
            return mView;
        } else if (isInitView) {
            throw new MvpNullPointerException("mView destroyed");
        } else {
            throw new MvpUninitializedException("mView uninitialized");
        }
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
