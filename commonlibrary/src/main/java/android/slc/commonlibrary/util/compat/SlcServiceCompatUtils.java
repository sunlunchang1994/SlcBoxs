package android.slc.commonlibrary.util.compat;

import android.app.Notification;
import android.app.Service;
import android.os.Build;

import androidx.annotation.NonNull;

/**
 * @author slc
 * @date 2021/3/17 11:06
 */
public class SlcServiceCompatUtils {
    /**
     * 启动前台服务
     *
     * @param service
     * @param id
     * @param notification
     */
    public static void startForeground(Service service, int id, Notification notification) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            service.startForeground(id, notification);
        }
    }

    /**
     * 启动前台服务
     *
     * @param service
     * @param id
     * @param notification
     * @param foregroundServiceType
     */
    public static void startForeground(Service service, int id, @NonNull Notification notification, int foregroundServiceType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            service.startForeground(id, notification, foregroundServiceType);
        }
    }

}
