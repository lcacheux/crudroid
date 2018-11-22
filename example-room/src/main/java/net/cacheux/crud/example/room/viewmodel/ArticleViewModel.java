package net.cacheux.crud.example.room.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import net.cacheux.crud.CrudProvider;
import net.cacheux.crud.CrudViewModel;
import net.cacheux.crud.example.room.database.ArticleDao;
import net.cacheux.crud.example.room.database.ExampleDatabase;
import net.cacheux.crud.example.room.model.Article;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ArticleViewModel extends CrudViewModel<Article> {
    public ArticleViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected CrudProvider<Article> getProvider(final Context context) {
        return new CrudProvider<Article>() {
            private ArticleDao dao = ExampleDatabase.getInstance(context).articleDao();
            private Executor executor = Executors.newSingleThreadExecutor();

            @Override
            public LiveData<List<Article>> readAll() {
                return dao.readAll();
            }

            @Override
            public void create(@NonNull final Article item) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        dao.create(item);
                    }
                });
            }

            @Override
            public void update(@NonNull final Article item) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        dao.update(item);
                    }
                });

            }

            @Override
            public void delete(@NonNull final Article item) {
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
