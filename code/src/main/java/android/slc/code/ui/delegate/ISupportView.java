package android.slc.code.ui.delegate;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * 对{@link android.app.Activity}和{@link androidx.fragment.app.Fragment}生命周期方法的拓展
 * 具体使用方式可查看实现类
 *
 * @author slc
 * @date 2021/2/5 15:11
 */
public interface ISupportView {
    /**
     * 获取内容视图
     *
     * @return
     */
    Object setContentView();

    /**
     * 绑定视图之前调用此方法
     * 在此处，适合做以下事件
     * 1、获取{@link android.content.Intent}中的数据
     * 2、调用{@link android.app.Activity#registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks)}
     * 3、初始化一些必要数据，为绑定试图做准备
     */
    void initViewBefore();

    /**
     * 绑定试图
     * 在此处，可初始化视图，如{@link android.app.Activity#findViewById(int)}
     *
     * @param savedInstanceState
     */
    void onBindView(@Nullable Bundle savedInstanceState);

    /**
     * 绑定视图之后调用此方法
     * 在此处，可做一些绑定数据的工作，
     */
    void initViewLater();
}
