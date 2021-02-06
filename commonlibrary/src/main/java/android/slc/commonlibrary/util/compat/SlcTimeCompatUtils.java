package android.slc.commonlibrary.util.compat;

import com.blankj.utilcode.util.ArrayUtils;

import java.util.Calendar;

/**
 * 时间工具
 * 包括时间格式化等等工具
 */
public class SlcTimeCompatUtils {
    /**
     * 清除日历
     * @param calendar
     * @param field
     */
    public static void clearCalendarField(Calendar calendar, int... field) {
        if (calendar != null) {
            ArrayUtils.forAllDo(field, (ArrayUtils.Closure<Integer>) (index, item) -> calendar.set(item, 0));
        }
    }

}
