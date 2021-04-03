package android.slc.code.ui.fragment;

import android.os.Bundle;
import android.slc.code.ui.delegate.MvvmViewCompat0395Delegate;
import android.slc.code.ui.views.MvvmViewShank;
import android.slc.code.vm.BaseViewCompatV0395Model;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

/**
 * @author slc
 * @date 2020/3/2 11:04
 */
public abstract class MvvmCompatV0395Fragment<V extends ViewDataBinding> extends MvvmFragment<V> implements MvvmViewShank {

    /**
     * 初始化ViewDelegate
     *
     * @param savedInstanceState
     */
    protected void initViewDelegate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                    @Nullable Bundle savedInstanceState) {
        mViewDelegate = new MvvmViewCompat0395Delegate<>(this);
        mViewDelegate.onCreate(inflater, container, savedInstanceState);
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

    /**
     * 注册mvvm视图句柄
     * 便于子类调用
     *
     * @param viewModel
     */
    protected void registerMvvmViewShank(BaseViewCompatV0395Model viewModel) {
        ((MvvmViewCompat0395Delegate) getMvvmViewDelegate()).registerMvvmViewShank(viewModel);
    }

}
