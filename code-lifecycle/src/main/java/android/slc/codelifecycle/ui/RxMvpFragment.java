package android.slc.codelifecycle.ui;

import android.os.Bundle;
import android.slc.code.ui.fragment.MvpFragment;
import android.slc.codelifecycle.contract.RxBaseContract;
import android.slc.rxlifecycle.RxLifecycleDelegate;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import androidx.annotation.Nullable;

public abstract class RxMvpFragment<P extends RxBaseContract.RxBasePresenter> extends MvpFragment<P>
        implements RxBaseContract.RxBaseView<P> {
    private RxLifecycleDelegate rxLifecycleDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        rxLifecycleDelegate = RxLifecycleDelegateImp.create(getLifecycle());
        super.onCreate(savedInstanceState);
    }

    @Override
    public RxLifecycleDelegate getRxLifecycleDelegate() {
        return rxLifecycleDelegate;
    }
}
