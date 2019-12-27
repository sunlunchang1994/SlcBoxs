package android.slc.or;

import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class FileRequestBody extends RequestBody {
    public static int DEF_NOTIFY_INTERVAL = 512;
    private File mFile;
    private MediaType mMediaType;
    private long mNotifyInterval;
    private FileUploadListener mListener;
    private long mContentLength;

    @SuppressWarnings("all")
    public FileRequestBody(final File file, final FileUploadListener listener) {
        this(file, MediaType.parse(MimeTypeMap.getSingleton().getMimeTypeFromExtension(getFileExtension(file))), listener);
    }

    public FileRequestBody(final File file, MediaType mediaType, final FileUploadListener listener) {
        this(file, mediaType, DEF_NOTIFY_INTERVAL, listener);
    }

    @SuppressWarnings("all")
    public FileRequestBody(final File file, int notifyInterval, final FileUploadListener listener) {
        this(file, MediaType.parse(MimeTypeMap.getSingleton().getMimeTypeFromExtension(getFileExtension(file))), notifyInterval, listener);
    }

    public FileRequestBody(final File file, MediaType mediaType, int notifyInterval, final FileUploadListener listener) {
        mFile = file;
        mMediaType = mediaType;
        mNotifyInterval = notifyInterval < 1 ? DEF_NOTIFY_INTERVAL : notifyInterval;
        mListener = listener;
        mContentLength = mFile.length();
        /*mHandler = new Handler();
        mProgressUpdater = new ProgressUpdater(mContentLength);*/
    }

    @Override
    public MediaType contentType() {
        return mMediaType;
    }

    @Override
    public long contentLength() {
        return mContentLength;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        byte[] buffer = new byte[1024];
        FileInputStream in = new FileInputStream(mFile);
        long bytesWritten = 0;
        try {
            if (mListener != null) {
                mListener.onStart();
            }
            int read;
            long startTime = 0;
            while ((read = in.read(buffer)) != -1) {
                bytesWritten += read;
                sink.write(buffer, 0, read);
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime >= mNotifyInterval) {
                    startTime = currentTime;
                    if (mListener != null) {
                        mListener.onProgressChange((int) (bytesWritten * 100 / mContentLength), bytesWritten, mContentLength);
                    }
                }
            }
            if (mListener != null) {
                mListener.onFinish();
            }
        } finally {
            in.close();
        }
    }
    /*@Override
    public void writeTo(BufferedSink sink) throws IOException {
        byte[] buffer = new byte[1024];
        FileInputStream in = new FileInputStream(mFile);
        long bytesWritten = 0;
        try {
            int read;
            long startTime = System.currentTimeMillis();
            while ((read = in.read(buffer)) != -1) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime >= mNotifyInterval) {
                    startTime = currentTime;
                    mHandler.post(mProgressUpdater.setData((int) (bytesWritten * 100 / mContentLength), bytesWritten));
                }
                bytesWritten += read;
                sink.write(buffer, 0, read);
            }
            mHandler.post(new FinishUpdater());
        } catch (Exception e) {
            mHandler.post(new ErrorUpdater(e));
        } finally {
            in.close();
        }
    }*/

    /**
     * 获取文件后缀名
     *
     * @param file
     * @return
     */
    public static String getFileExtension(final File file) {
        if (file == null) return "";
        return getFileExtension(file.getPath());
    }

    /**
     * 获取文件后缀名
     *
     * @param filePath The path of file.
     * @return the extension of file
     */
    public static String getFileExtension(final String filePath) {
        if (isSpace(filePath)) return "";
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) return "";
        return filePath.substring(lastPoi + 1);
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

    private class ProgressUpdater implements Runnable {
        private int mProgress;
        private long mBytesWritten;
        private long mContentLength;

        public ProgressUpdater(long contentLength) {
            this.mContentLength = contentLength;
        }

        public ProgressUpdater setData(int progress, long bytesWritten) {
            mProgress = progress;
            mBytesWritten = bytesWritten;
            return this;
        }


        @Override
        public void run() {
            mListener.onProgressChange(mProgress, mBytesWritten, mContentLength);
        }
    }


    private class FinishUpdater implements Runnable {

        @Override
        public void run() {
            mListener.onFinish();
        }
    }

    public interface FileUploadListener {
        void onStart();

        void onProgressChange(int progress, long bytesWritten, long contentLength);

        void onFinish();
    }

    public static class SimpleFileUploadListener implements FileUploadListener {
        public void onStart() {
        }

        public void onProgressChange(int progress, long bytesWritten, long contentLength) {
        }


        public void onFinish() {
        }
    }
}