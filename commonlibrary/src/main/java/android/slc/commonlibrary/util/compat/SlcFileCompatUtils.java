package android.slc.commonlibrary.util.compat;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/05/03
 *     desc  : utils about file
 * </pre>
 */
public final class SlcFileCompatUtils {

    /**
     * 获取存储空间总容量
     *
     * @param filePath
     * @return
     */
    public static long storageToal(File filePath) {
        StatFs stat = new StatFs(filePath.getPath()); // 创建StatFs对象
        long blockSize = stat.getBlockSizeLong(); // 获取block的size
        long totalBlocks = stat.getBlockCountLong(); // 获取block的总数
        return blockSize * totalBlocks;
    }


    /**
     * 获取存储空间使用量
     *
     * @param filePath
     * @return
     */
    public static long storageUse(File filePath) {
        StatFs stat = new StatFs(filePath.getPath()); // 创建StatFs对象
        long availableBlocks = stat.getAvailableBlocksLong(); // 获取可用块大小
        long blockSize = stat.getBlockSizeLong(); // 获取block的size
        long totalBlocks = stat.getBlockCountLong(); // 获取block的总数
        return ((totalBlocks - availableBlocks) * blockSize);

    }

    //TODO 后加
    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";

    private static String getTimeFormatName(String timeFormatHeader) {
        final Date date = new Date(System.currentTimeMillis());
        //必须要加上单引号
        final SimpleDateFormat dateFormat = new SimpleDateFormat("'" + timeFormatHeader + "'" + TIME_FORMAT,
                Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * @param timeFormatHeader 格式化的头(除去时间部分)
     * @param extension        后缀名
     * @return 返回时间格式化后的文件名
     */
    public static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader) + "." + extension;
    }

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            final Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    final int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public static String byte2FitMemorySize(final long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < 1024) {
            return String.format(Locale.getDefault(), "%.3fB", (double) byteNum);
        } else if (byteNum < 1048576) {
            return String.format(Locale.getDefault(), "%.3fKB", (double) byteNum / 1024);
        } else if (byteNum < 1073741824) {
            return String.format(Locale.getDefault(), "%.3fMB", (double) byteNum / 1048576);
        } else {
            return String.format(Locale.getDefault(), "%.3fGB", (double) byteNum / 1073741824);
        }
    }

    private static final String[][] MIME_MAP_TABLE = {
            {".3gp", "video/3gpp"}, {".apk", "application/vnd.android.package-archive"}, {".asf", "video/x-ms-asf"}, {".avi",
            "video/x-msvideo"}, {".bin", "application/octet-stream"}, {".bmp", "image/bmp"}, {".c", "text/plain"}, {".class",
            "application/octet-stream"}, {".conf", "text/plain"}, {".cpp", "text/plain"}, {".doc", "application/msword"}, {
            ".docx", "application/msword"}, {".exe", "application/octet-stream"}, {".gif", "image/gif"}, {".gtar",
            "application/x-gtar"}, {".gz", "application/x-gzip"}, {".h", "text/plain"}, {".htm", "text/html"}, {".html",
            "text/html"}, {".jar", "application/java-archive"}, {".java", "text/plain"}, {".jpeg", "image/jpeg"}, {".JPEG",
            "image/jpeg"}, {".jpg", "image/jpeg"}, {".js", "application/x-javascript"}, {".log", "text/plain"}, {".m3u",
            "audio/x-mpegurl"}, {".m4a", "audio/mp4a-latm"}, {".m4b", "audio/mp4a-latm"}, {".m4p", "audio/mp4a-latm"}, {".m4u"
            , "video/vnd.mpegurl"}, {".m4v", "video/x-m4v"}, {".mov", "video/quicktime"}, {".mp2", "audio/x-mpeg"}, {".mp3",
            "audio/x-mpeg"}, {".mp4", "video/mp4"}, {".mpc", "application/vnd.mpohun.certificate"}, {".mpe", "video/mpeg"}, {
            ".mpeg", "video/mpeg"}, {".mpg", "video/mpeg"}, {".mpg4", "video/mp4"}, {".mpga", "audio/mpeg"}, {".msg",
            "application/vnd.ms-outlook"}, {".ogg", "audio/ogg"}, {".pdf", "application/pdf"}, {".png", "image/png"}, {".pps"
            , "application/vnd.ms-powerpoint"}, {".ppt", "application/vnd.ms-powerpoint"}, {".pptx", "application/vnd" +
            ".ms-powerpoint"}, {".prop", "text/plain"}, {".rar", "application/x-rar-compressed"}, {".rc", "text/plain"}, {
            ".rmvb", "audio/x-pn-realaudio"}, {".rtf", "application/rtf"}, {".sh", "text/plain"}, {".tar", "application/x" +
            "-tar"}, {".tgz", "application/x-compressed"}, {".txt", "text/plain"}, {".wav", "audio/x-wav"}, {".wma", "audio/x" +
            "-ms-wma"}, {".wmv", "audio/x-ms-wmv"}, {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"}, {".z", "application/x-compress"}, {".zip", "application/zip"}, {"", "*/*"}};

    public static String getMimeType(@NonNull File file) {
        //String type = "*/*";
        /*String fName = file.getName();
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0)
            return type;
        String fileType = fName.substring(dotIndex).toLowerCase();
        if ("".equals(fileType)) return type;
        for (int i = 0; i < MIME_MAP_TABLE.length; i++) {
            if (fileType.equals(MIME_MAP_TABLE[i][0])) type = MIME_MAP_TABLE[i][1];
        }
        return type;*/
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(FileUtils.getFileExtension(file));
    }

    public static String getMimeType(@NonNull String path) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(FileUtils.getFileExtension(path));
    }

    /**
     * 更具一个未知路径获取文件名
     *
     * @param path
     * @return
     */
    public static String getFileNameByUnknownPath(String path) {
        if (TextUtils.isEmpty(path)) {
            return getTimeFormatName("SLC_");
        }
        int lastSetIndex = path.lastIndexOf(File.separator);
        if (lastSetIndex == -1) {
            return path;
        }
        return path.substring(lastSetIndex + 1);
    }
}
