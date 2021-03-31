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
 * 基础的activity，在系统的{@link android.app.Activity#onCreate(Bundle)}中细分化了方法
 * {@link android.app.Activity#onCreate(Bundle)}方法已交由{@link BaseViewDelegate}处理
 */
public abstract class BaseActivity extends EnhanceActivity implements ISupportView {
    /**
     * 处理通知栏状态的viewModel
     */
    protected BarVm mBarVm;
    /**
     * 工具栏代理
     */
    protected ISlcToolBarDelegate mSlcToolBarDelegate;
    /**
     * 基础视图代理
     */
    protected BaseViewDelegate mViewDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewDelegate(savedInstanceState);
    }

    /**
     * 初始化ViewDelegate
     *
     * @param savedInstanceState
     */
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
     * 获取 ISlcToolBarDelegate
     *
     * @return
     */
    protected ISlcToolBarDelegate initSlcToolBarDelegate() {
        return null;
    }

}
