package android.slc.codelifecycle.ui;

import android.os.Bundle;
import android.slc.code.ui.activity.MvpActivity;
import android.slc.codelifecycle.contract.RxBaseContract;
import android.slc.rxlifecycle.RxLifecycleDelegate;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import androidx.annotation.Nullable;

public abstract class RxMvpActivity<P extends RxBaseContract.RxBasePresenter> extends MvpActivity<P> implements
        RxBaseContract.RxBaseView<P> {
    private RxLifecycleDelegate rxLifecycleDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        rxLifecycleDelegate = RxLifecycleDelegateImp.create(getLifecycle());
        super.onCreate(savedInstanceState);
    }

    @Override
    public RxLifecycleDelegate getRxLifecycleDelegate() {
        return rxLifecycleDelegate;
    }
}
