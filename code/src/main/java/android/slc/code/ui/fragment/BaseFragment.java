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
 */
public abstract class BaseFragment extends EnhanceFragment implements ISupportView {
    protected BarVm mBarVm;
    protected ISlcToolBarDelegate mSlcToolBarDelegate;
    protected BaseViewDelegate mViewDelegate;
    private LayoutInflater createViewInflater;
    private ViewGroup createViewContainer;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewDelegate(savedInstanceState);
        return mViewDelegate.getContentView();
    }

    /**
     * 初始化ViewDelegate
     *
     * @param savedInstanceState
     */
    protected void initViewDelegate(@Nullable Bundle savedInstanceState) {
        mViewDelegate = new BaseViewDelegate(this);
        mViewDelegate.onCreate(this.createViewInflater, this.createViewContainer, savedInstanceState);
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

    @Override
    public void initViewBefore() {

    }

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
