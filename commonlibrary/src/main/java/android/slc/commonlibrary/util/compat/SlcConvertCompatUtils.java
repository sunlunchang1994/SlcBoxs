package android.slc.commonlibrary.util.compat;


import androidx.annotation.NonNull;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

import android.slc.commonlibrary.util.SlcGsonUtils;

public class SlcConvertCompatUtils {
    /**
     * 对象转map
     *
     * @param t
     * @return
     */
    public static <T> Map<String, Object> objToMap(@NonNull T t) {
        Map<String, Object> map = SlcGsonUtils.fromJson(SlcGsonUtils.toJson(t), SlcGsonUtils.getMapType(String.class, Object.class));
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
        return SlcGsonUtils.fromJson(SlcGsonUtils.toJson(map), type);
    }
}
