package android.slc.code.ui.activity;

import android.os.Bundle;
import android.slc.code.ui.delegate.MvvmViewCompat0395Delegate;
import android.slc.code.ui.delegate.MvvmViewDelegate;
import android.slc.code.ui.views.MvvmViewShank;
import android.slc.code.vm.BaseViewCompatV0395Model;
import android.slc.code.vm.BaseViewModel;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

/**
 * mvvm基础activity
 *
 * @author slc
 * @date 2020/3/2 9:48
 */
public abstract class MvvmCompatV0395Activity<V extends ViewDataBinding> extends MvvmActivity<V> implements MvvmViewShank {

    @Override
    protected void initViewDelegate(@Nullable Bundle savedInstanceState) {
        mViewDelegate = new MvvmViewCompat0395Delegate<>(this);
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
        return this;
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
