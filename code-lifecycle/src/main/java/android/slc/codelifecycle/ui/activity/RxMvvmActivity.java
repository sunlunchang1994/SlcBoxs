package android.slc.codelifecycle.ui.activity;

import android.slc.code.ui.activity.MvvmActivity;
import android.slc.code.vm.BaseViewModel;
import android.slc.codelifecycle.vm.RxViewModel;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import androidx.databinding.ViewDataBinding;

public abstract class RxMvvmActivity<V extends ViewDataBinding> extends MvvmActivity<V> {

    @Override
    protected void registerLiveEvent(BaseViewModel viewModel) {
        if (viewModel instanceof RxViewModel) {
            initRxLifecycle((RxViewModel) viewModel);
        }
        super.registerLiveEvent(viewModel);
    }

    protected void initRxLifecycle(RxViewModel rxViewModel) {
        rxViewModel.initRxLifecycle(RxLifecycleDelegateImp.create(getLifecycle()));
    }
}
