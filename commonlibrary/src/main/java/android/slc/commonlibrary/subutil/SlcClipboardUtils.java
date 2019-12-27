package android.slc.commonlibrary.subutil;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.slc.commonlibrary.util.SlcUtils;

/**
 * 剪切板
 */
public final class SlcClipboardUtils {

    private SlcClipboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 复制文本到剪贴板
     *
     * @param text 文本
     */
    public static void copyText(final CharSequence text) {
        ClipboardManager cm = (ClipboardManager) SlcUtils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.setPrimaryClip(ClipData.newPlainText("text", text));
    }

    /**
     * 获取剪贴板的文本
     *
     * @return 剪贴板的文本
     */
    public static CharSequence getText() {
        ClipboardManager cm = (ClipboardManager) SlcUtils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        ClipData clip = cm.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).coerceToText(SlcUtils.getApp());
        }
        return null;
    }

    /**
     * 复制uri到剪贴板
     *
     * @param uri uri
     */
    public static void copyUri(final Uri uri) {
        ClipboardManager cm = (ClipboardManager) SlcUtils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.setPrimaryClip(ClipData.newUri(SlcUtils.getApp().getContentResolver(), "uri", uri));
    }

    /**
     * 获取剪贴板的uri
     *
     * @return 剪贴板的uri
     */
    public static Uri getUri() {
        ClipboardManager cm = (ClipboardManager) SlcUtils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        ClipData clip = cm.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getUri();
        }
        return null;
    }

    /**
     * 复制意图到剪贴板
     *
     * @param intent 意图
     */
    public static void copyIntent(final Intent intent) {
        ClipboardManager cm = (ClipboardManager) SlcUtils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.setPrimaryClip(ClipData.newIntent("intent", intent));
    }

    /**
     * 获取剪贴板的意图
     *
     * @return 剪贴板的意图
     */
    public static Intent getIntent() {
        ClipboardManager cm = (ClipboardManager) SlcUtils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        ClipData clip = cm.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getIntent();
        }
        return null;
    }
}