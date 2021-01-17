package android.slc.commonlibrary.util;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.blankj.utilcode.util.Utils;

/**
 * @author slc
 * @date 2020-08-06 10:48
 */
public class ViewModelProviderFactory {
    /**
     * 全局的ViewModelStore
     */
    private static final ViewModelStore viewModelStore = new ViewModelStore();

    /**
     * 获取AppViewModelProvider
     *
     * @return
     */
    public static ViewModelProvider getAppViewModelProvider() {
        return new ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory.getInstance(Utils.getApp()));
    }
    public static ViewModelStore getAppViewModelStore(){
        return viewModelStore;
    }
}