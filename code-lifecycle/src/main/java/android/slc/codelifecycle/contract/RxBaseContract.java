package android.slc.codelifecycle.contract;

import android.slc.code.contract.MvpContract;
import android.slc.rxlifecycle.RxLifecycleDelegate;

public interface RxBaseContract {
    interface RxBaseView<P extends RxBasePresenter> extends MvpContract.BaseMvpView<P> {
        RxLifecycleDelegate getRxLifecycleDelegate();
    }

    interface RxBasePresenter<V extends RxBaseView> extends MvpContract.BasePresenter<V> {

    }

}
