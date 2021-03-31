package android.slc.code.ui.views;

import androidx.activity.result.ActivityResultCaller;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

/**
 * MVVM设计模时传递给{@link androidx.lifecycle.ViewModel}的视图句柄，用户更加便捷的对视图进行操作
 * 事实上，让{@link ViewModel}持有视图是不允许的，哪怕是{@link android.content.Context}也不允许，但为了某些时候方便写代码，此处还是这么做了
 * 如有更好且合理的做法请告诉作者
 *
 * @author slc
 * @date 2020/3/12 9:40
 * @email sunlunchang@gmail.com
 */
public interface MvvmViewShank {
    AppCompatActivity getActivityContext();

    LifecycleOwner getLifecycleOwner();

    ActivityResultCaller getActivityResultCaller();
}
