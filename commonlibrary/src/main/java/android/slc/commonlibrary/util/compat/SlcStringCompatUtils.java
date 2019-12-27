package android.slc.commonlibrary.util.compat;

import java.util.UUID;

import android.slc.commonlibrary.util.SlcStringUtils;

/**
 * 字符串操作类
 */
public class SlcStringCompatUtils {
    /**
     * 如果一个string为空，则返回“--”
     *
     * @param text
     * @return
     */
    public static String toStringOfSimple(String text) {
        return toStringOfPlaceholder(text, "--");
    }

    /**
     * 如果一个string为空，则返回展位符
     *
     * @param text
     * @return
     */
    public static String toStringOfPlaceholder(String text, String placeholder) {
        if (SlcStringUtils.isEmpty(text)) {
            return placeholder;
        }
        return text;
    }

    /**
     * 如果一个string为空，则返回展位符
     *
     * @param text
     * @return
     */
    public static String toStringOfReplace(String text, String replaceS, String replaceR) {
        if (SlcStringUtils.isEmpty(text) || text.equals(replaceS)) {
            return replaceR;
        }
        return text;
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getSimpleUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
