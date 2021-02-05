package android.slc.code.ui.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
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
