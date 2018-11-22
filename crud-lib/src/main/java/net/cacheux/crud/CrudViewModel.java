package net.cacheux.crud;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * An implementation of {@link AndroidViewModel} with methods for the CRUD operations.
 * @param <T>
 */
public abstract class CrudViewModel<T> extends AndroidViewModel {
    private CrudProvider<T> provider;

    public CrudViewModel(@NonNull Application application) {
        super(application);
        this.provider = getProvider(application);
    }

    public LiveData<List<T>> readAll() {
        return provider.readAll();
    }

    void create(@NonNull T key) {
        provider.create(key);
    }

    void update(@NonNull T key) {
        provider.update(key);
    }

    void delete(@NonNull T key) {
        provider.delete(key);
    }

    /**
     * Generate the {@link CrudProvider} instance which will be used for all CRUD operations.
     * @return
     */
    protected abstract CrudProvider<T> getProvider(Context context);
}
