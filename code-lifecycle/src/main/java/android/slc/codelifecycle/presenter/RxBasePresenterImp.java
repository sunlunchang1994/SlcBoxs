package android.slc.codelifecycle.presenter;

import android.slc.code.contract.MvpContract;
import android.slc.codelifecycle.contract.RxBaseContract;
import android.slc.code.presenter.BasePresenterImp;
import android.slc.rxlifecycle.RxLifecycleDelegate;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import com.trello.rxlifecycle3.LifecycleTransformer;

public class RxBasePresenterImp<V extends MvpContract.BaseMvpView>
        extends BasePresenterImp<V> implements RxBaseContract.RxBasePresenter<V> {
    private RxLifecycleDelegate rxLifecycleDelegate;

    public RxBasePresenterImp(V view) {
        super(view);
        rxLifecycleDelegate = RxLifecycleDelegateImp.create(view.getLifecycleOwner().getLifecycle());
    }
    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    protected <T> LifecycleTransformer<T> bindToLifecycle() {
        return rxLifecycleDelegate.bindToLifecycle();
    }

    /**
     * 获取rxLifecycleDelegate
     *
     * @return
     */
    protected RxLifecycleDelegate getRxLifecycleDelegate() {
        return rxLifecycleDelegate;
    }

}
