package android.slc.code.ui.activity;

import androidx.lifecycle.LifecycleOwner;

import android.content.Intent;

import android.slc.code.contract.MvpContract;
import android.slc.code.exception.MvpNullPointerException;
import android.slc.code.exception.MvpUninitializedException;

/**
 * Created by on the way on 2018/11/5.
 */

public class MvpActivity<P extends MvpContract.BasePresenter> extends EnhanceActivity implements MvpContract.BaseMvpView<P> {
    /**
     * mvp模式的主持者
     */
    private P mPresenter;
    private boolean isInitPresenter;//是否初始化Presenter

    @Override
    public MvpActivity getMvpContext() {
        return this;
    }

    /**
     * 设置presenter
     */
    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
        isInitPresenter = true;
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    /**
     * 获取presenter
     *
     * @return
     */
    protected P getPresenter() {
        if (this.mPresenter != null) {
            return this.mPresenter;
        } else if (isInitPresenter) {
            throw new MvpNullPointerException("mPresenter destroyed");
        } else {
            throw new MvpUninitializedException("mPresenter uninitialized");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            getPresenter().onActivityResult(requestCode, resultCode, data);
        } catch (MvpUninitializedException e) {
            //do nothing
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
