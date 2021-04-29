package android.slc.rx;

import androidx.annotation.NonNull;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author slc
 * @date 2019/10/18 11:07
 */
public class SlcRxJavaUtils {
    /**
     * 使用转换的Android调度器
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> applyOoAndroidSchedulers() {
        return new ObservableTransformer<T, T>() {
            @NonNull
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 使用转换的Android调度器
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> applyOfAndroidSchedulers() {
        return new FlowableTransformer<T, T>() {
            @NonNull
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 使用转换的Android调度器
     *
     * @param <T>
     * @return
     */
    public static <T> SingleTransformer<T, T> applyOsAndroidSchedulers() {
        return new SingleTransformer<T, T>() {
            @io.reactivex.annotations.NonNull
            @Override
            public SingleSource<T> apply(@NonNull Single<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 使用转换的Android调度器
     *
     * @param <T>
     * @return
     */
    public static <T> MaybeTransformer<T, T> applyOmAndroidSchedulers() {
        return new MaybeTransformer<T, T>() {
            @NonNull
            @Override
            public MaybeSource<T> apply(@NonNull Maybe<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 使用转换的Android调度器
     *
     * @return
     */
    public static CompletableTransformer applyOcAndroidSchedulers() {
        return new CompletableTransformer() {
            @NonNull
            @Override
            public CompletableSource apply(@NonNull Completable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
