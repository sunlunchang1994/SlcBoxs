package android.slc.codelifecycle.vm;

import android.app.Application;
import android.slc.code.ui.views.MvvmViewShank;
import android.slc.code.vm.BaseViewCompatV0395Model;
import android.slc.rxlifecycle.RxLifecycleDelegate;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import androidx.annotation.NonNull;

import com.trello.rxlifecycle3.LifecycleTransformer;

/**
 * @author slc
 * @date 2020/3/12 10:09
 * @email sunlunchang@gmail.com
 */
public class RxViewCompatV0395Model extends BaseViewCompatV0395Model {
    protected RxLifecycleDelegate rxLifecycleDelegate;

    public RxViewCompatV0395Model(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initMvvmViewShank(MvvmViewShank mvvmViewShank) {
        super.initMvvmViewShank(mvvmViewShank);

        rxLifecycleDelegate = RxLifecycleDelegateImp.create(mvvmViewShank.getLifecycleOwner().getLifecycle());
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
        return rxLifecycleDelegate;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        rxLifecycleDelegate = null;
    }
}
