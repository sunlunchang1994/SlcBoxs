package android.slc.or;

import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;
import androidx.collection.SimpleArrayMap;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SlcParam {
    public SlcParam() {

    }

    /**
     * 创建一个FileRequestBody
     *
     * @param file
     * @return
     */
    public static RequestBody createFReq(final File file) {
        return createFReq(file, null);
    }

    /**
     * 创建一个FileRequestBody
     *
     * @param file
     * @param listener
     * @return
     */
    @SuppressWarnings("all")
    public static RequestBody createFReq(final File file, final FileRequestBody.FileUploadListener listener) {
        return createFReq(file, MediaType.parse(MimeTypeMap.getSingleton().getMimeTypeFromExtension(FileRequestBody.getFileExtension(file))), listener);
    }

    /**
     * 创建一个FileRequestBody
     *
     * @param file
     * @param mediaType
     * @param listener
     * @return
     */
    public static RequestBody createFReq(final File file, MediaType mediaType, final FileRequestBody.FileUploadListener listener) {
        return createFReq(file, mediaType, FileRequestBody.DEF_NOTIFY_INTERVAL, listener);
    }

    /**
     * 创建一个FileRequestBody
     *
     * @param file
     * @param motifyInterval
     * @param listener
     * @return
     */
    @SuppressWarnings("all")
    public static RequestBody createFReq(final File file, int notifyInterval, final FileRequestBody.FileUploadListener listener) {
        return createFReq(file, MediaType.parse(MimeTypeMap.getSingleton().getMimeTypeFromExtension(FileRequestBody.getFileExtension(file))), notifyInterval, listener);
    }

    /**
     * 创建一个FileRequestBody
     *
     * @param file
     * @param mediaType
     * @param notifyInterval
     * @param listener
     * @return
     */
    public static RequestBody createFReq(final File file, MediaType mediaType, int notifyInterval, final FileRequestBody.FileUploadListener listener) {
        return new FileRequestBody(file, mediaType, notifyInterval, listener);
    }

    /**
     * 创建一个MultipartBody.Part
     *
     * @param requestBody
     * @return
     */
    public static MultipartBody.Part createPart(RequestBody requestBody) {
        return MultipartBody.Part.create(requestBody);
    }

    /**
     * 创建一个MultipartBody.Part
     *
     * @param headers
     * @param requestBody
     * @return
     */
    public static MultipartBody.Part createPart(Headers headers, RequestBody requestBody) {
        return MultipartBody.Part.create(headers, requestBody);
    }

    /**
     * 创建一个MultipartBody.Part
     *
     * @param name
     * @param value
     * @return
     */
    public static MultipartBody.Part createPart(String name, String value) {
        return MultipartBody.Part.createFormData(name, value);
    }

    /**
     * 创建一个MultipartBody.Part
     *
     * @param name
     * @param fileName
     * @param requestBody
     * @return
     */
    public static MultipartBody.Part createPart(String name, String fileName, RequestBody requestBody) {
        return MultipartBody.Part.createFormData(name, fileName, requestBody);
    }

    /**
     * 创建一个MultipartBody.Part
     *
     * @param key
     * @param file
     * @return
     */
    public static MultipartBody.Part createPart(final String key, final File file) {
        return createPart(key, file.getName(), createFReq(file));
    }

    /**
     * 创建一个MultipartBody.Part
     *
     * @param key
     * @param file
     * @param listener
     * @return
     */
    public static MultipartBody.Part createPart(final String key, final File file, final FileRequestBody.FileUploadListener listener) {
        return createPart(key, file.getName(), createFReq(file, listener));
    }

    /**
     * 创建一个MultipartBody.Part
     *
     * @param key
     * @param file
     * @param mediaType
     * @param listener
     * @return
     */
    public static MultipartBody.Part createPart(final String key, final File file, MediaType mediaType, final FileRequestBody.FileUploadListener listener) {
        return createPart(key, file.getName(), createFReq(file, mediaType, listener));
    }

    /**
     * 创建一个MultipartBody.Part
     *
     * @param key
     * @param file
     * @param notifyInterval
     * @param listener
     * @return
     */
    public static MultipartBody.Part createPart(final String key, final File file, int notifyInterval, final FileRequestBody.FileUploadListener listener) {
        return createPart(key, file.getName(), createFReq(file, notifyInterval, listener));
    }

    /**
     * 创建一个MultipartBody.Part
     *
     * @param key
     * @param file
     * @param mediaType
     * @param notifyInterval
     * @param listener
     * @return
     */
    public static MultipartBody.Part createPart(final String key, final File file, MediaType mediaType, int notifyInterval, final FileRequestBody.FileUploadListener listener) {
        return createPart(key, file.getName(), createFReq(file, mediaType, notifyInterval, listener));
    }

    /**
     * 创建一个MultipartBody.Builder
     *
     * @return
     */
    public static MultipartBody.Builder createMultBodyBuilder() {
        return new MultipartBody.Builder();
    }

    /**
     * 获的一个新的构建工具
     *
     * @return
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Map<String, Object> paramMap;
        private List<FileBox> fileBoxList;
        private List<MultipartBody.Part> partList;

        public Builder() {
            paramMap = new NonEmptyHasMap<>();
        }

        public Builder put(@NonNull String key, Short value) {
            paramMap.put(key, value);
            return this;
        }

        public Builder put(@NonNull String key, Integer value) {
            paramMap.put(key, value);
            return this;
        }

        public Builder put(@NonNull String key, Long value) {
            paramMap.put(key, value);
            return this;
        }

        public Builder put(@NonNull String key, Float value) {
            paramMap.put(key, value);
            return this;
        }

        public Builder put(@NonNull String key, Double value) {
            paramMap.put(key, value);
            return this;
        }

        public Builder put(@NonNull String key, Boolean value) {
            paramMap.put(key, value);
            return this;
        }

        public Builder put(@NonNull String key, String value) {
            paramMap.put(key, value);
            return this;
        }

        public Builder remove(@NonNull String key) {
            paramMap.remove(key);
            return this;
        }

        public Builder addPart(@NonNull MultipartBody.Part part) {
            ensurePartList();
            partList.add(part);
            return this;
        }

        public Builder remove(@NonNull MultipartBody.Part part) {
            ensurePartList();
            partList.remove(part);
            return this;
        }

        /**
         * 添加一个文件
         *
         * @param key
         * @param filePath
         * @return
         */
        public Builder putFile(@NonNull String key, @NonNull String filePath) {
            return putFile(key, new File(filePath));
        }

        /**
         * 添加一个文件
         *
         * @param key
         * @param file
         * @return
         */
        public Builder putFile(@NonNull String key, @NonNull File file) {
            ensureFileMap();
            fileBoxList.add(new FileBox(key, file));
            return this;
        }

        /**
         * 添加多个文件
         *
         * @param key
         * @param files
         * @return
         */
        public Builder putFiles(@NonNull String key, @NonNull File... files) {
            return putFiles(key, Arrays.asList(files));
        }

        /**
         * 添加多个文件
         *
         * @param key
         * @param fileList
         * @return
         */
        public Builder putFiles(@NonNull String key, @NonNull List<File> fileList) {
            for (File file : fileList) {
                putFile(key, file);
            }
            return this;
        }

        /**
         * 移除某个key文件
         *
         * @param key
         * @return
         */
        public Builder removeFileByKey(@NonNull String key) {
            Iterator<FileBox> iterator = fileBoxList.iterator();
            while (iterator.hasNext()) {
                FileBox fileBox = iterator.next();
                if (fileBox.getKey().equals(key)) {
                    iterator.remove();
                }
            }
            return this;
        }

        /**
         * 移除移除一个文件
         *
         * @param filePath
         * @return
         */
        public Builder removeFileByValue(@NonNull String filePath) {
            return removeFileByValue(new File(filePath));
        }

        /**
         * 移除一个文件
         *
         * @param file
         * @return
         */
        public Builder removeFileByValue(@NonNull File file) {
            Iterator<FileBox> iterator = fileBoxList.iterator();
            while (iterator.hasNext()) {
                FileBox fileBox = iterator.next();
                if (fileBox.getFile().getAbsolutePath().equals(file.getAbsolutePath())) {
                    iterator.remove();
                }
            }
            return this;
        }

        //TODO current
        private SimpleArrayMap<String, File> getFileBox(@NonNull String key, File file) {
            SimpleArrayMap<String, File> simpleArrayMap = new SimpleArrayMap<>();
            simpleArrayMap.put(key, file);
            return simpleArrayMap;
        }

        private SimpleArrayMap<String, File> getFileBox(@NonNull String key, String filePath) {
            return getFileBox(key, new File(filePath));
        }

        private void ensurePartList() {
            if (partList == null) {
                partList = new ArrayList<>();
            }
        }

        private void ensureFileMap() {
            if (fileBoxList == null) {
                fileBoxList = new ArrayList<>();
            }
        }

        /**
         * 将参数转换成Map
         * 注意，此方法只包含以下数据类型
         * short
         * int
         * long
         * float
         * double
         * boolean
         * String
         * 其他数据类型不包含在此
         *
         * @return
         */
        public Map<String, Object> build() {
            return paramMap;
        }

        /**
         * 将参数转换成FormBody
         * 注意，此方法只包含以下数据类型
         * short
         * int
         * long
         * float
         * double
         * boolean
         * String
         * 其他数据类型不包含在此
         *
         * @return
         */
        public FormBody toFormBody() {
            return toFormBody(false);
        }

        /**
         * 将参数转换成FormBody
         * 注意，此方法只包含以下数据类型
         * short
         * int
         * long
         * float
         * double
         * boolean
         * String
         * 其他数据类型不包含在此
         * 参数isEncoded为true时，这种方式可能不是很规范，该方法只是为了快捷创建上传参数，
         * 若要使用最标准的方式，请自行创建FormBody
         *
         * @param isEncoded 是否使用Encoded编码
         * @return
         */
        public FormBody toFormBody(boolean isEncoded) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                if (isEncoded) {
                    builder.addEncoded(entry.getKey(), entry.getValue().toString());
                } else {
                    builder.add(entry.getKey(), entry.getValue().toString());
                }
            }
            return builder.build();
        }

        /**
         * 转换成MultipartBody
         * 包含所有添加进来的数据
         *
         * @return
         */
        public MultipartBody toMultipartBody() {
            MultipartBody.Builder multipartBodyBuilder = createMultBodyBuilder();
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                multipartBodyBuilder.addFormDataPart(entry.getKey(), entry.getValue().toString());
            }
            ensurePartList();
            for (MultipartBody.Part part : partList) {
                multipartBodyBuilder.addPart(part);
            }
            ensureFileMap();
            for (FileBox fileBox : fileBoxList) {
                multipartBodyBuilder.addPart(createPart(fileBox.getKey(), fileBox.getFile()));
            }
            return multipartBodyBuilder.build();
        }

    }

    /**
     * 文件包装盒
     */
    private static class FileBox {
        private String key;
        private File file;

        public FileBox() {

        }

        public FileBox(String key, File file) {
            this.key = key;
            this.file = file;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }
    }
}
