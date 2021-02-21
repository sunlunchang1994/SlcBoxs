package android.slc.code.ui.fragment;

import android.os.Bundle;
import android.slc.code.ui.delegate.MvvmViewDelegate;
import android.slc.code.ui.views.MvvmViewShank;
import android.slc.code.vm.BaseViewModel;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

/**
 * @author slc
 * @date 2020/3/2 11:04
 */
public abstract class MvvmFragment<V extends ViewDataBinding> extends BaseFragment implements MvvmViewShank {

    /**
     * 初始化ViewDelegate
     *
     * @param savedInstanceState
     */
    protected void initViewDelegate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                    @Nullable Bundle savedInstanceState) {
        mViewDelegate = new MvvmViewDelegate<V>(this);
        mViewDelegate.onCreate(inflater, container, savedInstanceState);
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

    /**
     * 根据AppProvider创建ViewModel
     *
     * @param modelClass
     * @param <VM>
     * @return
     */
    protected <VM extends ViewModel> VM getVmByAppProvider(@NonNull Class<VM> modelClass) {
        return getMvvmViewDelegate().getVmByAppProvider(modelClass);
    }

    /**
     * 根据ActivityViewModelProvider创建ViewModel
     *
     * @param modelClass
     * @param <VM>
     * @return
     */
    protected <VM extends ViewModel> VM getVmByActivityProvider(@NonNull Class<VM> modelClass) {
        return getMvvmViewDelegate().getVmByActivityProvider(modelClass);
    }

    /**
     * 根据FragmentViewModelProvider创建ViewModel
     *
     * @param modelClass
     * @param <VM>
     * @return
     */
    protected <VM extends ViewModel> VM getVmByFragmentProvider(@NonNull Class<VM> modelClass) {
        return getMvvmViewDelegate().getVmByFragmentProvider(modelClass);
    }

    /**
     * 注册事件
     *
     * @param viewModel
     */
    protected void registerLiveEvent(BaseViewModel viewModel) {
        getMvvmViewDelegate().registerLiveEvent(viewModel);
    }

    /**
     * 注册mvvm视图句柄
     *
     * @param viewModel
     */
    protected void registerMvvmViewShank(BaseViewModel viewModel) {
        getMvvmViewDelegate().registerMvvmViewShank(viewModel);
    }

    /**
     * 获取DataBinding
     *
     * @return
     */
    protected V getDataBinding() {
        return getMvvmViewDelegate().getDataBinding();
    }
}
