package net.cacheux.crud.example.room.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import net.cacheux.crud.example.room.model.Customer;

import java.util.List;

@Dao
public interface CustomerDao {
    @Query("SELECT * FROM customers")
    LiveData<List<Customer>> readAll();

    @Insert
    void create(Customer customer);

    @Update
    void update(Customer customer);

    @Delete
    void delete(Customer customer);
}
