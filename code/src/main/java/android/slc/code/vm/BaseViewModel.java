package android.slc.code.vm;

import android.app.Application;
import android.slc.code.exception.MvvmNullPointerException;
import android.slc.code.ui.views.ViewDelegate;

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
    private ViewDelegate viewDelegate;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public void initViewDelegate(ViewDelegate viewDelegate) {
        this.viewDelegate = viewDelegate;
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

    protected ViewDelegate getViewDelegate() {
        if (viewDelegate == null) {
            throw new MvvmNullPointerException("VM on Cleared");
        }
        return viewDelegate;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        viewDelegate = null;
    }
}
