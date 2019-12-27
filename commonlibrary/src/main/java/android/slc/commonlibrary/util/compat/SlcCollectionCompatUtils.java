package android.slc.commonlibrary.util.compat;

import java.util.Collection;

public class SlcCollectionCompatUtils {

    private SlcCollectionCompatUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Adds all elements in the iteration to the given collection.
     *
     * @param collection        the collection to add to, may be null
     * @param elementCollection the elementCollection of elements to add, may be null
     */
    public static <E> void addAll(Collection<E> collection, Collection<E> elementCollection) {
        if (collection == null || elementCollection == null || elementCollection.size() == 0)
            return;
        collection.addAll(elementCollection);
    }
}
