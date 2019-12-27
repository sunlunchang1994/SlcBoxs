package android.slc.code.ui.fragment;

import androidx.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Bundle;
import android.slc.code.contract.MvpContract;
import android.slc.code.exception.MvpNullPointerException;
import android.slc.code.exception.MvpUninitializedException;
import android.slc.code.ui.activity.MvpActivity;

/**
 * Created by on the way on 2018/11/5.
 */

public class MvpFragment<P extends MvpContract.BasePresenter> extends EnhanceFragment implements MvpContract.BaseMvpView<P> {
    /**
     * mvp模式的主持者
     */
    private P mPresenter;
    private boolean isInitPresenter;//是否初始化Presenter

    @Override
    public MvpActivity getMvpContext() {
        return (MvpActivity) getActivity();
    }

    /**
     * 设置presenter
     *
     * @param presenter
     */
    public void setPresenter(P presenter) {
        isInitPresenter = true;
        this.mPresenter = presenter;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            getPresenter().onActivityResult(requestCode, resultCode, data);
        } catch (MvpUninitializedException e) {
            //TODO
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        try {
            getPresenter().onFragmentResult(requestCode, resultCode, data);
        } catch (MvpUninitializedException e) {
            //TODO
        }
    }
    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
