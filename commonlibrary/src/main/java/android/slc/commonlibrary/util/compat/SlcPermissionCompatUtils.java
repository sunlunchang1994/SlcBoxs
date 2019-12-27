package android.slc.commonlibrary.util.compat;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * 权限工具
 *
 * @author android.slc
 * @date 2019/10/25 14:30
 */
public class SlcPermissionCompatUtils {
    /**
     * 监察是否拥有该权限
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkSelfPermission(@NonNull Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
