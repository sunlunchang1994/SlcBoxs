package android.slc.commonlibrary.util.compat;


import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.GsonUtils;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

public class SlcConvertCompatUtils {
    /**
     * 对象转map
     *
     * @param t
     * @return
     */
    public static <T> Map<String, Object> objToMap(@NonNull T t) {
        Map<String, Object> map = GsonUtils.fromJson(GsonUtils.toJson(t), GsonUtils.getMapType(String.class, Object.class));
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //此处将小数点后为0的Double类型转成整形
            if (entry.getValue() instanceof Double) {
                double d = (Double) entry.getValue();
                long l = (long) d;
                if (l == d) {
                    entry.setValue(l);
                }
            }
        }
        return map;
    }

    /**
     * 对象转map去空
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> objToMapFilterEmpty(@NonNull T t) {
        Map<String, Object> map = objToMap(t);
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if (entry.getValue() == null) {
                iterator.remove();
            }
        }
        return map;
    }

    /**
     * map转对象
     *
     * @param map
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T mapToObj(@NonNull Map map, @NonNull Type type) {
        return GsonUtils.fromJson(GsonUtils.toJson(map), type);
    }

    /**
     * 时长转合适的时长字符串
     *
     * @param duration 时长 秒为单位
     * @return
     */
    public static String duration2FitStr(long duration) {
        StringBuilder timeStringBuilder = new StringBuilder();
        if (duration >= 60 * 60) {
            long hour = duration / 60 / 60;
            if (hour < 10) {
                timeStringBuilder.append("0");
            }
            timeStringBuilder.append(hour);
            timeStringBuilder.append(":");
        }
        long minutes = duration / 60 % 60;
        if (minutes < 10) {
            timeStringBuilder.append("0");
        }
        timeStringBuilder.append(minutes);
        timeStringBuilder.append(":");
        long remainingSeconds = duration % 60;
        if (remainingSeconds < 10) {
            timeStringBuilder.append("0");
        }
        timeStringBuilder.append(remainingSeconds);
        return timeStringBuilder.toString();
    }

    /**
     * View 转换为bitmap
     *
     * @param v
     * @return
     */
    public static Bitmap convertViewToBitmap(View v) {
        return SlcImageCompatUtils.convertViewToBitmap(v);
    }
}
