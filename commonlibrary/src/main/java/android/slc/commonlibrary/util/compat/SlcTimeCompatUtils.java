package android.slc.commonlibrary.util.compat;

import android.text.TextUtils;

import androidx.collection.SimpleArrayMap;

import com.blankj.utilcode.util.ArrayUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具
 * 包括时间格式化等等工具
 */
public class SlcTimeCompatUtils {
    private static SimpleArrayMap<String, DateFormat> dateFormatMap = new SimpleArrayMap<>();
    //private static SlcCacheMemoryUtils slcCacheMemoryUtils = SlcCacheMemoryUtils.getInstance("dateFormat", 512);

    /**
     * 将时间字符串转换成指定的格式的时间字符串
     *
     * @param pattern 时间格式
     * @param date    时间字符串
     * @return 返回指定的时间字符串
     */
    public static synchronized String getDateStringByPattern(String pattern, String date) {
        return getDateStringByPattern(pattern, date, "data error");
    }

    /**
     * 将时间字符串转换成指定的格式的时间字符串，如果转换出现异常则返回传入的默认字符串
     *
     * @param pattern 时间格式
     * @param date    时间字符串
     * @param defDate 默认的时间字符串
     * @return 返回指定的之间字符串
     */
    public static synchronized String getDateStringByPattern(String pattern, String date, String defDate) {
        if (TextUtils.isEmpty(date)) {
            return defDate;
        }
        return getDateStringByPattern(pattern, getDateByPattern(pattern, date), defDate);
    }

    /**
     * 将时间转换成指定格式的时间字符串
     *
     * @param pattern 时间格式
     * @param date    时间
     * @return 返回指定的时间字符串
     */
    public static synchronized String getDateStringByPattern(String pattern, Date date) {
        return getDateStringByPattern(pattern, date, "data error");
    }

    /**
     * 将时间转换成指定格式的时间字符串
     *
     * @param pattern 时间格式
     * @param date    时间
     * @param defDate 默认返回的数据
     * @return 返回指定的时间字符串
     */
    public static synchronized String getDateStringByPattern(String pattern, Date date, String defDate) {
        DateFormat dateFormat = getDateFormatByPattern(pattern);
        if (dateFormat == null) {
            return defDate;
        }
        return dateFormat.format(date);
    }

    public static synchronized Date getDateByPattern(String pattern, String date) {
        return getDateByPattern(pattern, date, new Date());
    }

    public static void clearCalendarField(Calendar calendar, int... field) {
        if (calendar != null) {
            ArrayUtils.forAllDo(field, (ArrayUtils.Closure<Integer>) (index, item) -> calendar.set(item, 0));
        }
    }

    public static synchronized Date getDateByPattern(String pattern, String date, Date defDate) {
        if (TextUtils.isEmpty(date)) {
            return defDate;
        }
        DateFormat dateFormat = getDateFormatByPattern(pattern);
        if (dateFormat == null) {
            return defDate;
        }
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return defDate;
        }
    }

    private static synchronized DateFormat getDateFormatByPattern(String pattern) {
        if (TextUtils.isEmpty(pattern)) {
            return null;
        }
        DateFormat dateFormat = dateFormatMap.get(pattern);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
            dateFormatMap.put(pattern, dateFormat);
        }
        return dateFormat;
    }
}
