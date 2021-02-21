package android.slc.code.vm;

import android.app.Application;
import android.slc.code.exception.MvvmNullPointerException;
import android.slc.code.ui.views.MvvmViewShank;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

/**
 * @author slc
 * @date 2020/2/29 16:41
 */
public class BaseViewModel extends AndroidViewModel {
    public final ObservableField<Void> finishOf = new ObservableField<>();
    public final ObservableField<Void> backPressedOf = new ObservableField<>();
    private MvvmViewShank mvvmViewShank;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public void initMvvmViewShank(MvvmViewShank mvvmViewShank) {
        this.mvvmViewShank = mvvmViewShank;
    }

    /**
     * 销毁界面
     */
    protected void finish() {
        finishOf.notifyChange();
    }

    /**
     * 返回按下
     */
    protected void backPressed() {
        backPressedOf.notifyChange();
    }

    /**
     * 获取视图代理器
     *
     * @return
     */
    protected MvvmViewShank getMvvmViewShank() {
        if (mvvmViewShank == null) {
            throw new MvvmNullPointerException("VM on Cleared");
        }
        return mvvmViewShank;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mvvmViewShank = null;
    }
}
