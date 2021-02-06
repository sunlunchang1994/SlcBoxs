package android.slc.code.ui.fragment;

import android.slc.code.vm.BaseViewModel;

import androidx.databinding.ViewDataBinding;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 此类是为了兼容0.2.9版本
 * 需要注意的是，部分方法声明类型也需要稍作调整
 *
 * @author slc
 * @date 2021/2/6 11:41
 */
public abstract class MvvmCompatV029Fragment<V extends ViewDataBinding, VM extends BaseViewModel> extends MvvmFragment<V> {
    protected VM viewModel;

    @Override
    public void initViewBefore() {
        super.initViewBefore();
        initViewModel();
    }

    /**
     * 初始化dataBind
     */
    protected void initViewModel() {
        viewModel = getVmByFragmentProvider(getThisVmClass());
    }

    @Override
    public void initViewLater() {
        super.initViewLater();
        registerLiveEvent();
        registerMvvmViewShank();
        if (getDataBinding() != null) {
            bindingVariable();
        }
    }

    /**
     * 获取当前的VmClass
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Class<VM> getThisVmClass() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            return (Class<VM>) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            //如果没有指定泛型参数，则默认使用BaseViewModel
            return (Class<VM>) BaseViewModel.class;
        }
    }

    /**
     * 注册LiveEvent事件
     */
    protected void registerLiveEvent() {
        registerLiveEvent(viewModel);
    }

    /**
     * 注册 MvvmViewShank
     */
    protected void registerMvvmViewShank() {
        registerMvvmViewShank(viewModel);
    }

    /**
     * 绑定Variable
     */
    protected abstract void bindingVariable();

}