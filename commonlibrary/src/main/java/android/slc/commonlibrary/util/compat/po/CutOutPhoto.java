package android.slc.commonlibrary.util.compat.po;

import android.net.Uri;
import android.os.Bundle;

/**
 * @author slc
 * @date 2020-10-10 13:42
 */
public class CutOutPhoto {
    private Uri inputUri;
    private Bundle bundle;

    public Uri getInputUri() {
        return inputUri;
    }

    public void setInputUri(Uri inputUri) {
        this.inputUri = inputUri;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
