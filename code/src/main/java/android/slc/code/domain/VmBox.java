package android.slc.code.domain;

import android.os.Bundle;
import android.slc.code.vm.SingleLiveEvent;
import android.util.Log;

/**
 * VmBox，这是一个将ViewModel的部分功能抽出来而得到的一个类。
 * 为什么会有此类？
 * 在某些场景下，n个activity或fragment的部分视图都具有相同的功能（他们之间是独立的，没有数据共享），
 * 第一种办法是将这些视图配置一个ViewModel，让具有该视图的activity或fragment持有该ViewModel来进行复用，
 * 第二种办法是让该视图所在的activity或fragment的主ViewModel来继承局部视图的ViewModel，但是由于Java的单继承特性，
 * 使得这样使用很不现实，因为该ViewModel很可能还需要继承其他的ViewModel。
 * 第三种是让该视图所在的activity或fragment的主ViewModel持有视图的ViewModel，或者将视图的ViewModel的参数传递给
 * activity或fragment的主ViewModel，这种方式个人觉得很不可取。
 * 说到这里相信你也明白了，这就是针对视图对某些功能模块的封装类。
 * 最佳使用方式：VmBox由ViewModel初始化，并加上final修饰符，根据具体的需求选择权限修饰符。
 * 对于以上问题，如果你有更好的解决方案，请联系我进行改进，感谢
 *
 * @author slc
 * @date 2021/4/2 12:38
 * @email sunlunchang@outlook.com
 */
public class VmBox {
    protected SingleLiveEvent<Void> finishOf;
    protected SingleLiveEvent<Void> backPressedOf;
    protected SingleLiveEvent<StartActivityComponent> startActivityOf;
    protected SingleLiveEvent<SlcActivityResult> fillResultOf;
    private boolean isRegister;

    /**
     * 注册LiveEvent，数据都是来自Vm，以保证数据发射源唯一
     * 个人角色这种设计不是很好，目前先这么写
     *
     * @param finishOf
     * @param backPressedOf
     * @param startActivityOf
     * @param fillResultOf
     */
    public final void registerLiveEventFromVm(SingleLiveEvent<Void> finishOf,
                                              SingleLiveEvent<Void> backPressedOf,
                                              SingleLiveEvent<StartActivityComponent> startActivityOf,
                                              SingleLiveEvent<SlcActivityResult> fillResultOf) {
        if (isRegister) {
            Log.w("VmBox", getClass().getSimpleName() + "：已注册此VmBox！！！！！！！！！！！！！！！！");
            return;
        }
        isRegister = true;
        this.finishOf = finishOf;
        this.backPressedOf = backPressedOf;
        this.startActivityOf = startActivityOf;
        this.fillResultOf = fillResultOf;
    }

    protected boolean isRegister() {
        return isRegister;
    }

    /**
     * 销毁界面
     */
    protected void finish() {
        checkLiveEvent(finishOf);
        finishOf.call();
    }

    /**
     * 返回按下
     */
    protected void backPressed() {
        checkLiveEvent(backPressedOf);
        backPressedOf.call();
    }

    protected void startActivity(Class<?> activityClass) {
        startActivity(activityClass, null);
    }

    protected void startActivity(Class<?> activityClass, Bundle bundle) {
        checkLiveEvent(startActivityOf);
        startActivityOf.postValue(new StartActivityComponent(activityClass, bundle));
    }

    protected void fillResult(Bundle bundle) {
        fillResult(SlcActivityResult.createBuilder().setBundle(bundle).build());
    }

    protected void fillResultAndFinish(Bundle bundle) {
        fillResult(SlcActivityResult.createBuilder().setBundle(bundle).setFinish(true).build());
    }

    protected void fillResult(SlcActivityResult slcActivityResult) {
        checkLiveEvent(fillResultOf);
        fillResultOf.postValue(slcActivityResult);
    }

    protected void checkLiveEvent(SingleLiveEvent singleLiveEvent) {
        if (singleLiveEvent == null) {
            throw new NullPointerException("singleLiveEvent为空，请注册本VmBox");
        }
    }

    /**
     * 清除
     */
    public void clear() {
        finishOf = null;
        backPressedOf = null;
        startActivityOf = null;
        fillResultOf = null;
    }
}
