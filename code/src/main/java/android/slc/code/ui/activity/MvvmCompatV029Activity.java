package android.slc.code.ui.activity;

import android.os.Bundle;
import android.slc.code.vm.BaseViewCompatV0395Model;
import android.slc.code.vm.BaseViewModel;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 此类是为了兼容0.2.9版本，新项目不推荐继承此类，请直接使用{@link MvvmActivity}
 * 需要注意的是，部分方法声明类型也需要稍作调整
 *
 * @author slc
 * @date 2021/2/6 11:41
 */
public abstract class MvvmCompatV029Activity<V extends ViewDataBinding, VM extends BaseViewCompatV0395Model> extends MvvmCompatV0395Activity<V> {
    protected VM viewModel;

    @Override
    protected void initViewDelegate(@Nullable Bundle savedInstanceState) {
        super.initViewDelegate(savedInstanceState);
    }

    @Override
    public void initViewBefore() {
        super.initViewBefore();
        initViewModel();
    }

    protected void initViewModel() {
        viewModel = getVmByActivityProvider(getThisVmClass());
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
            return (Class<VM>) BaseViewCompatV0395Model.class;
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