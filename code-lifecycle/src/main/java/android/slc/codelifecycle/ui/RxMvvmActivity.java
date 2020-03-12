package android.slc.codelifecycle.ui;

import android.os.Bundle;
import android.slc.code.ui.activity.MvpActivity;
import android.slc.code.ui.activity.MvvmActivity;
import android.slc.code.vm.BaseViewModel;
import android.slc.codelifecycle.contract.RxBaseContract;
import android.slc.rxlifecycle.RxLifecycleDelegate;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

public abstract class RxMvvmActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends MvvmActivity<V,VM> {
    private RxLifecycleDelegate rxLifecycleDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        rxLifecycleDelegate = RxLifecycleDelegateImp.create(getLifecycle());
        super.onCreate(savedInstanceState);
    }

    //@Override
    public RxLifecycleDelegate getRxLifecycleDelegate() {
        return rxLifecycleDelegate;
    }
}
