package android.slc.code.domain;

import android.app.Activity;
import android.os.Bundle;

public class SlcActivityResult {
    private int resultCode;
    private Bundle bundle;
    private boolean isFinish;

    private SlcActivityResult(int resultCode, Bundle bundle, boolean isFinish) {
        this.resultCode = resultCode;
        this.bundle = bundle;
        this.isFinish = isFinish;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int resultCode = Activity.RESULT_OK;
        private Bundle bundle;
        private boolean isFinish;

        public Builder setResultCode(int resultCode) {
            this.resultCode = resultCode;
            return this;
        }

        public Builder setBundle(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }

        public Builder setFinish(boolean finish) {
            isFinish = finish;
            return this;
        }

        public SlcActivityResult build() {
            return new SlcActivityResult(resultCode, bundle, isFinish);
        }
    }
}
