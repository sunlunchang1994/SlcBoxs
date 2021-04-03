package android.slc.code.vm;

import android.app.Application;
import android.os.Bundle;
import android.slc.code.domain.SlcActivityResult;
import android.slc.code.domain.StartActivityComponent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * 基础上viewModel
 * 在官方的基础上增加了对视图操作的常用方法
 *
 * @author slc
 * @date 2020/2/29 16:41
 */
public class BaseViewModel extends AndroidViewModel {
    protected final SingleLiveEvent<Void> finishOf = new SingleLiveEvent<>();
    protected final SingleLiveEvent<Void> backPressedOf = new SingleLiveEvent<>();
    protected final SingleLiveEvent<StartActivityComponent> startActivityOf = new SingleLiveEvent<>();
    protected final SingleLiveEvent<SlcActivityResult> fillResultOf = new SingleLiveEvent<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Void> getFinishLiveData() {
        return finishOf;
    }

    public LiveData<Void> getBackPressedLiveData() {
        return backPressedOf;
    }

    public LiveData<StartActivityComponent> getStartActivityLiveData() {
        return startActivityOf;
    }

    public LiveData<SlcActivityResult> getFillResultLiveData() {
        return fillResultOf;
    }

    /**
     * 销毁界面
     */
    protected void finish() {
        finishOf.call();
    }

    /**
     * 返回按下
     */
    protected void backPressed() {
        backPressedOf.call();
    }

    protected void startActivity(Class<?> activityClass) {
        startActivity(activityClass, null);
    }

    protected void startActivity(Class<?> activityClass, Bundle bundle) {
        startActivityOf.postValue(new StartActivityComponent(activityClass, bundle));
    }

    protected void fillResult(Bundle bundle) {
        fillResult(SlcActivityResult.createBuilder().setBundle(bundle).build());
    }

    protected void fillResultAndFinish(Bundle bundle) {
        fillResult(SlcActivityResult.createBuilder().setBundle(bundle).setFinish(true).build());
    }

    protected void fillResult(SlcActivityResult slcActivityResult) {
        fillResultOf.postValue(slcActivityResult);
    }


}
