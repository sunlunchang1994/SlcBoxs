package android.slc.code.ui.fragment;

import android.os.Bundle;
import android.slc.code.ui.delegate.ISupportMvmView;
import android.slc.code.ui.delegate.MvvmViewDelegate;
import android.slc.code.ui.views.MvvmViewShank;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

/**
 * @author slc
 * @date 2020/3/2 11:04
 */
public abstract class MvvmFragment<V extends ViewDataBinding> extends BaseFragment implements MvvmViewShank, ISupportMvmView {

    @Override
    protected void initViewDelegate(@Nullable Bundle savedInstanceState) {
        mViewDelegate = new MvvmViewDelegate<V>(this);
        mViewDelegate.onCreate(savedInstanceState);
    }

    /**
     * 获取mvvm代理
     *
     * @return
     */
    protected MvvmViewDelegate<V> getMvvmViewDelegate() {
        return (MvvmViewDelegate) mViewDelegate;
    }

    @Override
    public AppCompatActivity getActivityContext() {
        return _mActivity;
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    @Override
    public ActivityResultCaller getActivityResultCaller() {
        return this;
    }
}
