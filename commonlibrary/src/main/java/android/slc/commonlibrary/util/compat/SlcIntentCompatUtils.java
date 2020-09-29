package android.slc.commonlibrary.util.compat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;

public class SlcIntentCompatUtils {
    /**
     * 通过安卓自带的文件打开系统打开文件
     *
     * @param path
     */
    public static Intent openAndroidFile(String path) {
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
                Uri contentUri = FileProvider.getUriForFile(Utils.getApp(),
                        Utils.getApp().getPackageName() + ".provider",
                        file);
                intent.setDataAndType(contentUri, SlcFileCompatUtils.getMimeType(path));
            } else {
                Uri uri = Uri.fromFile(file);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(uri, SlcFileCompatUtils.getMimeType(path));
            }
        }
        return intent;
    }

    public static Intent openAndroidFile(Uri uri, String type) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//兼容7.0及以上的写法
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.setDataAndType(uri, type);
        return intent;
    }

    /**
     * 获取一个url intent
     *
     * @param uri
     * @return
     */
    public static Intent getUriIntent(String uri) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    }

    /**
     * 获取一个url intent
     *
     * @param uri
     * @return
     */
    public static Intent getUriIntent(Uri uri) {
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static void takePhoto(Context context,
                                 ActivityResultCaller activityResultCaller,
                                 ActivityResultCallback<Uri> activityResultCallback) {
        takePhoto(context, activityResultCaller, SlcUriCompatUtils.image2UriByInsert(context), activityResultCallback);
    }

    public static void takePhoto(Context context,
                                 ActivityResultCaller activityResultCaller,
                                 Uri photoUri,
                                 ActivityResultCallback<Uri> activityResultCallback) {
        activityResultCaller.registerForActivityResult(new ActivityResultContract<Uri, Uri>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Uri input) {
                final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, input);
                return intent;
            }

            @Override
            public Uri parseResult(int resultCode, @Nullable Intent intent) {
                if (resultCode == Activity.RESULT_OK) {
                    return photoUri;
                } else {
                    return null;
                }
            }
        }, result -> {
            if (result != null) {
                SlcUriCompatUtils.notifyMediaScannerScanFile(context.getApplicationContext(), (path, uri) -> new Handler(Looper.getMainLooper()).post(() -> activityResultCallback.onActivityResult(uri)), result);
            } else {
                activityResultCallback.onActivityResult(result);
            }
        }).launch(photoUri);
    }

    public static void cutOutPhoto(Context context,
                                   ActivityResultCaller activityResultCaller,
                                   Uri photoUri,
                                   ActivityResultCallback<Uri> activityResultCallback) {
        cutOutPhoto(context, activityResultCaller, photoUri, SlcUriCompatUtils.image2UriByInsert(context), activityResultCallback);
    }

    public static void cutOutPhoto(Context context,
                                   ActivityResultCaller activityResultCaller,
                                   Uri photoUri,
                                   Uri outPutUri,
                                   ActivityResultCallback<Uri> activityResultCallback) {
        Bundle bundle = new Bundle();
        bundle.putString("crop", "true");
        bundle.putInt("aspectX", 1);
        bundle.putInt("aspectY", 1);
        bundle.putInt("outputX", 256);
        bundle.putInt("outputY", 256);
        bundle.putBoolean("scale", true);
        //这里是坑
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            bundle.putParcelable(MediaStore.EXTRA_OUTPUT, outPutUri);
        } else {
            bundle.putParcelable(MediaStore.EXTRA_OUTPUT, Uri.fromFile(SlcUriCompatUtils.uri2File(outPutUri)));
        }

        bundle.putBoolean("return-data", false);
        bundle.putString("outputFormat", Bitmap.CompressFormat.PNG.toString());
        bundle.putBoolean("noFaceDetection", true);
        cutOutPhoto(context, activityResultCaller, photoUri, bundle, activityResultCallback);
    }

    public static void cutOutPhoto(Context context,
                                   ActivityResultCaller activityResultCaller,
                                   Uri photoUri,
                                   Bundle bundle,
                                   ActivityResultCallback<Uri> activityResultCallback) {
        activityResultCaller.registerForActivityResult(new ActivityResultContract<Uri, Uri>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Uri input) {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.setDataAndType(input, context.getContentResolver().getType(input));
                intent.putExtras(bundle);
                return intent;
            }

            @Override
            public Uri parseResult(int resultCode, @Nullable Intent intent) {
                if (resultCode == Activity.RESULT_OK) {
                    return bundle.getParcelable(MediaStore.EXTRA_OUTPUT);
                } else {
                    return null;
                }
            }
        }, result -> {
            if (result != null) {
                SlcUriCompatUtils.notifyMediaScannerScanFile(context.getApplicationContext(), (path, uri) -> new Handler(Looper.getMainLooper()).post(() -> activityResultCallback.onActivityResult(uri)), result);
            } else {
                activityResultCallback.onActivityResult(result);
            }
        }).launch(photoUri);
    }
}
