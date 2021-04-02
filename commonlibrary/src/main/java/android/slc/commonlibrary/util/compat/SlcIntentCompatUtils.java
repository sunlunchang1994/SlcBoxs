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
import android.slc.commonlibrary.util.compat.po.CutOutPhoto;
import android.webkit.MimeTypeMap;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.Utils;

import java.io.File;

public class SlcIntentCompatUtils {
    /**
     * 通过安卓自带的文件打开系统打开文件
     *
     * @param path
     */
    public static Intent getOpenAndroidFileIntent(String path) {
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

    public static Intent getOpenAndroidFileIntent(Uri uri, String type) {
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

    public static ActivityResultLauncher<Uri> takePhoto(ActivityResultCaller activityResultCaller,
                                                        ActivityResultCallback<Uri> activityResultCallback) {
        return activityResultCaller.registerForActivityResult(new ActivityResultContract<Uri, Uri>() {
            private Uri input;

            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Uri input) {
                this.input = input;
                final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, input);
                return intent;
            }

            @Override
            public Uri parseResult(int resultCode, @Nullable Intent intent) {
                if (resultCode == Activity.RESULT_OK) {
                    return input;
                } else {
                    return null;
                }
            }
        }, result -> {
            if (result != null) {
                SlcUriCompatUtils.notifyMediaScannerScanFile(Utils.getApp(), (path, uri) -> new Handler(Looper.getMainLooper()).post(() -> activityResultCallback.onActivityResult(uri)), result);
            } else {
                activityResultCallback.onActivityResult(result);
            }
        });
    }

    public static CutOutPhoto getCutOutPhoto(Uri photoUri) {
        return getCutOutPhoto(photoUri, SlcUriCompatUtils.image2UriByInsert(Utils.getApp()));
    }

    public static CutOutPhoto getCutOutPhoto(Uri photoUri, Uri outPutUri) {
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
        return getCutOutPhoto(photoUri, bundle);
    }

    public static CutOutPhoto getCutOutPhoto(Uri photoUri, Bundle bundle) {
        CutOutPhoto cutOutPhoto = new CutOutPhoto();
        cutOutPhoto.setInputUri(photoUri);
        cutOutPhoto.setBundle(bundle);
        return cutOutPhoto;
    }

    public static ActivityResultLauncher<CutOutPhoto> registerCutOutPhoto(ActivityResultCaller activityResultCaller,
                                                                          ActivityResultCallback<Uri> activityResultCallback) {
        return activityResultCaller.registerForActivityResult(new ActivityResultContract<CutOutPhoto, Uri>() {
            private Uri extraOutput;

            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, CutOutPhoto cutOutPhoto) {
                extraOutput = cutOutPhoto.getBundle().getParcelable(MediaStore.EXTRA_OUTPUT);
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.setDataAndType(cutOutPhoto.getInputUri(), context.getContentResolver().getType(cutOutPhoto.getInputUri()));
                intent.putExtras(cutOutPhoto.getBundle());
                return intent;
            }

            @Override
            public Uri parseResult(int resultCode, @Nullable Intent intent) {
                if (resultCode == Activity.RESULT_OK) {
                    return extraOutput;
                } else {
                    return null;
                }

            }
        }, result -> {
            if (result != null) {
                SlcUriCompatUtils.notifyMediaScannerScanFile(Utils.getApp(), (path, uri) -> new Handler(Looper.getMainLooper()).post(() -> activityResultCallback.onActivityResult(uri)), result);
            } else {
                activityResultCallback.onActivityResult(result);
            }
        });
    }
}
