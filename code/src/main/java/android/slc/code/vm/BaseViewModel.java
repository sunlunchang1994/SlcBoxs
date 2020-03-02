package android.slc.code.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * @author slc
 * @date 2020/2/29 16:41
 */
public class BaseViewModel extends AndroidViewModel {
    private SingleLiveEvent<Void> finishLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<Void> backPressedLiveData = new SingleLiveEvent<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 获取返回按压liveData
     * @return
     */
    public MutableLiveData<Void> getBackPressedLiveData() {
        return backPressedLiveData;
    }

    /**
     * 获取销毁livaData
     * @return
     */
    public MutableLiveData<Void> getFinishLiveData() {
        return finishLiveData;
    }

    /**
     * 销毁界面
     */
    protected void finish() {
        finishLiveData.call();
    }

    /**
     * 返回按下
     */
    protected void backPressed() {
        backPressedLiveData.call();
    }
}
