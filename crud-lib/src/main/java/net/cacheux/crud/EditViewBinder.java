package net.cacheux.crud;

import android.view.View;

/**
 * An interface to generate the content layout of the add/edit dialog and bind values from/to the
 * associated item.
 * @param <T>
 */
public interface EditViewBinder<T> {
    /**
     * Generate the content layout of the add/edit dialog.
     * @return
     */
    View getLayout();

    /**
     * Update the content layout with values from the T item.
     * @param view
     * @param item
     */
    void bindToView(View view, T item);

    /**
     * Update the T item values with data from the content layout.
     * @param view
     * @param item
     */
    void updateFromView(View view, T item);

    /**
     * Create a new instance of T. Most implementations will just call the default constructor.
     * @return
     */
    T createNew();
}
