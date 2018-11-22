package net.cacheux.crud.example.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import net.cacheux.crud.CrudProvider;
import net.cacheux.crud.CrudViewModel;
import net.cacheux.crud.example.model.Customer;
import net.cacheux.crud.example.storage.CustomerList;

import java.util.List;

public class CustomerViewModel extends CrudViewModel<Customer> {
    private CustomerList customerList = CustomerList.getInstance();
    private MutableLiveData<List<Customer>> customerLiveData = new MutableLiveData<>();

    public CustomerViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected CrudProvider<Customer> getProvider(final Context context) {
        return new CrudProvider<Customer>() {
            @Override
            public LiveData<List<Customer>> readAll() {
                customerLiveData.setValue(customerList.getContent());
                return customerLiveData;
            }

            @Override
            public void create(@NonNull final Customer item) {
                customerList.add(item);
                customerLiveData.setValue(customerList.getContent());
            }

            @Override
            public void update(@NonNull final Customer item) {
                customerList.update(item);
                customerLiveData.setValue(customerList.getContent());
            }

            @Override
            public void delete(@NonNull final Customer item) {
                customerList.delete(item);
                customerLiveData.setValue(customerList.getContent());
            }
        };
    }
}
