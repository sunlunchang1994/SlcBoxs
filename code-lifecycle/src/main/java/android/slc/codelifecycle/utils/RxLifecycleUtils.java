package android.slc.codelifecycle.utils;

import android.app.Activity;
import android.slc.rxlifecycle.RxLifecycleDelegate;
import android.slc.rxlifecycle.RxLifecycleDelegateImp;

import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.ActivityUtils;

/**
 * @author slc
 * @date 2021/4/2 12:43
 */
public class RxLifecycleUtils {
    public static RxLifecycleDelegate createByTopActivity() {
        Activity activity = ActivityUtils.getTopActivity();
        if (ActivityUtils.getTopActivity() instanceof LifecycleOwner) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) activity;
            return RxLifecycleDelegateImp.create(lifecycleOwner.getLifecycle());
        }
        return null;
    }
}
