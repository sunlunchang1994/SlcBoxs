package android.slc.code.ui.delegate;

import android.slc.code.ui.views.MvvmViewShank;
import android.slc.code.vm.BaseViewCompatV0395Model;
import android.slc.code.vm.BaseViewModel;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

/**
 * 使用与MVVM设计模式的ViewDelegate方法
 *
 * @author slc
 * @date 2021/2/5 16:03
 */
public class MvvmViewCompat0395Delegate<V extends ViewDataBinding> extends MvvmViewDelegate<V> {

    public MvvmViewCompat0395Delegate(ISupportView supportView) {
        super(supportView);
    }

    /**
     * 获取{@link MvvmViewShank}
     *
     * @return
     */
    protected MvvmViewShank getMvvmViewShank() {
        if (this.mActivity instanceof MvvmViewShank) {
            return (MvvmViewShank) this.mActivity;
        }
        if (this.mFragment instanceof MvvmViewShank) {
            return (MvvmViewShank) this.mFragment;
        }
        return null;
    }

    /**
     * 注册mvvm视图句柄
     * 事实上，让{@link ViewModel}持有视图是不允许的，哪怕是{@link android.content.Context}也不允许，但为了某些时候方便写代码，此处还是这么做了
     * 如有更好且合理的做法请告诉作者
     *
     * @param viewModel
     */
    public void registerMvvmViewShank(BaseViewCompatV0395Model viewModel) {
        MvvmViewShank mvvmViewShank = getMvvmViewShank();
        if (mvvmViewShank != null) {
            viewModel.initMvvmViewShank(mvvmViewShank);
        }
    }

}
