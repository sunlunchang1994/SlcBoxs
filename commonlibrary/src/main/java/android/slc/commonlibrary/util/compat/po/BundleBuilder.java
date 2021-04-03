package android.slc.commonlibrary.util.compat.po;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class BundleBuilder {
    private final Bundle bundle;

    private BundleBuilder() {
        bundle = new Bundle();
    }

    public static BundleBuilder create() {
        return new BundleBuilder();
    }

    public BundleBuilder putByte(@Nullable String key, byte value) {
        bundle.putByte(key, value);
        return this;
    }

    public BundleBuilder putChar(@Nullable String key, char value) {
        bundle.putChar(key, value);
        return this;
    }

    public BundleBuilder putShort(@Nullable String key, short value) {
        bundle.putShort(key, value);
        return this;
    }


    public BundleBuilder putFloat(@Nullable String key, float value) {
        bundle.putFloat(key, value);
        return this;
    }

    public BundleBuilder putCharSequence(@Nullable String key, @Nullable CharSequence value) {
        bundle.putCharSequence(key, value);
        return this;
    }

    public BundleBuilder putParcelable(@Nullable String key, @Nullable Parcelable value) {
        bundle.putParcelable(key, value);
        return this;
    }

    public BundleBuilder putSize(@Nullable String key, @Nullable Size value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bundle.putSize(key, value);
        }
        return this;
    }

    public BundleBuilder putSizeF(@Nullable String key, @Nullable SizeF value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bundle.putSizeF(key, value);
        }
        return this;
    }

    public BundleBuilder putParcelableArray(@Nullable String key, @Nullable Parcelable[] value) {
        bundle.putParcelableArray(key, value);
        return this;
    }

    public BundleBuilder putParcelableArrayList(@Nullable String key,
                                                @Nullable ArrayList<? extends Parcelable> value) {
        bundle.putParcelableArrayList(key, value);
        return this;
    }

    public BundleBuilder putSparseParcelableArray(@Nullable String key,
                                                  @Nullable SparseArray<? extends Parcelable> value) {
        bundle.putSparseParcelableArray(key, value);
        return this;
    }

    public BundleBuilder putIntegerArrayList(@Nullable String key, @Nullable ArrayList<Integer> value) {
        bundle.putIntegerArrayList(key, value);
        return this;
    }

    public BundleBuilder putStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        bundle.putStringArrayList(key, value);
        return this;
    }

    public BundleBuilder putCharSequenceArrayList(@Nullable String key,
                                                  @Nullable ArrayList<CharSequence> value) {
        bundle.putCharSequenceArrayList(key, value);
        return this;
    }

    public BundleBuilder putSerializable(@Nullable String key, @Nullable Serializable value) {
        bundle.putSerializable(key, value);
        return this;
    }

    public BundleBuilder putByteArray(@Nullable String key, @Nullable byte[] value) {
        bundle.putByteArray(key, value);
        return this;
    }

    public BundleBuilder putShortArray(@Nullable String key, @Nullable short[] value) {
        bundle.putShortArray(key, value);
        return this;
    }

    public BundleBuilder putCharArray(@Nullable String key, @Nullable char[] value) {
        bundle.putCharArray(key, value);
        return this;
    }

    public BundleBuilder putFloatArray(@Nullable String key, @Nullable float[] value) {
        bundle.putFloatArray(key, value);
        return this;
    }

    public BundleBuilder putCharSequenceArray(@Nullable String key, @Nullable CharSequence[] value) {
        bundle.putCharSequenceArray(key, value);
        return this;
    }

    public BundleBuilder putBundle(@Nullable String key, @Nullable Bundle value) {
        bundle.putBundle(key, value);
        return this;
    }

    public BundleBuilder putBinder(@Nullable String key, @Nullable IBinder value) {
        bundle.putBinder(key, value);
        return this;
    }

    public Bundle build() {
        return bundle;
    }
}
