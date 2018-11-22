package net.cacheux.crud.example.room.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import net.cacheux.crud.example.room.model.Article;

import java.util.List;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM articles")
    LiveData<List<Article>> readAll();

    @Insert
    void create(Article article);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);
}
