package android.slc.or;

public interface OnResultsListener<T> {
    void onSucceed(T data);

    void onError(int errorCode, String errorMessage);
}