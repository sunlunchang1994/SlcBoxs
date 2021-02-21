package android.slc.commonlibrary.util.compat;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;

import java.util.List;

public class SlcAppCompatUtils {
    /**
     * 推出app
     */
    public static void exitApp() {
        //注意：不能先杀掉主进程，否则逻辑代码无法继续执行，需先杀掉相关进程最后杀掉主进程
        ActivityManager mActivityManager = (ActivityManager) Utils.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid != Process.myPid()) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        Process.killProcess(android.os.Process.myPid());
        AppUtils.exitApp();
    }
}
