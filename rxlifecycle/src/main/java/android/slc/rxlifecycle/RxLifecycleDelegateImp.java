package android.slc.rxlifecycle;

import android.util.Log;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.RxLifecycle;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @author slc
 * @date 2019/11/28 10:38
 */
public class RxLifecycleDelegateImp implements RxLifecycleDelegate {
    private final BehaviorSubject<SlcMvpViewEvent> lifecycleSubject = BehaviorSubject.create();

    public static RxLifecycleDelegate create(Lifecycle lifecycle) {
        RxLifecycleDelegateImp rxlifecycleDelegateImp = new RxLifecycleDelegateImp();
        lifecycle.addObserver(rxlifecycleDelegateImp);
        return rxlifecycleDelegateImp;
    }

    public BehaviorSubject<SlcMvpViewEvent> getLifecycleSubject() {
        return lifecycleSubject;
    }

    @Override
    public void onCreate() {
        Log.i("RxLifecycleDelegate", "CREATE");
        lifecycleSubject.onNext(SlcMvpViewEvent.CREATE);
    }

    @Override
    public void onStart() {
        Log.i("RxLifecycleDelegate", "START");
        lifecycleSubject.onNext(SlcMvpViewEvent.START);
    }

    @Override
    public void onResume() {
        Log.i("RxLifecycleDelegate", "RESUME");
        lifecycleSubject.onNext(SlcMvpViewEvent.RESUME);
    }

    @Override
    public void onPause() {
        Log.i("RxLifecycleDelegate", "PAUSE");
        lifecycleSubject.onNext(SlcMvpViewEvent.PAUSE);
    }

    @Override
    public void onStop() {
        Log.i("RxLifecycleDelegate", "STOP");
        lifecycleSubject.onNext(SlcMvpViewEvent.STOP);
    }

    @Override
    public void onDestroy() {
        Log.i("RxLifecycleDelegate", "DESTROY");
        lifecycleSubject.onNext(SlcMvpViewEvent.DESTROY);
    }

    @Override
    @NonNull
    @CheckResult
    public Observable<SlcMvpViewEvent> lifecycle() {
        return lifecycleSubject;
    }

    @Override
    @NonNull
    @CheckResult
    public <T> LifecycleTransformer<T> bindUntilEvent(@NonNull SlcMvpViewEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, SlcMvpViewEvent.DESTROY);
    }
}
