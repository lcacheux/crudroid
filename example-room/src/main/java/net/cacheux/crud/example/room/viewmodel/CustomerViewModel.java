package net.cacheux.crud.example.room.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import net.cacheux.crud.CrudProvider;
import net.cacheux.crud.CrudViewModel;
import net.cacheux.crud.example.room.database.CustomerDao;
import net.cacheux.crud.example.room.database.ExampleDatabase;
import net.cacheux.crud.example.room.model.Customer;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CustomerViewModel extends CrudViewModel<Customer> {
    public CustomerViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected CrudProvider<Customer> getProvider(final Context context) {
        return new CrudProvider<Customer>() {
            private CustomerDao dao = ExampleDatabase.getInstance(context).customerDao();
            private Executor executor = Executors.newSingleThreadExecutor();

            @Override
            public LiveData<List<Customer>> readAll() {
                return dao.readAll();
            }

            @Override
            public void create(@NonNull final Customer item) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        dao.create(item);
                    }
                });
            }

            @Override
            public void update(@NonNull final Customer item) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        dao.update(item);
                    }
                });

            }

            @Override
            public void delete(@NonNull final Customer item) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        dao.delete(item);
                    }
                });
            }
        };
    }
}
