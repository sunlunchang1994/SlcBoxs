package android.slc.or;

import androidx.annotation.Nullable;

import java.util.HashMap;

/**
 * @author slc
 * @date 2019/12/18 14:14
 */
public class NonEmptyHasMap<K, V> extends HashMap<K, V> {
    @Nullable
    @Override
    public V put(K key, V value) {
        if (value != null) {
            return super.put(key, value);
        }
        return value;
    }
}
