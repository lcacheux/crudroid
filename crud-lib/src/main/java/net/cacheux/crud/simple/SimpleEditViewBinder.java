package net.cacheux.crud.simple;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.cacheux.crud.EditViewBinder;

/**
 * A simple EditViewBinder for items with a single String attribute. The object must implement the
 * {@link SimpleItem} interface, which will be called when its value is set.
 * @param <T>
 */
public class SimpleEditViewBinder<T extends SimpleItem> implements EditViewBinder<T> {
    private Context context;
    private final Class<T> tClass;

    public SimpleEditViewBinder(Context context, Class<T> tClass) {
        this.context = context;
        this.tClass = tClass;
    }

    @Override
    public View getLayout() {
        return new EditText(context);
    }

    @Override
    public void bindToView(View view, T item) {
        ((EditText) view).setText(item.toString());
    }

    @Override
    public void updateFromView(View view, T item) {
        item.setValue(((TextView) view).getText().toString());
    }

    @Override
    public T createNew() {
        try {
            return tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(
                tClass.toString() + " must have a default public constructor. Create it"
                    + " or override the createNew() method to avoid this exception.");
        }
    }
}
