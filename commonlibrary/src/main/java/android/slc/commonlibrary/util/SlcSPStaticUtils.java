package android.slc.commonlibrary.util;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2019/01/04
 *     desc  : utils about shared preference
 * </pre>
 */
public final class SlcSPStaticUtils {

    private static SlcSPUtils sDefaultSlcSPUtils;

    /**
     * Set the default instance of {@link SlcSPUtils}.
     *
     * @param SlcSPUtils The default instance of {@link SlcSPUtils}.
     */
    public static void setDefaultSlcSPUtils(final SlcSPUtils SlcSPUtils) {
        sDefaultSlcSPUtils = SlcSPUtils;
    }

    /**
     * Put the string value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    public static void put(@NonNull final String key, final String value) {
        put(key, value, getDefaultSlcSPUtils());
    }

    /**
     * Put the string value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void put(@NonNull final String key, final String value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSlcSPUtils());
    }


    /**
     * Return the string value in sp.
     *
     * @param key The key of sp.
     * @return the string value if sp exists or {@code ""} otherwise
     */
    public static String getString(@NonNull final String key) {
        return getString(key, getDefaultSlcSPUtils());
    }

    /**
     * Return the string value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the string value if sp exists or {@code defaultValue} otherwise
     */
    public static String getString(@NonNull final String key, final String defaultValue) {
        return getString(key, defaultValue, getDefaultSlcSPUtils());
    }


    /**
     * Put the int value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    public static void put(@NonNull final String key, final int value) {
        put(key, value, getDefaultSlcSPUtils());
    }

    /**
     * Put the int value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void put(@NonNull final String key, final int value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSlcSPUtils());
    }

    /**
     * Return the int value in sp.
     *
     * @param key The key of sp.
     * @return the int value if sp exists or {@code -1} otherwise
     */
    public static int getInt(@NonNull final String key) {
        return getInt(key, getDefaultSlcSPUtils());
    }

    /**
     * Return the int value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the int value if sp exists or {@code defaultValue} otherwise
     */
    public static int getInt(@NonNull final String key, final int defaultValue) {
        return getInt(key, defaultValue, getDefaultSlcSPUtils());
    }

    /**
     * Put the long value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    public static void put(@NonNull final String key, final long value) {
        put(key, value, getDefaultSlcSPUtils());
    }

    /**
     * Put the long value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void put(@NonNull final String key, final long value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSlcSPUtils());
    }

    /**
     * Return the long value in sp.
     *
     * @param key The key of sp.
     * @return the long value if sp exists or {@code -1} otherwise
     */
    public static long getLong(@NonNull final String key) {
        return getLong(key, getDefaultSlcSPUtils());
    }

    /**
     * Return the long value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the long value if sp exists or {@code defaultValue} otherwise
     */
    public static long getLong(@NonNull final String key, final long defaultValue) {
        return getLong(key, defaultValue, getDefaultSlcSPUtils());
    }

    /**
     * Put the float value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    public static void put(@NonNull final String key, final float value) {
        put(key, value, getDefaultSlcSPUtils());
    }

    /**
     * Put the float value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void put(@NonNull final String key, final float value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSlcSPUtils());
    }

    /**
     * Return the float value in sp.
     *
     * @param key The key of sp.
     * @return the float value if sp exists or {@code -1f} otherwise
     */
    public static float getFloat(@NonNull final String key) {
        return getFloat(key, getDefaultSlcSPUtils());
    }

    /**
     * Return the float value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the float value if sp exists or {@code defaultValue} otherwise
     */
    public static float getFloat(@NonNull final String key, final float defaultValue) {
        return getFloat(key, defaultValue, getDefaultSlcSPUtils());
    }

    /**
     * Put the boolean value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    public static void put(@NonNull final String key, final boolean value) {
        put(key, value, getDefaultSlcSPUtils());
    }

    /**
     * Put the boolean value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void put(@NonNull final String key, final boolean value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSlcSPUtils());
    }

    /**
     * Return the boolean value in sp.
     *
     * @param key The key of sp.
     * @return the boolean value if sp exists or {@code false} otherwise
     */
    public static boolean getBoolean(@NonNull final String key) {
        return getBoolean(key, getDefaultSlcSPUtils());
    }

    /**
     * Return the boolean value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the boolean value if sp exists or {@code defaultValue} otherwise
     */
    public static boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        return getBoolean(key, defaultValue, getDefaultSlcSPUtils());
    }

    /**
     * Put the set of string value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    public static void put(@NonNull final String key, final Set<String> value) {
        put(key, value, getDefaultSlcSPUtils());
    }

    /**
     * Put the set of string value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void put(@NonNull final String key,
                           final Set<String> value,
                           final boolean isCommit) {
        put(key, value, isCommit, getDefaultSlcSPUtils());
    }

    /**
     * Return the set of string value in sp.
     *
     * @param key The key of sp.
     * @return the set of string value if sp exists
     * or {@code Collections.<String>emptySet()} otherwise
     */
    public static Set<String> getStringSet(@NonNull final String key) {
        return getStringSet(key, getDefaultSlcSPUtils());
    }

    /**
     * Return the set of string value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the set of string value if sp exists or {@code defaultValue} otherwise
     */
    public static Set<String> getStringSet(@NonNull final String key,
                                           final Set<String> defaultValue) {
        return getStringSet(key, defaultValue, getDefaultSlcSPUtils());
    }

    /**
     * Return all values in sp.
     *
     * @return all values in sp
     */
    public static Map<String, ?> getAll() {
        return getAll(getDefaultSlcSPUtils());
    }

    /**
     * Return whether the sp contains the preference.
     *
     * @param key The key of sp.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean contains(@NonNull final String key) {
        return contains(key, getDefaultSlcSPUtils());
    }

    /**
     * Remove the preference in sp.
     *
     * @param key The key of sp.
     */
    public static void remove(@NonNull final String key) {
        remove(key, getDefaultSlcSPUtils());
    }

    /**
     * Remove the preference in sp.
     *
     * @param key      The key of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void remove(@NonNull final String key, final boolean isCommit) {
        remove(key, isCommit, getDefaultSlcSPUtils());
    }

    /**
     * Remove all preferences in sp.
     */
    public static void clear() {
        clear(getDefaultSlcSPUtils());
    }

    /**
     * Remove all preferences in sp.
     *
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public static void clear(final boolean isCommit) {
        clear(isCommit, getDefaultSlcSPUtils());
    }

    ///////////////////////////////////////////////////////////////////////////
    // dividing line
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Put the string value in sp.
     *
     * @param key     The key of sp.
     * @param value   The value of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key, final String value, @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value);
    }

    /**
     * Put the string value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     * @param SlcSPUtils  The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key,
                           final String value,
                           final boolean isCommit,
                           @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value, isCommit);
    }


    /**
     * Return the string value in sp.
     *
     * @param key     The key of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     * @return the string value if sp exists or {@code ""} otherwise
     */
    public static String getString(@NonNull final String key, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getString(key);
    }

    /**
     * Return the string value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @param SlcSPUtils      The instance of {@link SlcSPUtils}.
     * @return the string value if sp exists or {@code defaultValue} otherwise
     */
    public static String getString(@NonNull final String key,
                                   final String defaultValue,
                                   @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getString(key, defaultValue);
    }


    /**
     * Put the int value in sp.
     *
     * @param key     The key of sp.
     * @param value   The value of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key, final int value, @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value);
    }

    /**
     * Put the int value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     * @param SlcSPUtils  The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key,
                           final int value,
                           final boolean isCommit,
                           @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value, isCommit);
    }

    /**
     * Return the int value in sp.
     *
     * @param key     The key of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     * @return the int value if sp exists or {@code -1} otherwise
     */
    public static int getInt(@NonNull final String key, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getInt(key);
    }

    /**
     * Return the int value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @param SlcSPUtils      The instance of {@link SlcSPUtils}.
     * @return the int value if sp exists or {@code defaultValue} otherwise
     */
    public static int getInt(@NonNull final String key, final int defaultValue, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getInt(key, defaultValue);
    }

    /**
     * Put the long value in sp.
     *
     * @param key     The key of sp.
     * @param value   The value of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key, final long value, @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value);
    }

    /**
     * Put the long value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     * @param SlcSPUtils  The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key,
                           final long value,
                           final boolean isCommit,
                           @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value, isCommit);
    }

    /**
     * Return the long value in sp.
     *
     * @param key     The key of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     * @return the long value if sp exists or {@code -1} otherwise
     */
    public static long getLong(@NonNull final String key, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getLong(key);
    }

    /**
     * Return the long value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @param SlcSPUtils      The instance of {@link SlcSPUtils}.
     * @return the long value if sp exists or {@code defaultValue} otherwise
     */
    public static long getLong(@NonNull final String key, final long defaultValue, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getLong(key, defaultValue);
    }

    /**
     * Put the float value in sp.
     *
     * @param key     The key of sp.
     * @param value   The value of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key, final float value, @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value);
    }

    /**
     * Put the float value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     * @param SlcSPUtils  The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key,
                           final float value,
                           final boolean isCommit,
                           @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value, isCommit);
    }

    /**
     * Return the float value in sp.
     *
     * @param key     The key of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     * @return the float value if sp exists or {@code -1f} otherwise
     */
    public static float getFloat(@NonNull final String key, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getFloat(key);
    }

    /**
     * Return the float value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @param SlcSPUtils      The instance of {@link SlcSPUtils}.
     * @return the float value if sp exists or {@code defaultValue} otherwise
     */
    public static float getFloat(@NonNull final String key, final float defaultValue, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getFloat(key, defaultValue);
    }

    /**
     * Put the boolean value in sp.
     *
     * @param key     The key of sp.
     * @param value   The value of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key, final boolean value, @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value);
    }

    /**
     * Put the boolean value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     * @param SlcSPUtils  The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key,
                           final boolean value,
                           final boolean isCommit,
                           @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value, isCommit);
    }

    /**
     * Return the boolean value in sp.
     *
     * @param key     The key of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     * @return the boolean value if sp exists or {@code false} otherwise
     */
    public static boolean getBoolean(@NonNull final String key, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getBoolean(key);
    }

    /**
     * Return the boolean value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @param SlcSPUtils      The instance of {@link SlcSPUtils}.
     * @return the boolean value if sp exists or {@code defaultValue} otherwise
     */
    public static boolean getBoolean(@NonNull final String key,
                                     final boolean defaultValue,
                                     @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getBoolean(key, defaultValue);
    }

    /**
     * Put the set of string value in sp.
     *
     * @param key     The key of sp.
     * @param value   The value of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key, final Set<String> value, @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value);
    }

    /**
     * Put the set of string value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     * @param SlcSPUtils  The instance of {@link SlcSPUtils}.
     */
    public static void put(@NonNull final String key,
                           final Set<String> value,
                           final boolean isCommit,
                           @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.put(key, value, isCommit);
    }

    /**
     * Return the set of string value in sp.
     *
     * @param key     The key of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     * @return the set of string value if sp exists
     * or {@code Collections.<String>emptySet()} otherwise
     */
    public static Set<String> getStringSet(@NonNull final String key, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getStringSet(key);
    }

    /**
     * Return the set of string value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @param SlcSPUtils      The instance of {@link SlcSPUtils}.
     * @return the set of string value if sp exists or {@code defaultValue} otherwise
     */
    public static Set<String> getStringSet(@NonNull final String key,
                                           final Set<String> defaultValue,
                                           @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getStringSet(key, defaultValue);
    }

    /**
     * Return all values in sp.
     *
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     * @return all values in sp
     */
    public static Map<String, ?> getAll(@NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.getAll();
    }

    /**
     * Return whether the sp contains the preference.
     *
     * @param key     The key of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean contains(@NonNull final String key, @NonNull final SlcSPUtils SlcSPUtils) {
        return SlcSPUtils.contains(key);
    }

    /**
     * Remove the preference in sp.
     *
     * @param key     The key of sp.
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     */
    public static void remove(@NonNull final String key, @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.remove(key);
    }

    /**
     * Remove the preference in sp.
     *
     * @param key      The key of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     * @param SlcSPUtils  The instance of {@link SlcSPUtils}.
     */
    public static void remove(@NonNull final String key, final boolean isCommit, @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.remove(key, isCommit);
    }

    /**
     * Remove all preferences in sp.
     *
     * @param SlcSPUtils The instance of {@link SlcSPUtils}.
     */
    public static void clear(@NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.clear();
    }

    /**
     * Remove all preferences in sp.
     *
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     * @param SlcSPUtils  The instance of {@link SlcSPUtils}.
     */
    public static void clear(final boolean isCommit, @NonNull final SlcSPUtils SlcSPUtils) {
        SlcSPUtils.clear(isCommit);
    }

    private static SlcSPUtils getDefaultSlcSPUtils() {
        return sDefaultSlcSPUtils != null ? sDefaultSlcSPUtils : SlcSPUtils.getInstance();
    }
}