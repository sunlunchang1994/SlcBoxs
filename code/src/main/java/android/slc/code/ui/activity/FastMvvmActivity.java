package android.slc.code.ui.activity;

import android.slc.code.vm.BaseViewCompatV0395Model;
import android.slc.code.vm.BaseViewModel;

import androidx.databinding.ViewDataBinding;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 快速搭建 mvvm activity
 * 省去注册的部分
 *
 * @author slc
 * @date 2021/4/14 13:42
 */
public abstract class FastMvvmActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends MvvmActivity<V> {
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
    }

}
