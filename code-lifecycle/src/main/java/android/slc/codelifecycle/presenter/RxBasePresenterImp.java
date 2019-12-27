package android.slc.codelifecycle.presenter;

import android.slc.codelifecycle.contract.RxBaseContract;
import android.slc.code.presenter.BasePresenterImp;

import com.trello.rxlifecycle3.LifecycleTransformer;

public class RxBasePresenterImp<V extends RxBaseContract.RxBaseView>
        extends BasePresenterImp<V> implements RxBaseContract.RxBasePresenter<V> {

    public RxBasePresenterImp(V view) {
        super(view);
    }

    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    protected <T> LifecycleTransformer<T> bindToLifecycle() {
        return getView().getRxLifecycleDelegate().bindToLifecycle();
    }

}
