package android.slc.code.ui.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * 此方法只是为了使用{@link BaseViewDelegate}时传参更统一，方便{@link android.app.Activity}和{@link androidx.fragment.app.Fragment}互转更方便
 *
 * @author slc
 * @date 2020/2/29 1
 */
public class CreateViewAuxiliaryBox {
    private LayoutInflater inflater;
    @Nullable
    private ViewGroup container;
    @Nullable
    private final Bundle savedInstanceState;

    public CreateViewAuxiliaryBox(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    public CreateViewAuxiliaryBox(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    @Nullable
    public ViewGroup getContainer() {
        return container;
    }

    @Nullable
    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }
}
