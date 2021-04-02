package android.slc.rxlifecycle;

import com.trello.rxlifecycle3.LifecycleProvider;

import io.reactivex.subjects.BehaviorSubject;

/**
 * @author slc
 * @date 2019/10/25 10:27
 */
public interface SlcLifecycleProvider extends LifecycleProvider<SlcLifecycleEvent> {
    /**
     * 获取生命周期调度器
     * @return
     */
    BehaviorSubject<SlcLifecycleEvent> getLifecycleSubject();
}
