package android.slc.commonlibrary.util.compat;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

/**
 * <pre>
 *     author: sun lunchang
 * </pre>
 */
public final class SlcThreadCompatUtils {
    private SlcThreadCompatUtils() {
        throw new AssertionError("No instances.");
    }

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static void post(@NonNull Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            HANDLER.post(runnable);
        }
    }

    public static void postDelayed(@NonNull Runnable runnable, long delayMillis) {
        HANDLER.postDelayed(runnable, delayMillis);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
