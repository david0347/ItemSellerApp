package com.example.ebayproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ItemDao {

    @Insert
    void addItem(Items items);

    @Query("SELECT * FROM Items WHERE :status LIKE itemStatus")
    Items[] getItemFromStatus(String status);

    @Query("SELECT * FROM Items WHERE :id = itemID")
    Items getItemByID(int id);

    @Query("SELECT * FROM Items WHERE :name LIKE itemName")
    Items getItemByName(String name);

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

    @Query("SELECT itemID FROM Items")
    int[] getItemID();

    @Query("UPDATE Items SET itemStatus = 'listed' WHERE :id LIKE itemID")
    void updateItemStatusToListed(int id);

    @Query("UPDATE Items SET itemStatus = 'sold' WHERE :id LIKE itemID")
    void updateItemStatusToSold(int id);

    @Query("DELETE FROM Items WHERE :id LIKE itemID")
    void deleteSingleItem(int id);

    @Query("DELETE FROM Items")
    void deleteAllItems();
}
