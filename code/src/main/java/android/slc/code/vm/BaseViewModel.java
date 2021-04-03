package android.slc.code.vm;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.slc.code.domain.StartActivityComponent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础上viewModel
 * 在官方的基础上增加了对视图操作的常用方法
 *
 * @author slc
 * @date 2020/2/29 16:41
 */
public class BaseViewModel extends AndroidViewModel {
    public final SingleLiveEvent<Void> finishOf = new SingleLiveEvent<>();
    public final SingleLiveEvent<Void> backPressedOf = new SingleLiveEvent<>();
    public final SingleLiveEvent<StartActivityComponent> startActivityOf = new SingleLiveEvent<>();
    public final SingleLiveEvent<Bundle> fillResultOf = new SingleLiveEvent<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
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
        fillResultOf.setValue(bundle);
    }
}
