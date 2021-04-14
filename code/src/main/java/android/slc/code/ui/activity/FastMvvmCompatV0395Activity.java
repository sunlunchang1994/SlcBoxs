package android.slc.code.ui.activity;

import android.slc.code.vm.BaseViewCompatV0395Model;

import androidx.databinding.ViewDataBinding;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author slc
 * @date 2021/4/14 13:49
 */
public abstract class FastMvvmCompatV0395Activity<V extends ViewDataBinding, VM extends BaseViewCompatV0395Model> extends MvvmCompatV0395Activity<V> {
    protected VM viewModel;

    @Override
    public void initViewBefore() {
        super.initViewBefore();
        initViewModel();
    }

    protected void initViewModel() {
        viewModel = getVmByActivityProvider(getThisVmClass());
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
            return (Class<VM>) BaseViewCompatV0395Model.class;
        }
    }

    @Override
    public void initViewLater() {
        super.initViewLater();
        registerLiveEvent();
    }

    /**
     * 注册LiveEvent事件
     */
    protected void registerLiveEvent() {
        registerLiveEvent(viewModel);
        registerMvvmViewShank(viewModel);
    }

}
