package net.cacheux.crud.example.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import net.cacheux.crud.CrudProvider;
import net.cacheux.crud.CrudViewModel;
import net.cacheux.crud.example.model.Article;
import net.cacheux.crud.example.storage.ArticleList;

import java.util.List;

public class ArticleViewModel extends CrudViewModel<Article> {
    private ArticleList articleList = ArticleList.getInstance();
    private MutableLiveData<List<Article>> articleLiveData = new MutableLiveData<>();

    public ArticleViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected CrudProvider<Article> getProvider(final Context context) {
        return new CrudProvider<Article>() {
            @Override
            public LiveData<List<Article>> readAll() {
                articleLiveData.setValue(articleList.getContent());
                return articleLiveData;
            }

            @Override
            public void create(@NonNull final Article item) {
                articleList.add(item);
                articleLiveData.setValue(articleList.getContent());
            }

            @Override
            public void update(@NonNull final Article item) {
                articleList.update(item);
                articleLiveData.setValue(articleList.getContent());
            }

            @Override
            public void delete(@NonNull final Article item) {
                articleList.delete(item);
                articleLiveData.setValue(articleList.getContent());
            }
        };
    }
}
