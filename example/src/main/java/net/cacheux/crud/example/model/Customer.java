package net.cacheux.crud.example.model;

import android.support.annotation.NonNull;

import net.cacheux.crud.example.storage.BaseItem;
import net.cacheux.crud.simple.SimpleEditViewBinder;
import net.cacheux.crud.simple.SimpleItem;

/**
 * Customer only have its id (not editable) and a String field, so we can use the {@link SimpleItem}
 * interface to be usable with the {@link SimpleEditViewBinder}
 */
public class Customer extends BaseItem implements SimpleItem {

    @NonNull
    private String name;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Override
    public void setValue(String value) {
        this.name = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
