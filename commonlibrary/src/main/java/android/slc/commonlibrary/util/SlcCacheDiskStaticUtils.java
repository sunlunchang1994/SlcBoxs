package android.slc.commonlibrary.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2019/01/04
 *     desc  : utils about disk cache
 * </pre>
 */
public final class SlcCacheDiskStaticUtils {

    private static SlcCacheDiskUtils sDefaultSlcCacheDiskUtils;

    /**
     * Set the default instance of {@link SlcCacheDiskUtils}.
     *
     * @param SlcCacheDiskUtils The default instance of {@link SlcCacheDiskUtils}.
     */
    public static void setDefaultSlcCacheDiskUtils(final SlcCacheDiskUtils SlcCacheDiskUtils) {
        sDefaultSlcCacheDiskUtils = SlcCacheDiskUtils;
    }

    /**
     * Put bytes in cache.
     *
     * @param key   The key of cache.
     * @param value The value of cache.
     */
    public static void put(@NonNull final String key, final byte[] value) {
        put(key, value, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Put bytes in cache.
     *
     * @param key      The key of cache.
     * @param value    The value of cache.
     * @param saveTime The save time of cache, in seconds.
     */
    public static void put(@NonNull final String key, final byte[] value, final int saveTime) {
        put(key, value, saveTime, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the bytes in cache.
     *
     * @param key The key of cache.
     * @return the bytes if cache exists or null otherwise
     */
    public static byte[] getBytes(@NonNull final String key) {
        return getBytes(key, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the bytes in cache.
     *
     * @param key          The key of cache.
     * @param defaultValue The default value if the cache doesn't exist.
     * @return the bytes if cache exists or defaultValue otherwise
     */
    public static byte[] getBytes(@NonNull final String key, final byte[] defaultValue) {
        return getBytes(key, defaultValue, getDefaultSlcCacheDiskUtils());
    }

    ///////////////////////////////////////////////////////////////////////////
    // about String
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put string value in cache.
     *
     * @param key   The key of cache.
     * @param value The value of cache.
     */
    public static void put(@NonNull final String key, final String value) {
        put(key, value, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Put string value in cache.
     *
     * @param key      The key of cache.
     * @param value    The value of cache.
     * @param saveTime The save time of cache, in seconds.
     */
    public static void put(@NonNull final String key, final String value, final int saveTime) {
        put(key, value, saveTime, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the string value in cache.
     *
     * @param key The key of cache.
     * @return the string value if cache exists or null otherwise
     */
    public static String getString(@NonNull final String key) {
        return getString(key, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the string value in cache.
     *
     * @param key          The key of cache.
     * @param defaultValue The default value if the cache doesn't exist.
     * @return the string value if cache exists or defaultValue otherwise
     */
    public static String getString(@NonNull final String key, final String defaultValue) {
        return getString(key, defaultValue, getDefaultSlcCacheDiskUtils());
    }

    ///////////////////////////////////////////////////////////////////////////
    // about JSONObject
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put JSONObject in cache.
     *
     * @param key   The key of cache.
     * @param value The value of cache.
     */
    public static void put(@NonNull final String key, final JSONObject value) {
        put(key, value, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Put JSONObject in cache.
     *
     * @param key      The key of cache.
     * @param value    The value of cache.
     * @param saveTime The save time of cache, in seconds.
     */
    public static void put(@NonNull final String key,
                           final JSONObject value,
                           final int saveTime) {
        put(key, value, saveTime, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the JSONObject in cache.
     *
     * @param key The key of cache.
     * @return the JSONObject if cache exists or null otherwise
     */
    public static JSONObject getJSONObject(@NonNull final String key) {
        return getJSONObject(key, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the JSONObject in cache.
     *
     * @param key          The key of cache.
     * @param defaultValue The default value if the cache doesn't exist.
     * @return the JSONObject if cache exists or defaultValue otherwise
     */
    public static JSONObject getJSONObject(@NonNull final String key, final JSONObject defaultValue) {
        return getJSONObject(key, defaultValue, getDefaultSlcCacheDiskUtils());
    }


    ///////////////////////////////////////////////////////////////////////////
    // about JSONArray
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put JSONArray in cache.
     *
     * @param key   The key of cache.
     * @param value The value of cache.
     */
    public static void put(@NonNull final String key, final JSONArray value) {
        put(key, value, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Put JSONArray in cache.
     *
     * @param key      The key of cache.
     * @param value    The value of cache.
     * @param saveTime The save time of cache, in seconds.
     */
    public static void put(@NonNull final String key, final JSONArray value, final int saveTime) {
        put(key, value, saveTime, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the JSONArray in cache.
     *
     * @param key The key of cache.
     * @return the JSONArray if cache exists or null otherwise
     */
    public static JSONArray getJSONArray(@NonNull final String key) {
        return getJSONArray(key, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the JSONArray in cache.
     *
     * @param key          The key of cache.
     * @param defaultValue The default value if the cache doesn't exist.
     * @return the JSONArray if cache exists or defaultValue otherwise
     */
    public static JSONArray getJSONArray(@NonNull final String key, final JSONArray defaultValue) {
        return getJSONArray(key, defaultValue, getDefaultSlcCacheDiskUtils());
    }


    ///////////////////////////////////////////////////////////////////////////
    // about Bitmap
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put bitmap in cache.
     *
     * @param key   The key of cache.
     * @param value The value of cache.
     */
    public static void put(@NonNull final String key, final Bitmap value) {
        put(key, value, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Put bitmap in cache.
     *
     * @param key      The key of cache.
     * @param value    The value of cache.
     * @param saveTime The save time of cache, in seconds.
     */
    public static void put(@NonNull final String key, final Bitmap value, final int saveTime) {
        put(key, value, saveTime, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the bitmap in cache.
     *
     * @param key The key of cache.
     * @return the bitmap if cache exists or null otherwise
     */
    public static Bitmap getBitmap(@NonNull final String key) {
        return getBitmap(key, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the bitmap in cache.
     *
     * @param key          The key of cache.
     * @param defaultValue The default value if the cache doesn't exist.
     * @return the bitmap if cache exists or defaultValue otherwise
     */
    public static Bitmap getBitmap(@NonNull final String key, final Bitmap defaultValue) {
        return getBitmap(key, defaultValue, getDefaultSlcCacheDiskUtils());
    }

    ///////////////////////////////////////////////////////////////////////////
    // about Drawable
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put drawable in cache.
     *
     * @param key   The key of cache.
     * @param value The value of cache.
     */
    public static void put(@NonNull final String key, final Drawable value) {
        put(key, value, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Put drawable in cache.
     *
     * @param key      The key of cache.
     * @param value    The value of cache.
     * @param saveTime The save time of cache, in seconds.
     */
    public static void put(@NonNull final String key, final Drawable value, final int saveTime) {
        put(key, value, saveTime, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the drawable in cache.
     *
     * @param key The key of cache.
     * @return the drawable if cache exists or null otherwise
     */
    public static Drawable getDrawable(@NonNull final String key) {
        return getDrawable(key, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the drawable in cache.
     *
     * @param key          The key of cache.
     * @param defaultValue The default value if the cache doesn't exist.
     * @return the drawable if cache exists or defaultValue otherwise
     */
    public static Drawable getDrawable(@NonNull final String key, final Drawable defaultValue) {
        return getDrawable(key, defaultValue, getDefaultSlcCacheDiskUtils());
    }

    ///////////////////////////////////////////////////////////////////////////
    // about Parcelable
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put parcelable in cache.
     *
     * @param key   The key of cache.
     * @param value The value of cache.
     */
    public static void put(@NonNull final String key, final Parcelable value) {
        put(key, value, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Put parcelable in cache.
     *
     * @param key      The key of cache.
     * @param value    The value of cache.
     * @param saveTime The save time of cache, in seconds.
     */
    public static void put(@NonNull final String key, final Parcelable value, final int saveTime) {
        put(key, value, saveTime, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the parcelable in cache.
     *
     * @param key     The key of cache.
     * @param creator The creator.
     * @param <T>     The value type.
     * @return the parcelable if cache exists or null otherwise
     */
    public static <T> T getParcelable(@NonNull final String key,
                                      @NonNull final Parcelable.Creator<T> creator) {
        return getParcelable(key, creator, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the parcelable in cache.
     *
     * @param key          The key of cache.
     * @param creator      The creator.
     * @param defaultValue The default value if the cache doesn't exist.
     * @param <T>          The value type.
     * @return the parcelable if cache exists or defaultValue otherwise
     */
    public static <T> T getParcelable(@NonNull final String key,
                                      @NonNull final Parcelable.Creator<T> creator,
                                      final T defaultValue) {
        return getParcelable(key, creator, defaultValue, getDefaultSlcCacheDiskUtils());
    }

    ///////////////////////////////////////////////////////////////////////////
    // about Serializable
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put serializable in cache.
     *
     * @param key   The key of cache.
     * @param value The value of cache.
     */
    public static void put(@NonNull final String key, final Serializable value) {
        put(key, value, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Put serializable in cache.
     *
     * @param key      The key of cache.
     * @param value    The value of cache.
     * @param saveTime The save time of cache, in seconds.
     */
    public static void put(@NonNull final String key, final Serializable value, final int saveTime) {
        put(key, value, saveTime, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the serializable in cache.
     *
     * @param key The key of cache.
     * @return the bitmap if cache exists or null otherwise
     */
    public static Object getSerializable(@NonNull final String key) {
        return getSerializable(key, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the serializable in cache.
     *
     * @param key          The key of cache.
     * @param defaultValue The default value if the cache doesn't exist.
     * @return the bitmap if cache exists or defaultValue otherwise
     */
    public static Object getSerializable(@NonNull final String key, final Object defaultValue) {
        return getSerializable(key, defaultValue, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the size of cache, in bytes.
     *
     * @return the size of cache, in bytes
     */
    public static long getCacheSize() {
        return getCacheSize(getDefaultSlcCacheDiskUtils());
    }

    /**
     * Return the count of cache.
     *
     * @return the count of cache
     */
    public static int getCacheCount() {
        return getCacheCount(getDefaultSlcCacheDiskUtils());
    }

    /**
     * Remove the cache by key.
     *
     * @param key The key of cache.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean remove(@NonNull final String key) {
        return remove(key, getDefaultSlcCacheDiskUtils());
    }

    /**
     * Clear all of the cache.
     *
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean clear() {
        return clear(getDefaultSlcCacheDiskUtils());
    }

    ///////////////////////////////////////////////////////////////////////////
    // dividing line
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put bytes in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final byte[] value,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value);
    }

    /**
     * Put bytes in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param saveTime       The save time of cache, in seconds.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final byte[] value,
                           final int saveTime,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value, saveTime);
    }

    /**
     * Return the bytes in cache.
     *
     * @param key            The key of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the bytes if cache exists or null otherwise
     */
    public static byte[] getBytes(@NonNull final String key, @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getBytes(key);
    }

    /**
     * Return the bytes in cache.
     *
     * @param key            The key of cache.
     * @param defaultValue   The default value if the cache doesn't exist.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the bytes if cache exists or defaultValue otherwise
     */
    public static byte[] getBytes(@NonNull final String key,
                                  final byte[] defaultValue,
                                  @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getBytes(key, defaultValue);
    }

    ///////////////////////////////////////////////////////////////////////////
    // about String
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put string value in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final String value,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value);
    }

    /**
     * Put string value in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param saveTime       The save time of cache, in seconds.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final String value,
                           final int saveTime,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value, saveTime);
    }

    /**
     * Return the string value in cache.
     *
     * @param key            The key of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the string value if cache exists or null otherwise
     */
    public static String getString(@NonNull final String key, @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getString(key);
    }

    /**
     * Return the string value in cache.
     *
     * @param key            The key of cache.
     * @param defaultValue   The default value if the cache doesn't exist.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the string value if cache exists or defaultValue otherwise
     */
    public static String getString(@NonNull final String key,
                                   final String defaultValue,
                                   @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getString(key, defaultValue);
    }

    ///////////////////////////////////////////////////////////////////////////
    // about JSONObject
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put JSONObject in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final JSONObject value,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value);
    }

    /**
     * Put JSONObject in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param saveTime       The save time of cache, in seconds.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final JSONObject value,
                           final int saveTime,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value, saveTime);
    }

    /**
     * Return the JSONObject in cache.
     *
     * @param key            The key of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the JSONObject if cache exists or null otherwise
     */
    public static JSONObject getJSONObject(@NonNull final String key, @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getJSONObject(key);
    }

    /**
     * Return the JSONObject in cache.
     *
     * @param key            The key of cache.
     * @param defaultValue   The default value if the cache doesn't exist.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the JSONObject if cache exists or defaultValue otherwise
     */
    public static JSONObject getJSONObject(@NonNull final String key,
                                           final JSONObject defaultValue,
                                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getJSONObject(key, defaultValue);
    }


    ///////////////////////////////////////////////////////////////////////////
    // about JSONArray
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put JSONArray in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final JSONArray value,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value);
    }

    /**
     * Put JSONArray in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param saveTime       The save time of cache, in seconds.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final JSONArray value,
                           final int saveTime,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value, saveTime);
    }

    /**
     * Return the JSONArray in cache.
     *
     * @param key            The key of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the JSONArray if cache exists or null otherwise
     */
    public static JSONArray getJSONArray(@NonNull final String key, @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getJSONArray(key);
    }

    /**
     * Return the JSONArray in cache.
     *
     * @param key            The key of cache.
     * @param defaultValue   The default value if the cache doesn't exist.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the JSONArray if cache exists or defaultValue otherwise
     */
    public static JSONArray getJSONArray(@NonNull final String key,
                                         final JSONArray defaultValue,
                                         @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getJSONArray(key, defaultValue);
    }


    ///////////////////////////////////////////////////////////////////////////
    // about Bitmap
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put bitmap in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final Bitmap value,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value);
    }

    /**
     * Put bitmap in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param saveTime       The save time of cache, in seconds.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final Bitmap value,
                           final int saveTime,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value, saveTime);
    }

    /**
     * Return the bitmap in cache.
     *
     * @param key            The key of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the bitmap if cache exists or null otherwise
     */
    public static Bitmap getBitmap(@NonNull final String key, @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getBitmap(key);
    }

    /**
     * Return the bitmap in cache.
     *
     * @param key            The key of cache.
     * @param defaultValue   The default value if the cache doesn't exist.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the bitmap if cache exists or defaultValue otherwise
     */
    public static Bitmap getBitmap(@NonNull final String key,
                                   final Bitmap defaultValue,
                                   @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getBitmap(key, defaultValue);
    }

    ///////////////////////////////////////////////////////////////////////////
    // about Drawable
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put drawable in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final Drawable value,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value);
    }

    /**
     * Put drawable in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param saveTime       The save time of cache, in seconds.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final Drawable value,
                           final int saveTime,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value, saveTime);
    }

    /**
     * Return the drawable in cache.
     *
     * @param key            The key of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the drawable if cache exists or null otherwise
     */
    public static Drawable getDrawable(@NonNull final String key, @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getDrawable(key);
    }

    /**
     * Return the drawable in cache.
     *
     * @param key            The key of cache.
     * @param defaultValue   The default value if the cache doesn't exist.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the drawable if cache exists or defaultValue otherwise
     */
    public static Drawable getDrawable(@NonNull final String key,
                                       final Drawable defaultValue,
                                       @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getDrawable(key, defaultValue);
    }

    ///////////////////////////////////////////////////////////////////////////
    // about Parcelable
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put parcelable in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final Parcelable value,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value);
    }

    /**
     * Put parcelable in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param saveTime       The save time of cache, in seconds.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final Parcelable value,
                           final int saveTime,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value, saveTime);
    }

    /**
     * Return the parcelable in cache.
     *
     * @param key            The key of cache.
     * @param creator        The creator.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @param <T>            The value type.
     * @return the parcelable if cache exists or null otherwise
     */
    public static <T> T getParcelable(@NonNull final String key,
                                      @NonNull final Parcelable.Creator<T> creator,
                                      @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getParcelable(key, creator);
    }

    /**
     * Return the parcelable in cache.
     *
     * @param key            The key of cache.
     * @param creator        The creator.
     * @param defaultValue   The default value if the cache doesn't exist.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @param <T>            The value type.
     * @return the parcelable if cache exists or defaultValue otherwise
     */
    public static <T> T getParcelable(@NonNull final String key,
                                      @NonNull final Parcelable.Creator<T> creator,
                                      final T defaultValue,
                                      @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getParcelable(key, creator, defaultValue);
    }

    ///////////////////////////////////////////////////////////////////////////
    // about Serializable
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put serializable in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final Serializable value,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value);
    }

    /**
     * Put serializable in cache.
     *
     * @param key            The key of cache.
     * @param value          The value of cache.
     * @param saveTime       The save time of cache, in seconds.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     */
    public static void put(@NonNull final String key,
                           final Serializable value,
                           final int saveTime,
                           @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        SlcCacheDiskUtils.put(key, value, saveTime);
    }

    /**
     * Return the serializable in cache.
     *
     * @param key            The key of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the bitmap if cache exists or null otherwise
     */
    public static Object getSerializable(@NonNull final String key, @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getSerializable(key);
    }

    /**
     * Return the serializable in cache.
     *
     * @param key            The key of cache.
     * @param defaultValue   The default value if the cache doesn't exist.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the bitmap if cache exists or defaultValue otherwise
     */
    public static Object getSerializable(@NonNull final String key,
                                         final Object defaultValue,
                                         @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getSerializable(key, defaultValue);
    }

    /**
     * Return the size of cache, in bytes.
     *
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the size of cache, in bytes
     */
    public static long getCacheSize(@NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getCacheSize();
    }

    /**
     * Return the count of cache.
     *
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return the count of cache
     */
    public static int getCacheCount(@NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.getCacheCount();
    }

    /**
     * Remove the cache by key.
     *
     * @param key            The key of cache.
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean remove(@NonNull final String key, @NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.remove(key);
    }

    /**
     * Clear all of the cache.
     *
     * @param SlcCacheDiskUtils The instance of {@link SlcCacheDiskUtils}.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean clear(@NonNull final SlcCacheDiskUtils SlcCacheDiskUtils) {
        return SlcCacheDiskUtils.clear();
    }

    private static SlcCacheDiskUtils getDefaultSlcCacheDiskUtils() {
        return sDefaultSlcCacheDiskUtils != null ? sDefaultSlcCacheDiskUtils : SlcCacheDiskUtils.getInstance();
    }
}