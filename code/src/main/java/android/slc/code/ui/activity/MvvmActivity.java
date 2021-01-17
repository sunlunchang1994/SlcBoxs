package android.slc.code.ui.activity;

import android.os.Bundle;
import android.slc.code.ui.CreateViewAuxiliaryBox;
import android.slc.code.ui.views.ViewDelegate;
import android.slc.code.vm.BaseViewModel;
import android.slc.commonlibrary.util.ViewModelProviderFactory;
import android.view.View;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * mvvm基础activity
 *
 * @author slc
 * @date 2020/3/2 9:48
 */
public abstract class MvvmActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity implements ViewDelegate {
    protected V dataBinding;
    protected VM viewModel;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initViewModel();
        super.initView(savedInstanceState);
        registerLiveEvent();
        registerViewDelegate();
        if (dataBinding != null) {
            dataBinding.setLifecycleOwner(this);
            bindingVariable();
        }
    }

    @Override
    protected void interfereLoadView(CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        Object layoutObj = createViewAuxiliaryBox.getLayoutObj();
        if (layoutObj instanceof Integer) {
            int contentViewLayout = (int) layoutObj;
            dataBinding = DataBindingUtil.setContentView(this, contentViewLayout);
            if (dataBinding == null) {
                setContentView(contentViewLayout);
            }
        } else if (layoutObj instanceof View) {
            View contentView = (View) layoutObj;
            setContentView(contentView);
            dataBinding = DataBindingUtil.bind(contentView);
        } else {
            throw new ClassCastException("setContentView() type must be int or View");
        }
    }

    /**
     * 初始化dataBind
     */
    @SuppressWarnings("unchecked")
    protected void initViewModel() {
        Class modelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            //如果没有指定泛型参数，则默认使用BaseViewModel
            modelClass = BaseViewModel.class;
        }
        viewModel = (VM) getActivityViewModelProvider().get(modelClass);
    }

    /**
     * 注册liveData事件
     */
    protected void registerLiveEvent() {
        viewModel.finishOf.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                finish();
            }
        });
        viewModel.backPressedOf.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                onBackPressed();
            }
        });
    }

    protected void registerViewDelegate() {
        viewModel.initViewDelegate(this);
    }

    /**
     * 绑定Variable
     */
    protected abstract void bindingVariable();

    /**
     * 获取app的ViewModelProvider
     *
     * @return
     */
    protected ViewModelProvider getAppViewModelProvider() {
        return ViewModelProviderFactory.getAppViewModelProvider();
    }

    /**
     * 获取activity的ViewModelProvider
     *
     * @return
     */
    protected ViewModelProvider getActivityViewModelProvider() {
        return new ViewModelProvider(this, getDefaultViewModelProviderFactory());
    }

    @Override
    public AppCompatActivity getActivityContext() {
        return this;
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    @Override
    public ActivityResultCaller getActivityResultCaller() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataBinding != null) {
            dataBinding.unbind();
        }
    }
}
