package net.cacheux.crud.example.room.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import net.cacheux.crud.simple.SimpleEditViewBinder;
import net.cacheux.crud.simple.SimpleItem;

/**
 * Customer only have its id (not editable) and a String field, so we can use the {@link SimpleItem}
 * interface to be usable with the {@link SimpleEditViewBinder}
 */
@Entity(tableName = "customers")
public class Customer implements SimpleItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Override
    public void setValue(String value) {
        this.name = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
