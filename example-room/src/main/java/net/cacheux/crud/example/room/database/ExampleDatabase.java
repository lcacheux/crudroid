package net.cacheux.crud.example.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import net.cacheux.crud.example.room.model.Article;
import net.cacheux.crud.example.room.model.Customer;

@Database(entities = { Article.class, Customer.class },
    version = 1, exportSchema = false)
public abstract class ExampleDatabase extends RoomDatabase {
    private static ExampleDatabase instance;

    public static ExampleDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ExampleDatabase.class, "example.db")
                .build();
        }
        return instance;
    }

    public abstract ArticleDao articleDao();
    public abstract CustomerDao customerDao();
}
