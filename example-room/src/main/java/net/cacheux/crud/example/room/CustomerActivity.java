package net.cacheux.crud.example.room;

import android.os.Bundle;

import net.cacheux.crud.CrudActivity;
import net.cacheux.crud.CrudListAdapter;
import net.cacheux.crud.EditViewBinder;
import net.cacheux.crud.simple.SimpleEditViewBinder;
import net.cacheux.crud.simple.SimpleListAdapter;
import net.cacheux.crud.example.room.model.Customer;
import net.cacheux.crud.example.room.viewmodel.CustomerViewModel;

/**
 * This is the simplest possible implementation of a {@link CrudActivity}, using
 * {@link SimpleListAdapter} and {@link SimpleEditViewBinder} as implementations for list items
 * and edit view.
 * <p>
 * This is possible because {@link Customer} have only one editable string field.
 */
public class CustomerActivity extends CrudActivity<Customer> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.customers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public CrudListAdapter<Customer> getListAdapter() {
        return new SimpleListAdapter<>(this);
    }

    @Override
    public EditViewBinder<Customer> getEditViewBinder() {
        return new SimpleEditViewBinder<>(this, Customer.class);
    }

    @Override
    public Class getViewModelClass() {
        return CustomerViewModel.class;
    }
}
