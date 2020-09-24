package android.slc.commonlibrary.util.compat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.slc.commonlibrary.R;

import androidx.core.app.NotificationCompat;

import com.blankj.utilcode.util.Utils;

/**
 * 通知工具
 * 用于快捷发送通知
 *
 * @author android.slc
 * @date 2019/10/25 14:30
 */
public class SlcNotifyCompatUtils {
    public static String DEF_CHANNEL_ID = "CommonNotify";
    private NotificationManager notificationManager;

    public static class Holder {
        private static SlcNotifyCompatUtils INSTANCE = new SlcNotifyCompatUtils(Utils.getApp());
    }

    /**
     * 获取全局唯一实例
     * @return
     */
    public static SlcNotifyCompatUtils getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 隐藏唯一的构造方法
     * @param context
     */
    private SlcNotifyCompatUtils(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null) {
            return;
        }
        //适配Android 8.0+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel(DEF_CHANNEL_ID, "Common Notify", NotificationManager.IMPORTANCE_DEFAULT));
        }
    }

    /**
     * 发送测试通知
     */
    public void sendTestNotify() {
        if (notificationManager != null) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(Utils.getApp(), SlcNotifyCompatUtils.DEF_CHANNEL_ID)
                    .setContentIntent(PendingIntent.getActivity(Utils.getApp(), 0, new Intent("com.android.slc.mle.msg.im"), PendingIntent.FLAG_UPDATE_CURRENT))
                    .setContentTitle("测试通知")
                    .setContentText("测试通知内容")
                    .setGroup(SlcNotifyCompatUtils.DEF_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_slc_notify_test_small)
                    .setAutoCancel(true);
            notificationManager.notify(0, builder.build());
        }
    }

    /**
     * 发送通知
     *
     * @param id
     * @param notification
     */
    public void sendNotify(int id, Notification notification) {
        if (notificationManager != null) {
            notificationManager.notify(id, notification);
        }
    }

    /**
     * 取消通知根据id
     *
     * @param id
     */
    public void cancel(int id) {
        if (notificationManager != null) {
            notificationManager.cancel(id);
        }
    }

    /**
     * 取消所有
     */
    public void cancelAll() {
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }

    /**
     * 添加渠道
     * @param notificationChannel
     */
    public void addNotificationChannel(NotificationChannel notificationChannel) {
        if (notificationManager == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /**
     * 移除渠道id
     *
     * @param channelId
     */
    public void deleteNotificationChannel(String channelId) {
        if (notificationManager == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannel(channelId);
        }
    }
}
