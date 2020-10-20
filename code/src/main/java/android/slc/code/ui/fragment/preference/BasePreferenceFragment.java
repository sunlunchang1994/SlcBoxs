package android.slc.code.ui.fragment.preference;

import android.os.Bundle;
import android.slc.code.contract.MvpContract;
import android.slc.code.exception.MvpNullPointerException;
import android.slc.code.exception.MvpUninitializedException;
import android.slc.code.ui.activity.MvpActivity;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

public abstract class BasePreferenceFragment<P extends MvpContract.BasePresenter> extends PreferenceFragment
        implements MvpContract.BaseMvpView<P> {
    private boolean isInitPresenter;
    private P mPresenter;

    protected abstract int getPreferenceFromResource();

    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        fromResourceBefore();
        addPreferencesFromResource(getPreferenceFromResource());
        fromResourceLater();
    }

    public void fromResourceBefore() {
    }

    public void fromResourceLater() {
    }

    public MvpActivity getMvpContext() {
        return (MvpActivity) getActivity();
    }

    public void setPresenter(P presenter) {

        this.isInitPresenter = true;
        this.mPresenter = presenter;
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    @Override
    public ActivityResultCaller getActivityResultCaller() {
        return this;
    }

    protected P getPresenter() {
        if (this.mPresenter != null) {
            return this.mPresenter;
        }
        if (this.isInitPresenter) {
            throw new MvpNullPointerException("mPresenter destroyed");
        }
        throw new MvpUninitializedException("mPresenter uninitialized");
    }

    public void onDestroyView() {
        if (this.mPresenter != null) {
            this.mPresenter.destroy();
            this.mPresenter = null;
        }
        super.onDestroyView();
    }
}
