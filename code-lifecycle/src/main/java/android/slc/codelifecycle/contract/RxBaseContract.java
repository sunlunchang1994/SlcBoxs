package android.slc.codelifecycle.contract;

import android.slc.code.contract.MvpContract;
import android.slc.rxlifecycle.RxLifecycleDelegate;

public interface RxBaseContract {

    interface RxBasePresenter<V extends MvpContract.BaseMvpView> extends MvpContract.BasePresenter<V> {

    }

}
