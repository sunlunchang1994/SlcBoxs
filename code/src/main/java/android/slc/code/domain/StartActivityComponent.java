package android.slc.code.domain;

import android.os.Bundle;

public class StartActivityComponent {
    private Class<?> startActivityClass;
    private Bundle bundle;

    public StartActivityComponent() {
    }

    public StartActivityComponent(Class<?> startActivityClass) {
        this.startActivityClass = startActivityClass;
    }

    public StartActivityComponent(Class<?> startActivityClass, Bundle bundle) {
        this.startActivityClass = startActivityClass;
        this.bundle = bundle;
    }

    public Class<?> getStartActivityClass() {
        return startActivityClass;
    }

    public void setStartActivityClass(Class<?> startActivityClass) {
        this.startActivityClass = startActivityClass;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
