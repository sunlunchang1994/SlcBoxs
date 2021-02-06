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

    public MvvmViewDelegate(ISupportView supportView) {
        super(supportView);
    }

    @Override
    protected void initView(@Nullable CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        super.initView(createViewAuxiliaryBox);
        if (this.mDataBinding != null) {
            this.mDataBinding.setLifecycleOwner(this.mActivity);
        }
    }

    @Override
    protected void interfereLoadView(CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        if (this.mActivity != null) {
            Object layoutObj = this.mSupportView.setContentView();
            if (layoutObj instanceof Integer) {
                int contentViewLayout = (int) layoutObj;
                this.mDataBinding = DataBindingUtil.setContentView(this.mActivity, contentViewLayout);
                if (this.mDataBinding == null) {
                    this.mActivity.setContentView(contentViewLayout);
                }
            } else if (layoutObj instanceof View) {
                View contentView = (View) layoutObj;
                this.mActivity.setContentView((contentView));
                this.mDataBinding = DataBindingUtil.bind(contentView);
            } else {
                throw new ClassCastException("setContentView() type must be int or View");
            }
            return;
        }
        if (this.mFragment != null) {
            Object layoutObj = this.mSupportView.setContentView();
            if (layoutObj instanceof Integer) {
                int contentViewLayout = (int) layoutObj;
                this.mDataBinding = DataBindingUtil.inflate(createViewAuxiliaryBox.getInflater(), contentViewLayout, createViewAuxiliaryBox.getContainer(), false);
                if (this.mDataBinding == null) {
                    this.mContentView = createViewAuxiliaryBox.getInflater().inflate((Integer) layoutObj, createViewAuxiliaryBox.getContainer(), false);
                } else {
                    this.mContentView = this.mDataBinding.getRoot();
                }
            } else if (layoutObj instanceof View) {
                this.mContentView = (View) layoutObj;
                this.mDataBinding = DataBindingUtil.bind(this.mContentView);
            } else {
                throw new ClassCastException("setContentView() type must be int or View");
            }
        }
    }

    /**
     * 获取 DataBinding
     *
     * @return
     */
    public V getDataBinding() {
        return this.mDataBinding;
    }

    /**
     * 获取MvvmViewShank
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
        if (this.mActivityViewModelProvider == null) {
            if (this.mActivity != null) {
                this.mActivityViewModelProvider = new ViewModelProvider(this.mActivity, this.mActivity.getDefaultViewModelProviderFactory());
            }else if(this.mFragment!=null){
                this.mActivityViewModelProvider = new ViewModelProvider(this.mFragment.getActivity(), this.mFragment.getActivity().getDefaultViewModelProviderFactory());
            }
        }
        return this.mActivityViewModelProvider;
    }

    /**
     * 获取fragment的ViewModelProvider
     *
     * @return
     */
    @MainThread
    public ViewModelProvider getFragmentViewModelProvider() {
        if (this.mFragmentViewModelProvider == null) {
            this.mFragmentViewModelProvider = new ViewModelProvider(this.mFragment, this.mFragment.getDefaultViewModelProviderFactory());
        }
        return this.mFragmentViewModelProvider;
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
     * 根据FragmentViewModelProvider创建ViewModel
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
                if (MvvmViewDelegate.this.mActivity != null) {
                    MvvmViewDelegate.this.mActivity.finish();
                    return;
                }
                if (MvvmViewDelegate.this.mFragment != null) {
                    FragmentActivity fragmentActivity = MvvmViewDelegate.this.mFragment.getActivity();
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
                if (MvvmViewDelegate.this.mActivity != null) {
                    MvvmViewDelegate.this.mActivity.onBackPressed();
                    return;
                }
                if (MvvmViewDelegate.this.mFragment != null) {
                    FragmentActivity fragmentActivity = MvvmViewDelegate.this.mFragment.getActivity();
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
        if (this.mDataBinding != null) {
            this.mDataBinding.unbind();
        }
    }
}
