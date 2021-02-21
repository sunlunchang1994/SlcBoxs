package android.slc.code.ui.activity;

import android.os.Bundle;
import android.slc.code.ui.delegate.BaseViewDelegate;
import android.slc.code.ui.delegate.ISupportView;
import android.slc.code.vm.BarVm;
import android.slc.toolbar.ISlcToolBarDelegate;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;


/**
 * Created by on the way on 2017/12/6.
 */

public abstract class BaseActivity extends EnhanceActivity implements ISupportView {
    protected BarVm mBarVm;
    protected ISlcToolBarDelegate mSlcToolBarDelegate;
    protected BaseViewDelegate mViewDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewDelegate(savedInstanceState);
    }

    protected void initViewDelegate(@Nullable Bundle savedInstanceState) {
        mViewDelegate = new BaseViewDelegate(this);
        mViewDelegate.onCreate(savedInstanceState);
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
        mBarVm = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(BarVm.class);
        mBarVm.init(this);
        mBarVm.syncBarStyle();
    }

    /**
     * 获取ToolBar操作
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
     * 获取 ISlcToolBarDelegate
     *
     * @return
     */
    protected ISlcToolBarDelegate initSlcToolBarDelegate() {
        return null;
    }

}
