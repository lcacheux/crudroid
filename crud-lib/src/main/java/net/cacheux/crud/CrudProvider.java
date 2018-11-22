package net.cacheux.crud;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * This interface is the link between the UI and the data source. Be aware that most implementations
 * which implies storage and/or network access can be blocking and must not be executed in the main
 * thread.
 * @param <T>
 */
public interface CrudProvider<T> {
    /**
     * This method must return the whole list of objects as a LiveData.
     * @return
     */
    LiveData<List<T>> readAll();

    /**
     * This method will insert a new object into your data source.
     * @param item
     */
    void create(@NonNull T item);

    /**
     * This method will update an existing object from your data source. Ensure that your T object
     * declare a field with a unique id so it can be retrieved properly.
     * @param item
     */
    void update(@NonNull T item);

    /**
     * This method will delete an existing object from your data source. Ensure that your T object
     * declare a field with a unique id so it can be retrieved properly.
     * @param item
     */
    void delete(@NonNull T item);
}
