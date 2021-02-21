package android.slc.code.vm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.slc.commonlibrary.util.compat.SlcBarCompatUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;

/**
 * 状态栏代理
 *
 * @author slc
 * @date 2021/2/5 14:36
 */
public class BarVm extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    protected AppCompatActivity mActivity;
    public final ObservableBoolean barLightMode = new ObservableBoolean();

    public BarVm(@NonNull Application application) {
        super(application);
    }

    public void init(AppCompatActivity activity) {
        if (this.mActivity == null) {
            this.mActivity = activity;
            barLightMode.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    syncBarStyle();
                }
            });
            syncBarStyle();
        }
    }

    /**
     * 同步syncBarStyle
     */
    public void syncBarStyle() {
        SlcBarCompatUtils.setStatusBarLightMode(mActivity, this.barLightMode.get());
    }

}
