package android.slc.commonlibrary.util.compat;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.material.animation.ArgbEvaluatorCompat;

import java.util.ArrayList;
import java.util.List;

import android.slc.commonlibrary.R;

public class SlcColorCompatUtils {
    public static final int[] COLOR_ARRAY_RGB = new int[]{0xFFFF0000, 0xFFFF00FF,
            0xFF0000FF, 0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00/*,0xFFFF0000*/};
    public static final int[] COLOR_ARRAY_MD = new int[]{0xfff44336, 0xffE91E63, 0xff9C27B0, 0xff673AB7, 0xff3F51B5,
            0xff2196F3, 0xff03A9F4, 0xff00BCD4, 0xff009688, 0xff4CAF50, 0xff8BC34A, 0xffCDDC39, 0xffFFEB3B, 0xffFFC107,
            0xffFF9800, 0xffFF5722};
    public static final int[] COLOR_ARRAY_MD_EXPAND = new int[]{0xfff44336, 0xffE91E63, 0xff9C27B0, 0xff673AB7, 0xff3F51B5,
            0xff2196F3, 0xff03A9F4, 0xff00BCD4, 0xff009688, 0xff4CAF50, 0xff8BC34A, 0xffCDDC39, 0xffFFEB3B, 0xffFFC107,
            0xffFF9800, 0xffFF5722, 0xff795548,/*0xff9E9E9E,*/0xff607D8B};

    private SlcColorCompatUtils() {
    }

    /**
     * 获取主色
     *
     * @param context
     * @return
     */
    public static int getColorPrimary(@NonNull Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.colorPrimary, tv, true)) {
            return tv.data;
        }
        return Color.BLACK;
    }

    /**
     * 获取强调色
     *
     * @param context
     * @return
     */
    public static int getColorAccent(@NonNull Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.colorAccent, tv, true)) {
            return tv.data;
        }
        return Color.BLACK;
    }

    /**
     * @param id
     * @return
     */
    public static int getColor(@NonNull Context context, @ColorRes int id) {
        return ContextCompat.getColor(context, id);
    }

    /**
     * 获取平均颜色值
     *
     * @param count 个数
     * @return
     */
    public static int[] getColorByAverage(int count, int... colorArray) {
        if (count <= 2) {
            throw new IllegalStateException("count must > 2");
        }
        int[] resultColor = new int[count];
        if (colorArray == null || colorArray.length == 0) {
            colorArray = COLOR_ARRAY_RGB;
        }
        int colorArrayLength = colorArray.length;
        if (colorArrayLength == 1) {
            throw new IllegalStateException("colorArray must > 1");
        }
        if (colorArrayLength == count) {
            resultColor = colorArray;
            return resultColor;
        }
        List<Integer> colorList = new ArrayList<>();
        ArgbEvaluatorCompat argbEvaluatorCompat = new ArgbEvaluatorCompat();
        for (int i = 0; i < colorArrayLength - 1; i++) {
            int startColor = colorArray[i];
            int endColor = colorArray[i + 1];
            if (i == 0) {
                colorList.add(argbEvaluatorCompat.evaluate(0, startColor, endColor));
            }
            for (int j = 0; j < count; j++) {
                float temp = (float) j / (count - 1);
                if (j > 0) {
                    colorList.add(argbEvaluatorCompat.evaluate(temp, startColor, endColor));
                }
            }
        }
        int colorListSize = colorList.size();
        for (int i = 0, j = 0; i < colorListSize; i++) {
            if (i % (colorArrayLength - 1) == 0) {
                resultColor[j++] = colorList.get(i);
            }
        }
        return resultColor;
    }

    public static String int2Hex(int colorInt) {
        String hexCode = "";
        hexCode = String.format("#%06X", 16777215 & colorInt);
        return hexCode;
    }
}
