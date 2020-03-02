package android.slc.code.ui.fragment;

import android.slc.code.ui.CreateViewAuxiliaryBox;
import android.slc.code.vm.BaseViewModel;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author slc
 * @date 2020/3/2 11:04
 */
public abstract class MvvmFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment {
    protected V dataBinding;
    protected VM viewModel;

    @Override
    protected View interfereLoadView(CreateViewAuxiliaryBox createViewAuxiliaryBox) {
        View contentView = null;
        Object layoutObj = createViewAuxiliaryBox.getLayoutObj();
        if (createViewAuxiliaryBox.getLayoutObj() instanceof Integer) {
            int contentViewLayout = (int) layoutObj;
            dataBinding = DataBindingUtil.inflate(createViewAuxiliaryBox.getInflater(), contentViewLayout, createViewAuxiliaryBox.getContainer(), false);
            if (dataBinding == null) {
                contentView = createViewAuxiliaryBox.getInflater().inflate((Integer) layoutObj, createViewAuxiliaryBox.getContainer(), false);
            } else {
                contentView = dataBinding.getRoot();
            }
        } else if (layoutObj instanceof View) {
            contentView = (View) layoutObj;
            dataBinding = DataBindingUtil.bind(contentView);
        } else {
            throw new ClassCastException("setContentView() type must be int or View");
        }
        initDataBinding();
        return contentView;
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
        viewModel = (VM) getFragmentViewModelProvider().get(modelClass);
        if (dataBinding != null) {
            dataBinding.setLifecycleOwner(this);
            bindingVariable();
        }
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
        return new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(_mActivity.getApplication()));
    }

    /**
     * 获取activity的ViewModelProvider
     *
     * @return
     */
    protected ViewModelProvider getActivityViewModelProvider() {
        return new ViewModelProvider(_mActivity, _mActivity.getDefaultViewModelProviderFactory());
    }

    /**
     * 获取fragment的ViewModelProvider
     *
     * @return
     */
    protected ViewModelProvider getFragmentViewModelProvider() {
        return new ViewModelProvider(this, getDefaultViewModelProviderFactory());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (dataBinding != null) {
            dataBinding.unbind();
        }
    }
}
