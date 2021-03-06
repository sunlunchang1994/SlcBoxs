package android.slc.rx;

import androidx.annotation.NonNull;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * @author slc
 * @date 2019/10/23 15:38
 */
public class SimpleSingleObserver<T> implements SingleObserver<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onSuccess(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

}
