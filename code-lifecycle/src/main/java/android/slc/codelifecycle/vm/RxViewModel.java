package android.slc.codelifecycle.vm;

import android.app.Application;
import android.slc.code.domain.VmBox;
import android.slc.code.vm.BaseViewModel;
import android.slc.codelifecycle.domain.RxVmBox;
import android.slc.codelifecycle.utils.RxLifecycleUtils;
import android.slc.rxlifecycle.RxLifecycleDelegate;

import androidx.annotation.NonNull;

import com.trello.rxlifecycle3.LifecycleTransformer;

/**
 * @author slc
 * @date 2020/3/12 10:09
 * @email sunlunchang@gmail.com
 */
public class RxViewModel extends BaseViewModel {
    protected RxLifecycleDelegate rxLifecycleDelegate;
    private boolean isCleared;

    public RxViewModel(@NonNull Application application) {
        super(application);
    }

    public void initRxLifecycle(RxLifecycleDelegate rxLifecycleDelegate) {
        this.rxLifecycleDelegate = rxLifecycleDelegate;
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
        if (rxLifecycleDelegate == null && !isCleared) {
            rxLifecycleDelegate = RxLifecycleUtils.createByTopActivity();
        }
        return rxLifecycleDelegate;
    }

    @Override
    protected void registerVmBox(VmBox vmBox) {
        if(vmBox instanceof RxVmBox){
            RxVmBox rxVmBox = (RxVmBox) vmBox;
            rxVmBox.setRxLifecycleDelegate(getRxLifecycleDelegate());
        }
        super.registerVmBox(vmBox);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        isCleared = true;
        rxLifecycleDelegate = null;
    }
}
