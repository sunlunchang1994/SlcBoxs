package android.slc.code.contract;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.os.Bundle;
import android.slc.code.ui.activity.MvpActivity;

/**
 * Created by on the way on 2018/11/5.
 */

public interface MvpContract {
    interface BaseMvpView<P extends BasePresenter> {
        /**
         * 获取上下文环境mvp模式
         *
         * @return
         */
        MvpActivity getMvpContext();

        /**
         * 设置Presenter
         */
        void setPresenter(P presenter);

        /**
         * 获取生命周期管理工具
         *
         * @return
         */
        Lifecycle getLifecycle();

        /**
         * 获取生命周期所有者
         *
         * @return
         */
        LifecycleOwner getLifecycleOwner();
    }

    interface BasePresenter<V extends BaseMvpView> extends LifecycleObserver {
        /**
         * 设置model
         *
         * @param view
         */
        void setView(V view);

        /**
         * 开始方法，一般在activity或fragment初始化完试图之后调用
         */
        void init();

        /**
         * activity的结果返回
         *
         * @param requestCode
         * @param resultCode
         * @param data
         */
        void onActivityResult(int requestCode, int resultCode, Intent data);

        /**
         * fragment的结果返回
         *
         * @param requestCode
         * @param resultCode
         * @param data
         */
        void onFragmentResult(int requestCode, int resultCode, Bundle data);

        /**
         * 销毁
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void destroy();
    }

}
