package android.slc.code.ui.activity;

import android.os.Bundle;
import android.slc.code.ui.delegate.ISupportMvmView;
import android.slc.code.ui.delegate.MvvmViewDelegate;
import android.slc.code.ui.views.MvvmViewShank;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

/**
 * mvvm基础activity
 *
 * @author slc
 * @date 2020/3/2 9:48
 */
public abstract class MvvmActivity<V extends ViewDataBinding> extends BaseActivity implements MvvmViewShank, ISupportMvmView {

    @Override
    protected void initViewDelegate(@Nullable Bundle savedInstanceState) {
        mViewDelegate = new MvvmViewDelegate<V>(this);
        mViewDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void initViewModel() {

    }

    /**
     * 获取mvvm代理
     *
     * @return
     */
    protected MvvmViewDelegate<V> getMvvmViewDelegate() {
        return (MvvmViewDelegate) mViewDelegate;
    }

    @Override
    public AppCompatActivity getActivityContext() {
        return this;
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    @Override
    public ActivityResultCaller getActivityResultCaller() {
        return this;
    }

}
