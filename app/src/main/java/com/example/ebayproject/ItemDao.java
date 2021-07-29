package com.example.ebayproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ItemDao {

    @Insert
    void addItem(Items items);

    @Query("SELECT * FROM Items WHERE :status LIKE itemStatus")
    Items getItemFromStatus(String status);

    @Query("DELETE FROM Items")
    void deleteAllItems();
}
