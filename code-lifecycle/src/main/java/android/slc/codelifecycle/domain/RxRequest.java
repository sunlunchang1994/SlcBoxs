package android.slc.codelifecycle.domain;

import android.slc.code.domain.Request;
import android.slc.code.domain.VmBox;
import android.slc.codelifecycle.utils.RxLifecycleUtils;
import android.slc.rxlifecycle.RxLifecycleDelegate;

import com.trello.rxlifecycle3.LifecycleTransformer;

import java.lang.ref.WeakReference;

/**
 * @author slc
 * @date 2021/4/2 12:47
 */
public class RxRequest extends Request {
    private WeakReference<RxLifecycleDelegate> rxLifecycleDelegateWeakReference;

    /**
     * 设置 RxLifecycleDelegate
     *
     * @param rxLifecycleDelegate
     */
    public void setRxLifecycleDelegate(RxLifecycleDelegate rxLifecycleDelegate) {
        this.rxLifecycleDelegateWeakReference = new WeakReference<>(rxLifecycleDelegate);
    }

    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return getRxLifecycleDelegate().bindToLifecycle();
    }

    /**
     * 获取rxLifecycleDelegate
     *
     * @return
     */
    public RxLifecycleDelegate getRxLifecycleDelegate() {
        if (rxLifecycleDelegateWeakReference == null) {
            rxLifecycleDelegateWeakReference = new WeakReference<>(RxLifecycleUtils.createByTopActivity());
        }
        return rxLifecycleDelegateWeakReference.get();
    }
}
