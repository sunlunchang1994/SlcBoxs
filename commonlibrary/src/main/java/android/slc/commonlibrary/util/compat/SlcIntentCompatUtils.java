package android.slc.commonlibrary.util.compat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.MimeTypeMap;

import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.ToastUtils;

import java.io.File;

public class SlcIntentCompatUtils {
    /**
     * 通过安卓自带的文件打开系统打开文件
     * @param context
     * @param path
     */
    public static void openAndroidFile(Context context, String path) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (path.startsWith("http")) {
            String extension = MimeTypeMap.getFileExtensionFromUrl(path);
            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            intent.setDataAndType(Uri.parse(path), mimeType);
        } else {
            File file = new File(path);
            if (!file.exists()) {
                throw new IllegalStateException("文件不存在");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(context.getApplicationContext(),
                        context.getApplicationContext().getPackageName() +
                                ".provider", file);
                intent.setDataAndType(contentUri, SlcFileCompatUtils.getMimeType(path));
            } else {
                Uri uri = Uri.fromFile(file);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(uri, SlcFileCompatUtils.getMimeType(path));
            }
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showShort("找不到打开此文件的应用！");
        }
    }

    /**
     * 获取一个url intent
     * @param uri
     * @return
     */
    public static Intent getUriIntent(String uri) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    }
    /**
     * 获取一个url intent
     * @param uri
     * @return
     */
    public static Intent getUriIntent(Uri uri) {
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
