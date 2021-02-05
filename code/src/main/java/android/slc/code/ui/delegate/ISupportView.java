package android.slc.code.ui.delegate;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @author slc
 * @date 2021/2/5 15:11
 */
public interface ISupportView {
    /**
     * 获取内容视图
     * @return
     */
    Object setContentView();
    /**
     * 绑定试图
     *
     * @param savedInstanceState
     */
    void onBindView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化视图之前调用此方法
     */
    void initViewBefore();

    /**
     * 初始化试图之后调用此方法
     */
    void initViewLater();
}
