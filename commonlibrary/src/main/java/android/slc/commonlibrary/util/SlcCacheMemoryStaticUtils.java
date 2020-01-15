package android.slc.commonlibrary.util;

import androidx.annotation.NonNull;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2019/01/04
 *     desc  : utils about memory cache
 * </pre>
 */
public final class SlcCacheMemoryStaticUtils {

    private static SlcCacheMemoryUtils sDefaultSlcCacheMemoryUtils;

    /**
     * Set the default instance of {@link SlcCacheMemoryUtils}.
     *
     * @param slcCacheMemoryUtils The default instance of {@link SlcCacheMemoryUtils}.
     */
    public static void setDefaultSlcCacheMemoryUtils(final SlcCacheMemoryUtils slcCacheMemoryUtils) {
        sDefaultSlcCacheMemoryUtils = slcCacheMemoryUtils;
    }

    /**
     * Put bytes in cache.
     *
     * @param key   The key of cache.
     * @param value The value of cache.
     */
    public static void put(@NonNull final String key, final Object value) {
        put(key, value, getDefaultSlcCacheMemoryUtils());
    }

    /**
     * Put bytes in cache.
     *
     * @param key      The key of cache.
     * @param value    The value of cache.
     * @param saveTime The save time of cache, in seconds.
     */
    public static void put(@NonNull final String key, final Object value, int saveTime) {
        put(key, value, saveTime, getDefaultSlcCacheMemoryUtils());
    }

    /**
     * Return the value in cache.
     *
     * @param key The key of cache.
     * @param <T> The value type.
     * @return the value if cache exists or null otherwise
     */
    public static <T> T get(@NonNull final String key) {
        return get(key, getDefaultSlcCacheMemoryUtils());
    }

    /**
     * Return the value in cache.
     *
     * @param key          The key of cache.
     * @param defaultValue The default value if the cache doesn't exist.
     * @param <T>          The value type.
     * @return the value if cache exists or defaultValue otherwise
     */
    public static <T> T get(@NonNull final String key, final T defaultValue) {
        return get(key, defaultValue, getDefaultSlcCacheMemoryUtils());
    }

    /**
     * Return the count of cache.
     *
     * @return the count of cache
     */
    public static int getCacheCount() {
        return getCacheCount(getDefaultSlcCacheMemoryUtils());
    }

    /**
     * Remove the cache by key.
     *
     * @param key The key of cache.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static Object remove(@NonNull final String key) {
        return remove(key, getDefaultSlcCacheMemoryUtils());
    }

    /**
     * Clear all of the cache.
     */
    public static void clear() {
        clear(getDefaultSlcCacheMemoryUtils());
    }

    ///////////////////////////////////////////////////////////////////////////
    // dividing line
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put bytes in cache.
     *
     * @param key              The key of cache.
     * @param value            The value of cache.
     * @param slcCacheMemoryUtils The instance of {@link SlcCacheMemoryUtils}.
     */
    public static void put(@NonNull final String key,
                           final Object value,
                           @NonNull final SlcCacheMemoryUtils slcCacheMemoryUtils) {
        slcCacheMemoryUtils.put(key, value);
    }

    /**
     * Put bytes in cache.
     *
     * @param key              The key of cache.
     * @param value            The value of cache.
     * @param saveTime         The save time of cache, in seconds.
     * @param slcCacheMemoryUtils The instance of {@link SlcCacheMemoryUtils}.
     */
    public static void put(@NonNull final String key,
                           final Object value,
                           int saveTime,
                           @NonNull final SlcCacheMemoryUtils slcCacheMemoryUtils) {
        slcCacheMemoryUtils.put(key, value, saveTime);
    }

    /**
     * Return the value in cache.
     *
     * @param key              The key of cache.
     * @param slcCacheMemoryUtils The instance of {@link SlcCacheMemoryUtils}.
     * @param <T>              The value type.
     * @return the value if cache exists or null otherwise
     */
    public static <T> T get(@NonNull final String key, @NonNull final SlcCacheMemoryUtils slcCacheMemoryUtils) {
        return slcCacheMemoryUtils.get(key);
    }

    /**
     * Return the value in cache.
     *
     * @param key              The key of cache.
     * @param defaultValue     The default value if the cache doesn't exist.
     * @param slcCacheMemoryUtils The instance of {@link SlcCacheMemoryUtils}.
     * @param <T>              The value type.
     * @return the value if cache exists or defaultValue otherwise
     */
    public static <T> T get(@NonNull final String key,
                            final T defaultValue,
                            @NonNull final SlcCacheMemoryUtils slcCacheMemoryUtils) {
        return slcCacheMemoryUtils.get(key, defaultValue);
    }

    /**
     * Return the count of cache.
     *
     * @param slcCacheMemoryUtils The instance of {@link SlcCacheMemoryUtils}.
     * @return the count of cache
     */
    public static int getCacheCount(@NonNull final SlcCacheMemoryUtils slcCacheMemoryUtils) {
        return slcCacheMemoryUtils.getCacheCount();
    }

    /**
     * Remove the cache by key.
     *
     * @param key              The key of cache.
     * @param slcCacheMemoryUtils The instance of {@link SlcCacheMemoryUtils}.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static Object remove(@NonNull final String key, @NonNull final SlcCacheMemoryUtils slcCacheMemoryUtils) {
        return slcCacheMemoryUtils.remove(key);
    }

    /**
     * Clear all of the cache.
     *
     * @param slcCacheMemoryUtils The instance of {@link SlcCacheMemoryUtils}.
     */
    public static void clear(@NonNull final SlcCacheMemoryUtils slcCacheMemoryUtils) {
        slcCacheMemoryUtils.clear();
    }

    private static SlcCacheMemoryUtils getDefaultSlcCacheMemoryUtils() {
        return sDefaultSlcCacheMemoryUtils != null ? sDefaultSlcCacheMemoryUtils : SlcCacheMemoryUtils.getInstance();
    }
}