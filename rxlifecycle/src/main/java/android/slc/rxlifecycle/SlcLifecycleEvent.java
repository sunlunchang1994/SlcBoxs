package android.slc.rxlifecycle;

/**
 * @author slc
 * @date 2019/10/25 10:28
 */
public enum SlcLifecycleEvent {
    CREATE,
    START,
    RESUME,
    PAUSE,
    STOP,
    DESTROY;

    SlcLifecycleEvent() {
    }
}