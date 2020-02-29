package android.slc.code.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * @author slc
 * @date 2020/2/29 1
 */
public class CreateViewAuxiliaryBox {
    private Object layoutObj;
    private LayoutInflater inflater;
    @Nullable
    private ViewGroup container;
    @Nullable
    private Bundle savedInstanceState;

    public CreateViewAuxiliaryBox(Object layoutObj, @Nullable Bundle savedInstanceState) {
        this.layoutObj = layoutObj;
        this.savedInstanceState = savedInstanceState;
    }

    public CreateViewAuxiliaryBox(Object layoutObj, LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.layoutObj = layoutObj;
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
    }

    public Object getLayoutObj() {
        return layoutObj;
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
