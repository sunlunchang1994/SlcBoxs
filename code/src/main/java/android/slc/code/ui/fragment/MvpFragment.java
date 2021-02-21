package android.slc.code.ui.fragment;

import android.os.Bundle;
import android.slc.code.contract.MvpContract;
import android.slc.code.exception.MvpNullPointerException;
import android.slc.code.exception.MvpUninitializedException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

/**
 * Created by on the way on 2018/11/5.
 */

public abstract class MvpFragment<P extends MvpContract.BasePresenter> extends BaseFragment implements MvpContract.BaseMvpView<P> {
    /**
     * mvp模式的主持者
     */
    private P mPresenter;
    private boolean isInitPresenter;//是否初始化Presenter

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState) {
        super.onBindView(savedInstanceState);
        initPresenter();
    }

    protected void initPresenter() {
    }

    @Override
    public AppCompatActivity getMvpContext() {
        return _mActivity;
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

    @Override
    public ActivityResultCaller getActivityResultCaller() {
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
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
