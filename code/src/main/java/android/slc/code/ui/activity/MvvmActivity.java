package android.slc.code.ui.activity;

import android.os.Bundle;
import android.slc.code.ui.delegate.MvvmViewDelegate;
import android.slc.code.vm.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

/**
 * mvvm基础activity
 *
 * @author slc
 * @date 2020/3/2 9:48
 */
public abstract class MvvmActivity<V extends ViewDataBinding> extends BaseActivity {

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

    /**
     * 根据AppProvider创建ViewModel
     * 便于子类调用
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
     * 便于子类调用
     *
     * @param modelClass
     * @param <VM>
     * @return
     */
    protected <VM extends ViewModel> VM getVmByActivityProvider(@NonNull Class<VM> modelClass) {
        return getMvvmViewDelegate().getVmByActivityProvider(modelClass);
    }

    /**
     * 注册事件
     * 便于子类调用
     *
     * @param viewModel
     */
    protected void registerLiveEvent(BaseViewModel viewModel) {
        getMvvmViewDelegate().registerLiveEvent(viewModel);
    }

    /**
     * 获取DataBinding
     * 便于子类调用
     *
     * @return
     */
    protected V getDataBinding() {
        return getMvvmViewDelegate().getDataBinding();
    }
}
