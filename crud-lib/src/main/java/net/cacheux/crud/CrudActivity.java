package net.cacheux.crud;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import net.cacheux.crud.simple.SimpleEditViewBinder;
import net.cacheux.crud.simple.SimpleListAdapter;

/**
 * Base class to create a CRUD activity (create/read/update/delete).
 * <p>
 * An implementation of CrudActivity must be associated to a model class which represent an object
 * we want to list/create/update/delete. It will usually be a simple POJO object with an unique id
 * and some data fields.
 * <p>
 * The activity will be composed of two fragments : one with the list of objects (default view),
 * one with a creation/edition form.
 * // TODO write more
 * @param <T>
 */
public abstract class CrudActivity<T> extends AppCompatActivity {

    private CrudViewModel<T> viewModel;
    private CrudListFragment<T> crudListFragment;
    private CrudEditFragment<T> crudEditFragment;

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        crudListFragment = new CrudListFragment<>();
        crudEditFragment = new CrudEditFragment<>();

        viewModel = ViewModelProviders.of(this).get(getViewModelClass());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.crud_content, crudListFragment);
        transaction.commit();
    }

    public CrudViewModel<T> getViewModel() {
        return viewModel;
    }

    /**
     * This method must be overrided to provide a custom adapter for the list fragment. Simple lists
     * with only a string to display per element can consider {@link SimpleListAdapter} as an
     * implementation.
     *
     * @return
     */
    public abstract CrudListAdapter<T> getListAdapter();

    /**
     * This method must be overrided to provide a custom EditViewBinder implementation which will
     * create the edition form view. Simple types with only one text field can consider
     * {@link SimpleEditViewBinder} as an implementation.
     *
     * @return
     */
    public abstract EditViewBinder<T> getEditViewBinder();

    /**
     * This method must be overrided and return an implementation class of {@link CrudViewModel}.
     *
     * @return
     */
    public abstract Class<CrudViewModel> getViewModelClass();

    /**
     * Get the last long-clicked item.
     * @return
     */
    public T getCurrentItem() {
        return crudListFragment.getCurrentItem();
    }

    void showAddItemDialog() {
        crudEditFragment.show(getSupportFragmentManager());
    }

    void showEditItemDialog(T item) {
        crudEditFragment.show(getSupportFragmentManager(), item);
    }

    /**
     * This method can be overrided to add other actions on item creation.
     * @param item
     */
    @CallSuper
    public void createItem(@NonNull T item) {
        viewModel.create(item);
    }

    /**
     * This method can be overrided to add other actions on item edition.
     * @param item
     */
    @CallSuper
    public void updateItem(@NonNull T item) {
        viewModel.update(item);
    }

    /**
     * This method can be overrided to add other actions on item deletion.
     * @param item
     */
    @CallSuper
    public void deleteItem(@NonNull T item) {
        viewModel.delete(item);
    }
}
