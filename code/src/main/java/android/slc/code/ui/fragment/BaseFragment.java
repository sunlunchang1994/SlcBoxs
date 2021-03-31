package android.slc.code.ui.fragment;

import android.os.Bundle;
import android.slc.code.ui.delegate.BaseViewDelegate;
import android.slc.code.ui.delegate.ISupportView;
import android.slc.code.vm.BarVm;
import android.slc.toolbar.ISlcToolBarDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by on the way on 2017/12/6.
 * 基础的activity，在系统的{@link androidx.fragment.app.Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}中细分化了方法
 * {@link androidx.fragment.app.Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}方法已交由{@link BaseViewDelegate}处理
 */
public abstract class BaseFragment extends EnhanceFragment implements ISupportView {
    protected BarVm mBarVm;
    protected ISlcToolBarDelegate mSlcToolBarDelegate;
    protected BaseViewDelegate mViewDelegate;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewDelegate(inflater, container, savedInstanceState);
        return mViewDelegate.getContentView();
    }

    /**
     * 初始化ViewDelegate
     *
     * @param savedInstanceState
     */
    protected void initViewDelegate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                    @Nullable Bundle savedInstanceState) {
        mViewDelegate = new BaseViewDelegate(this);
        mViewDelegate.onCreate(inflater, container, savedInstanceState);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState) {
        initBarVm();
        mSlcToolBarDelegate = initSlcToolBarDelegate();
    }

    /**
     * 初始化BarVm
     */
    protected void initBarVm() {
        mBarVm = new ViewModelProvider(_mActivity, _mActivity.getDefaultViewModelProviderFactory()).get(BarVm.class);
        mBarVm.init(_mActivity);
        mBarVm.syncBarStyle();
    }

    /**
     * 获取 ISlcToolBarDelegate
     *
     * @return
     */
    protected ISlcToolBarDelegate initSlcToolBarDelegate() {
        return null;
    }

    /**
     * 获取SlcToolBarDelegate
     *
     * @param <T>
     * @return
     */
    protected <T extends ISlcToolBarDelegate> T getSlcToolBarDelegate() {
        return (T) mSlcToolBarDelegate;
    }

    /**
     * 初始化视图之前的回调，详细解析在{@link ISupportView}
     */
    @Override
    public void initViewBefore() {

    }

    /**
     * 初始化视图之后的回调，详细解析在{@link ISupportView}
     */
    @Override
    public void initViewLater() {

    }

    /**
     * 获取contentView
     *
     * @return
     */
    protected View getContentView() {
        return this.mViewDelegate.getContentView();
    }

    /**
     * findViewById
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findViewById(@IdRes int id) {
        return this.mViewDelegate.getContentView().findViewById(id);
    }
}
