package android.slc.rx;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * @author slc
 * @date 2019/10/23 15:38
 */
public class SimpleDisposableMaybeObserver<T> extends DisposableMaybeObserver<T> {

    @Override
    public void onSuccess(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
