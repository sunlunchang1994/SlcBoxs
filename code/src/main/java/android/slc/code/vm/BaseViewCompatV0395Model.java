package android.slc.code.vm;

import android.app.Application;
import android.slc.code.exception.MvvmNullPointerException;
import android.slc.code.ui.views.MvvmViewShank;

import androidx.annotation.NonNull;

/**
 * 基础上viewModel
 * 在官方的基础上增加了对视图操作的常用方法
 *
 * @author slc
 * @date 2020/2/29 16:41
 */
public class BaseViewCompatV0395Model extends BaseViewModel {
    private MvvmViewShank mvvmViewShank;

    public BaseViewCompatV0395Model(@NonNull Application application) {
        super(application);
    }

    public void initMvvmViewShank(MvvmViewShank mvvmViewShank) {
        this.mvvmViewShank = mvvmViewShank;
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
