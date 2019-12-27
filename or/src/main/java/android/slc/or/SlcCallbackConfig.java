package android.slc.or;

public class SlcCallbackConfig {
    public static final SlcCallbackConfig defSlcCallbackConfig = new SlcCallbackConfig();
    private int mDialogMsg = R.string.net_date_is_loading;
    private int mToastRes = R.string.net_date_acquisition_failed;
    private boolean mIsShowDialog;
    private boolean mIsShowToast = true;
    private boolean mIsAutoDismissDialog = true;

    private SlcCallbackConfig() {
    }

    public SlcCallbackConfig(int mDialogMsg, int mToastRes, boolean mIsShowDialog, boolean mIsShowToast, boolean isAutoDismissDialog) {
        this.mDialogMsg = mDialogMsg;
        this.mToastRes = mToastRes;
        this.mIsShowDialog = mIsShowDialog;
        this.mIsShowToast = mIsShowToast;
        this.mIsAutoDismissDialog = isAutoDismissDialog;
    }

    public int getDialogMsg() {
        return mDialogMsg;
    }

    public int getToastRes() {
        return mToastRes;
    }

    public boolean isIsShowDialog() {
        return mIsShowDialog;
    }

    public boolean isIsShowToast() {
        return mIsShowToast;
    }

    public boolean isIsAutoDismissDialog() {
        return mIsAutoDismissDialog;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int mDialogMsg = R.string.net_date_is_loading;
        private int mToastRes = R.string.net_date_acquisition_failed;
        private boolean mIsShowDialog;
        private boolean mIsShowToast = true;
        private boolean mIsAutoDismissDialog = true;

        public Builder() {

        }

        public Builder showDialog(boolean showDialog) {
            mIsShowDialog = showDialog;
            return this;
        }

        public Builder showToast(boolean showToast) {
            mIsShowToast = showToast;
            return this;
        }

        public Builder setDialogMsg(int dialogMsg) {
            this.mDialogMsg = dialogMsg;
            showDialog(true);
            return this;
        }

        public Builder setToastRes(int toastRes) {
            this.mToastRes = toastRes;
            showToast(true);
            return this;
        }

        public Builder setIsAutoDismissDialog(boolean isAutoDismissDialog) {
            this.mIsAutoDismissDialog = isAutoDismissDialog;
            return this;
        }

        public SlcCallbackConfig build() {
            return new SlcCallbackConfig(mDialogMsg, mToastRes, mIsShowDialog, mIsShowToast, mIsAutoDismissDialog);
        }
    }
}