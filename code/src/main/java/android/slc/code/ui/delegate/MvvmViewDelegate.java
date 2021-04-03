package android.slc.code.ui.delegate;

import android.app.Activity;
import android.content.Intent;
import android.slc.code.domain.SlcActivityResult;
import android.slc.code.vm.BaseViewModel;
import android.slc.commonlibrary.util.ViewModelProviderFactory;
import android.view.View;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.Utils;

/**
 * 使用与MVVM设计模式的ViewDelegate方法
 *
 * @author slc
 * @date 2021/2/5 16:03
 */
public class MvvmViewDelegate<V extends ViewDataBinding> extends BaseViewDelegate {
    /**
     * ViewDataBing
     */
    protected V mDataBinding;
    /**
     * {@link android.app.Activity}和{@link androidx.fragment.app.Fragment}的{@link ViewModel}提供者
     */
    protected ViewModelProvider mActivityViewModelProvider, mFragmentViewModelProvider;

    public MvvmViewDelegate(ISupportView supportView) {
        super(supportView);
    }

    /**
     * 此处给{@link ViewDataBinding}绑定可生命周期
     *
     * @param createViewAuxiliaryBox
     */
    @Override
    protected void initView(@Nullable CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        super.initView(createViewAuxiliaryBox);
        if (this.mDataBinding != null) {
            this.mDataBinding.setLifecycleOwner(this.mActivity);
        }
    }

    /**
     * 此处使用{@link ViewDataBinding}的方式绑定视图
     *
     * @param createViewAuxiliaryBox
     */
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
     * 获取 {@link ViewDataBinding}
     *
     * @return
     */
    public V getDataBinding() {
        return this.mDataBinding;
    }

    /**
     * 获取application的{@link ViewModelProvider}
     *
     * @return
     */
    @MainThread
    public ViewModelProvider getAppViewModelProvider() {
        return ViewModelProviderFactory.getAppViewModelProvider();
    }

    /**
     * 获取activity的{@link ViewModelProvider}
     *
     * @return
     */
    @MainThread
    public ViewModelProvider getActivityViewModelProvider() {
        if (this.mActivityViewModelProvider == null) {
            if (this.mActivity != null) {
                this.mActivityViewModelProvider = new ViewModelProvider(this.mActivity, this.mActivity.getDefaultViewModelProviderFactory());
            } else if (this.mFragment != null) {
                this.mActivityViewModelProvider = new ViewModelProvider(this.mFragment.getActivity(), this.mFragment.getActivity().getDefaultViewModelProviderFactory());
            }
        }
        return this.mActivityViewModelProvider;
    }

    /**
     * 获取fragment的{@link ViewModelProvider}
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
     * 注册{@link BaseViewModel}的销毁和方法方法
     *
     * @param viewModel
     */
    public void registerLiveEvent(BaseViewModel viewModel) {
        LifecycleOwner lifecycleOwner = MvvmViewDelegate.this.mActivity != null ? MvvmViewDelegate.this.mActivity : MvvmViewDelegate.this.mFragment;
        viewModel.getFinishLiveData().observe(lifecycleOwner, aVoid -> {
            if (MvvmViewDelegate.this.mActivity != null) {
                MvvmViewDelegate.this.mActivity.finish();
                return;
            }
            if (MvvmViewDelegate.this.mFragment != null) {
                FragmentActivity fragmentActivity = MvvmViewDelegate.this.mFragment.getActivity();
                if (fragmentActivity != null) {
                    fragmentActivity.finish();
                }
            }
        });
        viewModel.getBackPressedLiveData().observe(lifecycleOwner, aVoid -> {
            if (MvvmViewDelegate.this.mActivity != null) {
                MvvmViewDelegate.this.mActivity.onBackPressed();
                return;
            }
            if (MvvmViewDelegate.this.mFragment != null) {
                FragmentActivity fragmentActivity = MvvmViewDelegate.this.mFragment.getActivity();
                if (fragmentActivity != null) {
                    fragmentActivity.onBackPressed();
                }
            }
        });
        viewModel.getStartActivityLiveData().observe(lifecycleOwner, startActivityComponent -> {
            Intent intent = new Intent(Utils.getApp(), startActivityComponent.getStartActivityClass());
            if (startActivityComponent.getBundle() != null) {
                intent.putExtras(startActivityComponent.getBundle());
            }
            if (MvvmViewDelegate.this.mActivity != null) {
                MvvmViewDelegate.this.mActivity.startActivity(intent);
                return;
            }
            if (MvvmViewDelegate.this.mFragment != null) {
                MvvmViewDelegate.this.mFragment.startActivity(intent);
            }
        });
        viewModel.getFillResultLiveData().observe(lifecycleOwner, slcActivityResult -> {
            Activity activityTemp = null;
            if (MvvmViewDelegate.this.mActivity != null) {
                activityTemp = MvvmViewDelegate.this.mActivity;
            } else if (MvvmViewDelegate.this.mFragment != null) {
                activityTemp = MvvmViewDelegate.this.mFragment.getActivity();
            }
            if (activityTemp != null) {
                Intent intent = new Intent();
                if (slcActivityResult.getBundle() != null) {
                    intent.putExtras(slcActivityResult.getBundle());
                }
                activityTemp.setResult(slcActivityResult.getResultCode(), intent);
                if (slcActivityResult.isFinish()) {
                    activityTemp.finish();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mDataBinding != null) {
            this.mDataBinding.unbind();
        }
    }
}
