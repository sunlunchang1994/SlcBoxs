package android.slc.code.ui.activity;

import android.slc.code.ui.CreateViewAuxiliaryBox;
import android.slc.code.vm.BaseViewModel;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * mvvm基础activity
 *
 * @author slc
 * @date 2020/3/2 9:48
 */
public abstract class MvvmActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity {
    protected V dataBinding;
    protected VM viewModel;

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
        initDataBinding();
    }

    /**
     * 初始化dataBind
     */
    @SuppressWarnings("unchecked")
    protected void initDataBinding() {
        Class modelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            //如果没有指定泛型参数，则默认使用BaseViewModel
            modelClass = BaseViewModel.class;
        }
        viewModel = (VM) getActivityViewModelProvider().get(modelClass);
        registerLiveEvent();
        if (dataBinding != null) {
            dataBinding.setLifecycleOwner(this);
            bindingVariable();
        }
    }

    /**
     * 注册liveData事件
     */
    protected void registerLiveEvent() {
        viewModel.getFinishLiveData().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                finish();
            }
        });
        viewModel.getBackPressedLiveData().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                onBackPressed();
            }
        });
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
        return new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
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
    protected void onDestroy() {
        super.onDestroy();
        if (dataBinding != null) {
            dataBinding.unbind();
        }
    }
}
