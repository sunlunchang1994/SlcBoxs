package android.slc.rx;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * @author slc
 * @date 2019/10/23 15:38
 */
public class SimpleSingleObserver<T> implements SingleObserver<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

}
