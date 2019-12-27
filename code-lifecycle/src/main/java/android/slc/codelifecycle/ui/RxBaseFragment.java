package android.slc.codelifecycle.ui;

import android.os.Bundle;
import android.slc.codelifecycle.contract.RxBaseContract;
import android.slc.code.ui.fragment.BaseFragment;
import android.slc.rxlifecycle.RxLifecycleDelegate;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import androidx.annotation.Nullable;

public abstract class RxBaseFragment<P extends RxBaseContract.RxBasePresenter> extends BaseFragment<P>
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
