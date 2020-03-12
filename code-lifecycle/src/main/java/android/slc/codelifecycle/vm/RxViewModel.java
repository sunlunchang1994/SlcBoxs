package android.slc.codelifecycle.vm;

import android.app.Application;
import android.slc.code.ui.views.ViewDelegate;
import android.slc.code.vm.BaseViewModel;
import android.slc.rxlifecycle.RxLifecycleDelegate;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import androidx.annotation.NonNull;

import com.trello.rxlifecycle3.LifecycleTransformer;

/**
 * @author slc
 * @date 2020/3/12 10:09
 * @email sunlunchang@gmail.com
 */
public class RxViewModel extends BaseViewModel {
    private RxLifecycleDelegate rxLifecycleDelegate;

    public RxViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initViewDelegate(ViewDelegate viewDelegate) {
        super.initViewDelegate(viewDelegate);
        rxLifecycleDelegate = RxLifecycleDelegateImp.create(viewDelegate.getLifecycleOwner().getLifecycle());
    }

    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return rxLifecycleDelegate.bindToLifecycle();
    }

    /**
     * 获取rxLifecycleDelegate
     *
     * @return
     */
    public RxLifecycleDelegate getRxLifecycleDelegate() {
        return rxLifecycleDelegate;
    }
}
