package android.slc.commonlibrary.util.compat;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThrowableUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/27
 *     desc  : utils about crash
 * </pre>
 */
public final class SlcCrashUtils {

    private static final String FILE_SEP = System.getProperty("file.separator");

    private static final Thread.UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

    private SlcCrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Initialization.
     */
    @SuppressLint("MissingPermission")
    public static void init() {
        init("");
    }

    /**
     * Initialization
     *
     * @param crashDir The directory of saving crash information.
     */
    public static void init(@NonNull final File crashDir) {
        init(crashDir.getAbsolutePath(), null);
    }

    /**
     * Initialization
     *
     * @param crashDirPath The directory's path of saving crash information.
     */
    public static void init(final String crashDirPath) {
        init(crashDirPath, null);
    }

    /**
     * Initialization
     *
     * @param onCrashListener The crash listener.
     */
    public static void init(final OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }

    /**
     * Initialization
     *
     * @param crashDir        The directory of saving crash information.
     * @param onCrashListener The crash listener.
     */
    public static void init(@NonNull final File crashDir, final OnCrashListener onCrashListener) {
        init(crashDir.getAbsolutePath(), onCrashListener);
    }

    /**
     * Initialization
     *
     * @param crashDirPath    The directory's path of saving crash information.
     * @param onCrashListener The crash listener.
     */
    public static void init(final String crashDirPath, final OnCrashListener onCrashListener) {
        String dirPath;
        if (StringUtils.isSpace(crashDirPath)) {
            if (SDCardUtils.isSDCardEnableByEnvironment()
                    && Utils.getApp().getExternalFilesDir(null) != null)
                dirPath = Utils.getApp().getExternalFilesDir(null) + FILE_SEP + "crash" + FILE_SEP;
            else {
                dirPath = Utils.getApp().getFilesDir() + FILE_SEP + "crash" + FILE_SEP;
            }
        } else {
            dirPath = crashDirPath.endsWith(FILE_SEP) ? crashDirPath : crashDirPath + FILE_SEP;
        }
        Thread.setDefaultUncaughtExceptionHandler(getUncaughtExceptionHandler(dirPath, onCrashListener));
    }

    private static Thread.UncaughtExceptionHandler getUncaughtExceptionHandler(final String dirPath,
                                                                               final OnCrashListener onCrashListener) {
        return new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull final Thread t, @NonNull final Throwable e) {
                if (Looper.getMainLooper().getThread() == t) {//如果是主线程异常则不打印日志，防止异步造成的不必要的空指针导致ui卡顿
                    if (onCrashListener != null && onCrashListener.onCrash("主线程异常", e)) {
                        return;
                    }
                }
                final String time = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss").format(new Date());
                final StringBuilder sb = new StringBuilder();
                final String head = "************* Log Head ****************" +
                        "\nTime Of Crash      : " + time +
                        "\nDevice Manufacturer: " + Build.MANUFACTURER +
                        "\nDevice Model       : " + Build.MODEL +
                        "\nAndroid Version    : " + Build.VERSION.RELEASE +
                        "\nAndroid SDK        : " + Build.VERSION.SDK_INT +
                        "\nApp VersionName    : " + AppUtils.getAppVersionName() +
                        "\nApp VersionCode    : " + AppUtils.getAppVersionCode() +
                        "\n************* Log Head ****************\n\n";
                sb.append(head).append(ThrowableUtils.getFullStackTrace(e));
                final String crashInfo = sb.toString();
                final String crashFile = dirPath + time + ".txt";
                FileIOUtils.writeFileFromString(crashFile, crashInfo, true);

                if (onCrashListener != null && onCrashListener.onCrash(crashInfo, e)) {
                    return;
                }

                if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, e);
                }
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////
    // interface
    ///////////////////////////////////////////////////////////////////////////

    public interface OnCrashListener {
        boolean onCrash(String crashInfo, Throwable e);
    }
}
