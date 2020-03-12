package android.slc.code.exception;

/**
 * mvp模式产生的空指针异常 * Created by on the way on 2018/7/14.
 */

public class MvvmNullPointerException extends NullPointerException {

    public MvvmNullPointerException() {
        super();
    }


    public MvvmNullPointerException(String s) {
        super(s);
    }
}
