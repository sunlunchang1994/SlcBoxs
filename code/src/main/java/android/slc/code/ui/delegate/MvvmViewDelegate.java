package android.slc.code.ui.delegate;

import android.slc.code.ui.views.MvvmViewShank;
import android.slc.code.vm.BaseViewModel;
import android.slc.commonlibrary.util.ViewModelProviderFactory;
import android.view.View;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author slc
 * @date 2021/2/5 16:03
 */
public class MvvmViewDelegate<V extends ViewDataBinding> extends BaseViewDelegate {
    protected V mDataBinding;
    protected ViewModelProvider mActivityViewModelProvider, mFragmentViewModelProvider;

    public MvvmViewDelegate(ISupportMvmView supportView) {
        super(supportView);
    }

    @Override
    protected void initView(@Nullable CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        super.initView(createViewAuxiliaryBox);
        if (mDataBinding != null) {
            mDataBinding.setLifecycleOwner(mActivity);
        }
        getSupportMvmView().initViewModel();
        //getSupportMvmView().registerLiveEvent();
        //getSupportMvmView().registerViewDelegate();
        //getSupportMvmView().bindingVariable();
    }

    @Override
    protected void interfereLoadView(CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        if (this.mActivity != null) {
            Object layoutObj = this.mSupportView.setContentView();
            if (layoutObj instanceof Integer) {
                int contentViewLayout = (int) layoutObj;
                mDataBinding = DataBindingUtil.setContentView(this.mActivity, contentViewLayout);
                if (mDataBinding == null) {
                    this.mActivity.setContentView(contentViewLayout);
                }
            } else if (layoutObj instanceof View) {
                View contentView = (View) layoutObj;
                this.mActivity.setContentView((contentView));
                mDataBinding = DataBindingUtil.bind(contentView);
            } else {
                throw new ClassCastException("setContentView() type must be int or View");
            }
            return;
        }
        if (this.mFragment != null) {
            Object layoutObj = this.mSupportView.setContentView();
            if (layoutObj instanceof Integer) {
                int contentViewLayout = (int) layoutObj;
                mDataBinding = DataBindingUtil.inflate(createViewAuxiliaryBox.getInflater(), contentViewLayout, createViewAuxiliaryBox.getContainer(), false);
                if (mDataBinding == null) {
                    this.mContentView = createViewAuxiliaryBox.getInflater().inflate((Integer) layoutObj, createViewAuxiliaryBox.getContainer(), false);
                } else {
                    this.mContentView = mDataBinding.getRoot();
                }
            } else if (layoutObj instanceof View) {
                this.mContentView = (View) layoutObj;
                mDataBinding = DataBindingUtil.bind(this.mContentView);
            } else {
                throw new ClassCastException("setContentView() type must be int or View");
            }
        }
    }

    /**
     * 获取ISupportMvmView
     * 提供给子类使用
     *
     * @return
     */
    protected ISupportMvmView getSupportMvmView() {
        return (ISupportMvmView) mSupportView;
    }

    /**
     * 获取 DataBinding
     *
     * @return
     */
    public V getDataBinding() {
        return mDataBinding;
    }

    /**
     * 获取MvvmViewShank
     *
     * @return
     */
    protected MvvmViewShank getMvvmViewShank() {
        if (mActivity instanceof MvvmViewShank) {
            return (MvvmViewShank) mActivity;
        }
        if (mFragment instanceof MvvmViewShank) {
            return (MvvmViewShank) mFragment;
        }
        return null;
    }

    /**
     * 获取app的ViewModelProvider
     *
     * @return
     */
    @MainThread
    public ViewModelProvider getAppViewModelProvider() {
        return ViewModelProviderFactory.getAppViewModelProvider();
    }

    /**
     * 获取activity的ViewModelProvider
     *
     * @return
     */
    @MainThread
    public ViewModelProvider getActivityViewModelProvider() {
        if (mActivityViewModelProvider == null) {
            mActivityViewModelProvider = new ViewModelProvider(mActivity, mActivity.getDefaultViewModelProviderFactory());
        }
        return mActivityViewModelProvider;
    }

    /**
     * 获取fragment的ViewModelProvider
     *
     * @return
     */
    @MainThread
    public ViewModelProvider getFragmentViewModelProvider() {
        if (mFragmentViewModelProvider == null) {
            mFragmentViewModelProvider = new ViewModelProvider(mFragment, mFragment.getDefaultViewModelProviderFactory());
        }
        return mFragmentViewModelProvider;
    }

    /**
     * 根据AppProvider创建ViewModel
     *
     * @param modelClass
     * @param <VM>
     * @return
     */
    @MainThread
    public <VM extends ViewModel> VM getVmByAppProvider(@NonNull Class<VM> modelClass) {
        return getAppViewModelProvider().get(modelClass);
    }

    /**
     * 根据ActivityViewModelProvider创建ViewModel
     *
     * @param modelClass
     * @param <VM>
     * @return
     */
    @MainThread
    public <VM extends ViewModel> VM getVmByActivityProvider(@NonNull Class<VM> modelClass) {
        return getActivityViewModelProvider().get(modelClass);
    }

    /**
     * 根据ActivityViewModelProvider创建ViewModel
     *
     * @param modelClass
     * @param <VM>
     * @return
     */
    @MainThread
    public <VM extends ViewModel> VM getVmByFragmentProvider(@NonNull Class<VM> modelClass) {
        return getFragmentViewModelProvider().get(modelClass);
    }

    /**
     * 注册事件
     *
     * @param viewModel
     */
    public void registerLiveEvent(BaseViewModel viewModel) {
        viewModel.finishOf.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (mActivity != null) {
                    mActivity.finish();
                    return;
                }
                if (mFragment != null) {
                    FragmentActivity fragmentActivity = mFragment.getActivity();
                    if (fragmentActivity != null) {
                        fragmentActivity.finish();
                    }
                    return;
                }
            }
        });
        viewModel.backPressedOf.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (mActivity != null) {
                    mActivity.onBackPressed();
                    return;
                }
                if (mFragment != null) {
                    FragmentActivity fragmentActivity = mFragment.getActivity();
                    if (fragmentActivity != null) {
                        fragmentActivity.onBackPressed();
                    }
                    return;
                }
            }
        });
    }

    /**
     * 注册mvvm视图句柄
     *
     * @param viewModel
     */
    public void registerMvvmViewShank(BaseViewModel viewModel) {
        MvvmViewShank mvvmViewShank = getMvvmViewShank();
        if (mvvmViewShank != null) {
            viewModel.initMvvmViewShank(mvvmViewShank);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDataBinding != null) {
            mDataBinding.unbind();
        }
    }
}
