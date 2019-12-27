package android.slc.commonlibrary.util.compat;

import android.graphics.Bitmap;
import android.view.View;

/**
 * @author android.slc
 * @date 2019/11/22 9:43
 */
public class SlcImageCompatUtils {
    /**
     * View 转换为bitmap
     */
    public static Bitmap convertViewToBitmap(View v) {
        v.setDrawingCacheEnabled(true);
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }
}
