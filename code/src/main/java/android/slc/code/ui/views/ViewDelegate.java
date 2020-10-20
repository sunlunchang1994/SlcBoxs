package android.slc.code.ui.views;

import androidx.activity.result.ActivityResultCaller;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

/**
 * @author slc
 * @date 2020/3/12 9:40
 * @email sunlunchang@gmail.com
 */
public interface ViewDelegate {
    AppCompatActivity getActivityContext();

    LifecycleOwner getLifecycleOwner();

    ActivityResultCaller getActivityResultCaller();
}
