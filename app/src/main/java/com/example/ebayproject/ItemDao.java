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

    @Query("SELECT COUNT(*) FROM ITEMS")
    int tableSize();

    @Query("SELECT itemName FROM Items")
    String[] getItemNames();

    @Query("SELECT itemDesc FROM Items")
    String[] getItemDesc();

    @Query("SELECT itemStatus FROM Items")
    String[] getItemStatus();

    @Query("SELECT itemCategory FROM Items")
    String[] getItemCategory();

    @Query("SELECT itemBuyPrice FROM Items")
    double[] getItemBuyPrice();

    @Query("SELECT itemSellPrice FROM Items")
    double[] getItemSellPrice();

    @Query("DELETE FROM Items")
    void deleteAllItems();
}
