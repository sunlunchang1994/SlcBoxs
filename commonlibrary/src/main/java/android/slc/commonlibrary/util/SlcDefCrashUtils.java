package android.slc.commonlibrary.util;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/27
 *     desc  : utils about crash
 * </pre>
 */
public final class SlcDefCrashUtils {

    private static String defaultDir;
    private static String dir;
    private static String versionName;
    private static int versionCode;

    private static final String FILE_SEP = System.getProperty("file.separator");
    @SuppressLint("SimpleDateFormat")
    private static final Format FORMAT = new SimpleDateFormat("MM-dd HH-mm-ss");

    private static final UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER;
    private static final UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER;

    private static OnCrashListener sOnCrashListener;

    static {
        try {
            PackageInfo pi = SlcUtils.getApp()
                    .getPackageManager()
                    .getPackageInfo(SlcUtils.getApp().getPackageName(), 0);
            if (pi != null) {
                versionName = pi.versionName;
                versionCode = pi.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

        UNCAUGHT_EXCEPTION_HANDLER = new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                if (e == null) {
                    if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, null);
                    } else {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                    return;
                }
                if (Looper.getMainLooper().getThread() == t) {//如果是主线程异常则不打印日志，防止异步造成的不必要的空指针导致ui卡顿
                    if (sOnCrashListener != null && sOnCrashListener.onCrash("主线程异常", e)) {
                        return;
                    }
                }
                final String time = FORMAT.format(new Date(System.currentTimeMillis()));
                final StringBuilder sb = new StringBuilder();
                final String head = "************* Log Head ****************" +
                        "\nTime Of Crash      : " + time +
                        "\nDevice Manufacturer: " + Build.MANUFACTURER +
                        "\nDevice Model       : " + Build.MODEL +
                        "\nAndroid Version    : " + Build.VERSION.RELEASE +
                        "\nAndroid SDK        : " + Build.VERSION.SDK_INT +
                        "\nApp VersionName    : " + versionName +
                        "\nApp VersionCode    : " + versionCode +
                        "\n************* Log Head ****************\n\n";
                sb.append(head)
                        .append(SlcThrowableUtils.getFullStackTrace(e));
                final String crashInfo = sb.toString();
                final String fullPath = (dir == null ? defaultDir : dir) + time + ".txt";
                if (createOrExistsFile(fullPath)) {
                    input2File(crashInfo, fullPath);
                } else {
                    Log.e("SlcCrashUtils", "create " + fullPath + " failed!");
                }

                if (sOnCrashListener != null && sOnCrashListener.onCrash(crashInfo, e)) {
                    return;
                }
                if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, e);
                }
            }
        };
    }

    private SlcDefCrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Initialization.
     * <p>Must hold {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init() {
        init("");
    }

    /**
     * Initialization
     * <p>Must hold {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDir The directory of saving crash information.
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(@NonNull final File crashDir) {
        init(crashDir.getAbsolutePath(), null);
    }

    /**
     * Initialization
     * <p>Must hold {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDirPath The directory's path of saving crash information.
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(final String crashDirPath) {
        init(crashDirPath, null);
    }

    /**
     * Initialization
     * <p>Must hold {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param onCrashListener The crash listener.
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(final OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }

    /**
     * Initialization
     * <p>Must hold {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDir        The directory of saving crash information.
     * @param onCrashListener The crash listener.
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(@NonNull final File crashDir, final OnCrashListener onCrashListener) {
        init(crashDir.getAbsolutePath(), onCrashListener);
    }

    /**
     * Initialization
     * <p>Must hold {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDirPath    The directory's path of saving crash information.
     * @param onCrashListener The crash listener.
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(final String crashDirPath, final OnCrashListener onCrashListener) {
        if (isSpace(crashDirPath)) {
            dir = null;
        } else {
            dir = crashDirPath.endsWith(FILE_SEP) ? crashDirPath : crashDirPath + FILE_SEP;
        }
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && SlcUtils.getApp().getExternalCacheDir() != null)
            defaultDir = SlcUtils.getApp().getExternalCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        else {
            defaultDir = SlcUtils.getApp().getCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        }
        sOnCrashListener = onCrashListener;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Looper.loop();
                    } catch (Throwable ex) {
                        Log.i("handleException", "有主线程异常");
                        //ex.printStackTrace();
                        UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(Thread.currentThread(), ex);
                    }
                }
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
    }

    ///////////////////////////////////////////////////////////////////////////
    // interface
    ///////////////////////////////////////////////////////////////////////////

    public interface OnCrashListener {
        boolean onCrash(String crashInfo, Throwable e);
    }

    ///////////////////////////////////////////////////////////////////////////
    // other utils methods
    ///////////////////////////////////////////////////////////////////////////

    private static void input2File(final String input, final String filePath) {
        Future<Boolean> submit = Executors.newSingleThreadExecutor().submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(filePath, true));
                    bw.write(input);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (bw != null) {
                            bw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            if (submit.get()) return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.e("SlcCrashUtils", "write crash info to " + filePath + " failed!");
    }

    private static boolean createOrExistsFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
