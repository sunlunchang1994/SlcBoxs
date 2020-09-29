package android.slc.commonlibrary.util.compat;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.UriUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.provider.BaseColumns._ID;

/**
 * @author slc
 * @date 2020-09-28 13:23
 */
public class SlcUriCompatUtils {

    public static Uri file2Uri(Context context, @NonNull final String filePath) {
        return file2Uri(context, filePath);
    }

    /**
     * File to uri.
     *
     * @param file The file.
     * @return uri
     */
    public static Uri file2Uri(Context context, @NonNull final File file) {
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {//兼容10.0及以上的写法
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL),
                    new String[]{},
                    MediaStore.Images.Media.DISPLAY_NAME + "= ?",
                    new String[]{file.getAbsolutePath()},
                    null);
            fileUri = null;
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    fileUri = ContentUris.withAppendedId(MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL), cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                }
                cursor.close();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//兼容7.0及以上的写法
            fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    public static Uri imageId2Uri(long id) {
        return id2Uri(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
    }

    public static Uri audioId2Uri(long id) {
        return id2Uri(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
    }

    public static Uri videoId2Uri(long id) {
        return id2Uri(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);
    }

    public static Uri fileId2Uri(long id) {
        return id2Uri(MediaStore.Files.getContentUri(MediaStore.VOLUME_INTERNAL), id);
    }

    public static Uri id2Uri(Uri uri, long id) {
        return ContentUris.withAppendedId(uri, id);
    }

    public static Uri image2UriByInsert(Context context) {
        return image2UriByInsert(context, Environment.DIRECTORY_DCIM, SlcFileCompatUtils.getFileNameByTime("IMG", "jpg"));
    }

    public static Uri image2UriByInsert(Context context, @NonNull final String relativePath, @NonNull final String displayName) {
        return image2UriByInsert(context, relativePath, "image/jpeg", displayName);
    }

    public static Uri image2UriByInsert(Context context, @NonNull final String relativePath, @NonNull final String mimeType, @NonNull final String displayName) {
        return image2UriByInsert(context, newBuilder().setRelativePath(relativePath).setMimeType(mimeType).setDisplayName(displayName).build());
    }

    public static Uri image2UriByInsert(Context context, @NonNull final ContentValues contentValues) {
        return file2UriByInsert(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    public static Uri file2UriByInsert(Context context, @NonNull final Uri mediaUri, @NonNull final ContentValues contentValues) {
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {//兼容10.0及以上的写法
            fileUri = context.getContentResolver().insert(mediaUri, contentValues);
        } else {
            String relativePath = contentValues.getAsString("relative_path");
            relativePath = TextUtils.isEmpty(relativePath) ? "" : relativePath + File.separator;
            String fileName = contentValues.getAsString(MediaStore.MediaColumns.DISPLAY_NAME);
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + relativePath + fileName);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//兼容7.0及以上的写法
                fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
            } else {
                fileUri = Uri.fromFile(file);
            }
        }
        return fileUri;
    }

    /**
     * 通知扫描
     *
     * @param context
     * @param filePath
     */
    public static void notifyMediaScannerScanFile(Context context,
                                                  MediaScannerConnection.OnScanCompletedListener onScanCompletedListener,
                                                  final String... filePath) {
        MediaScannerConnection.scanFile(context, filePath, null, onScanCompletedListener);
    }

    /**
     * 通知扫描
     *
     * @param context
     */
    public static void notifyMediaScannerScanFile(Context context,
                                                  MediaScannerConnection.OnScanCompletedListener onScanCompletedListener,
                                                  final Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            onScanCompletedListener.onScanCompleted(null, uri);
        } else {
            notifyMediaScannerScanFile(context, onScanCompletedListener, uri2File(uri).getAbsolutePath());
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private ContentValues contentValues;

        public Builder() {
            contentValues = new ContentValues();
        }

        public Builder setDisplayName(String displayName) {
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName);
            return this;
        }

        public Builder setMimeType(String mimeType) {
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
            return this;
        }

        public Builder setRelativePath(String relativePath) {
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath);
            return this;
        }

        public Builder put(String key, String value) {
            contentValues.put(key, value);
            return this;
        }

        public Builder putAll(ContentValues other) {
            contentValues.putAll(other);
            return this;
        }

        public Builder put(String key, Byte value) {
            contentValues.put(key, value);
            return this;
        }

        public Builder put(String key, Short value) {
            contentValues.put(key, value);
            return this;
        }

        public Builder put(String key, Integer value) {
            contentValues.put(key, value);
            return this;
        }

        public Builder put(String key, Long value) {
            contentValues.put(key, value);
            return this;
        }

        public Builder put(String key, Float value) {
            contentValues.put(key, value);
            return this;
        }

        public Builder put(String key, Double value) {
            contentValues.put(key, value);
            return this;
        }

        public Builder put(String key, Boolean value) {
            contentValues.put(key, value);
            return this;
        }

        public Builder put(String key, byte[] value) {
            contentValues.put(key, value);
            return this;
        }

        public ContentValues build() {
            return contentValues;
        }
    }

    /**
     * Uri to file.
     *
     * @param uri The uri.
     * @return file
     */
    public static File uri2File(@NonNull final Uri uri) {
        File file = uri2FileReal(uri);
        if (file != null) return file;
        return copyUri2Cache(uri);
    }

    /**
     * Uri to file.
     *
     * @param uri The uri.
     * @return file
     */
    private static File uri2FileReal(@NonNull final Uri uri) {
        Log.d("UriUtils", uri.toString());
        String authority = uri.getAuthority();
        String scheme = uri.getScheme();
        String path = uri.getPath();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && path != null) {
            String[] externals = new String[]{"/external/", "/external_path/"};
            File file = null;
            for (String external : externals) {
                if (path.startsWith(external)) {
                    return new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                            + path.replace(external, "/"));
                }
            }
            if (path.startsWith("/files_path/")) {
                file = new File(Utils.getApp().getFilesDir().getAbsolutePath()
                        + path.replace("/files_path/", "/"));
            } else if (path.startsWith("/cache_path/")) {
                file = new File(Utils.getApp().getCacheDir().getAbsolutePath()
                        + path.replace("/cache_path/", "/"));
            } else if (path.startsWith("/external_files_path/")) {
                file = new File(Utils.getApp().getExternalFilesDir(null).getAbsolutePath()
                        + path.replace("/external_files_path/", "/"));
            } else if (path.startsWith("/external_cache_path/")) {
                file = new File(Utils.getApp().getExternalCacheDir().getAbsolutePath()
                        + path.replace("/external_cache_path/", "/"));
            }
            if (file != null && file.exists()) {
                Log.d("UriUtils", uri.toString() + " -> " + path);
                return file;
            }
        }
        if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            if (path != null) return new File(path);
            Log.d("UriUtils", uri.toString() + " parse failed. -> 0");
            return null;
        }// end 0
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && DocumentsContract.isDocumentUri(Utils.getApp(), uri)) {
            if ("com.android.externalstorage.documents".equals(authority)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return new File(Environment.getExternalStorageDirectory() + "/" + split[1]);
                } else {
                    // Below logic is how External Storage provider build URI for documents
                    // http://stackoverflow.com/questions/28605278/android-5-sd-card-label
                    StorageManager mStorageManager = (StorageManager) Utils.getApp().getSystemService(Context.STORAGE_SERVICE);
                    try {
                        Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
                        Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
                        Method getUuid = storageVolumeClazz.getMethod("getUuid");
                        Method getState = storageVolumeClazz.getMethod("getState");
                        Method getPath = storageVolumeClazz.getMethod("getPath");
                        Method isPrimary = storageVolumeClazz.getMethod("isPrimary");
                        Method isEmulated = storageVolumeClazz.getMethod("isEmulated");

                        Object result = getVolumeList.invoke(mStorageManager);

                        final int length = Array.getLength(result);
                        for (int i = 0; i < length; i++) {
                            Object storageVolumeElement = Array.get(result, i);
                            //String uuid = (String) getUuid.invoke(storageVolumeElement);

                            final boolean mounted = Environment.MEDIA_MOUNTED.equals(getState.invoke(storageVolumeElement))
                                    || Environment.MEDIA_MOUNTED_READ_ONLY.equals(getState.invoke(storageVolumeElement));

                            //if the media is not mounted, we need not get the volume details
                            if (!mounted) continue;

                            //Primary storage is already handled.
                            if ((Boolean) isPrimary.invoke(storageVolumeElement)
                                    && (Boolean) isEmulated.invoke(storageVolumeElement)) {
                                continue;
                            }

                            String uuid = (String) getUuid.invoke(storageVolumeElement);

                            if (uuid != null && uuid.equals(type)) {
                                return new File(getPath.invoke(storageVolumeElement) + "/" + split[1]);
                            }
                        }
                    } catch (Exception ex) {
                        Log.d("UriUtils", uri.toString() + " parse failed. " + ex.toString() + " -> 1_0");
                    }
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_0");
                return null;
            }// end 1_0
            else if ("com.android.providers.downloads.documents".equals(authority)) {
                final String id = DocumentsContract.getDocumentId(uri);
                if (TextUtils.isEmpty(id)) {
                    Log.d("UriUtils", uri.toString() + " parse failed(id is null). -> 1_1");
                    return null;
                }
                if (id.startsWith("raw:")) {
                    return new File(id.substring(4));
                }

                String[] contentUriPrefixesToTry = new String[]{
                        "content://downloads/public_downloads",
                        "content://downloads/all_downloads",
                        "content://downloads/my_downloads"
                };

                for (String contentUriPrefix : contentUriPrefixesToTry) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse(contentUriPrefix), Long.valueOf(id));
                    try {
                        File file = getFileFromUri(contentUri, "1_1");
                        if (file != null) {
                            return file;
                        }
                    } catch (Exception ignore) {
                    }
                }

                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_1");
                return null;
            }// end 1_1
            else if ("com.android.providers.media.documents".equals(authority)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else {
                    Log.d("UriUtils", uri.toString() + " parse failed. -> 1_2");
                    return null;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getFileFromUri(contentUri, selection, selectionArgs, "1_2");
            }// end 1_2
            else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
                return getFileFromUri(uri, "1_3");
            }// end 1_3
            else {
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_4");
                return null;
            }// end 1_4
        }// end 1
        else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            return getFileFromUri(uri, "2");
        }// end 2
        else {
            Log.d("UriUtils", uri.toString() + " parse failed. -> 3");
            return null;
        }// end 3
    }

    private static File getFileFromUri(final Uri uri, final String code) {
        return getFileFromUri(uri, null, null, code);
    }

    private static File getFileFromUri(final Uri uri,
                                       final String selection,
                                       final String[] selectionArgs,
                                       final String code) {
        if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
            if (!TextUtils.isEmpty(uri.getLastPathSegment())) {
                return new File(uri.getLastPathSegment());
            }
        } else if ("com.tencent.mtt.fileprovider".equals(uri.getAuthority())) {
            String path = uri.getPath();
            if (!TextUtils.isEmpty(path)) {
                File fileDir = Environment.getExternalStorageDirectory();
                return new File(fileDir, path.substring("/QQBrowser".length(), path.length()));
            }
        } else if ("com.huawei.hidisk.fileprovider".equals(uri.getAuthority())) {
            String path = uri.getPath();
            if (!TextUtils.isEmpty(path)) {
                return new File(path.replace("/root", ""));
            }
        }

        final Cursor cursor = Utils.getApp().getContentResolver().query(
                uri, new String[]{"_data"}, selection, selectionArgs, null);
        if (cursor == null) {
            Log.d("UriUtils", uri.toString() + " parse failed(cursor is null). -> " + code);
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                final int columnIndex = cursor.getColumnIndex("_data");
                if (columnIndex > -1) {
                    return new File(cursor.getString(columnIndex));
                } else {
                    Log.d("UriUtils", uri.toString() + " parse failed(columnIndex: " + columnIndex + " is wrong). -> " + code);
                    return null;
                }
            } else {
                Log.d("UriUtils", uri.toString() + " parse failed(moveToFirst return false). -> " + code);
                return null;
            }
        } catch (Exception e) {
            Log.d("UriUtils", uri.toString() + " parse failed. -> " + code);
            return null;
        } finally {
            cursor.close();
        }
    }

    private static File copyUri2Cache(Uri uri) {
        Log.d("UriUtils", "copyUri2Cache() called");
        InputStream is = null;
        try {
            is = Utils.getApp().getContentResolver().openInputStream(uri);
            File file = new File(Utils.getApp().getCacheDir(), "" + System.currentTimeMillis());
            FileIOUtils.writeFileFromIS(file.getAbsolutePath(), is);
            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
