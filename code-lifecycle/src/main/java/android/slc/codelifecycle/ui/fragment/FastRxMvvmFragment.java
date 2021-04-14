package android.slc.codelifecycle.ui.fragment;

import android.slc.code.ui.fragment.FastMvvmFragment;
import android.slc.code.ui.fragment.MvvmFragment;
import android.slc.code.vm.BaseViewModel;
import android.slc.codelifecycle.vm.RxViewModel;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import androidx.databinding.ViewDataBinding;

public abstract class FastRxMvvmFragment<V extends ViewDataBinding, VM extends RxViewModel> extends FastMvvmFragment<V, VM> {

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
