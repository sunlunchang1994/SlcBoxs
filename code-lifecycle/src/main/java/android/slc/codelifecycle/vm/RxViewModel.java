package android.slc.codelifecycle.vm;

import android.app.Application;
import android.slc.code.vm.BaseViewModel;
import android.slc.rxlifecycle.RxLifecycleDelegate;

import androidx.annotation.NonNull;

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

    /**
     * 获取rxLifecycleDelegate
     *
     * @return
     */
    public RxLifecycleDelegate getRxLifecycleDelegate() {
        return rxLifecycleDelegate;
    }
}
