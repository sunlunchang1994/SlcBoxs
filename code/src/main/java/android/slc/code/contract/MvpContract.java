package android.slc.code.contract;

import androidx.activity.result.ActivityResultCaller;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

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
        AppCompatActivity getMvpContext();

        /**
         * 设置Presenter
         */
        void setPresenter(P presenter);

        /**
         * 获取生命周期所有者
         *
         * @return
         */
        LifecycleOwner getLifecycleOwner();

        /**
         * 获取ActivityResultCaller
         *
         * @return
         */
        ActivityResultCaller getActivityResultCaller();
    }

    interface BasePresenter<V extends BaseMvpView> extends LifecycleObserver {

        /**
         * 开始方法，一般在activity或fragment初始化完试图之后调用
         */
        void init();

        /**
         * 设置model
         *
         * @param view
         */
        void setView(V view);

        /**
         * 销毁
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void destroy();
    }

}
