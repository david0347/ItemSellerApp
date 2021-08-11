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

    @Query("SELECT itemBuyPrice FROM Items WHERE :id LIKE itemID")
    double getItemBuyPrice(int id);

    @Query("SELECT itemSellPrice FROM Items WHERE :id LIKE itemID")
    double getItemSellPrice(int id);

    @Query("SELECT itemID FROM Items")
    int[] getItemID();

    @Query("UPDATE Items SET itemName = :newName WHERE :id LIKE itemID")
    void updateItemName(String newName, int id);

    @Query("UPDATE Items SET itemDesc = :newDesc WHERE :id LIKE itemID")
    void updateItemDesc(String newDesc, int id);

    @Query("UPDATE Items SET itemBuyPrice = :newBuyPrice WHERE :id LIKE itemID")
    void updateItemBuyPrice(String newBuyPrice, int id);

    @Query("UPDATE Items SET itemSellPrice = :newSellPrice WHERE :id LIKE itemID")
    void updateSellPrice(String newSellPrice, int id);

    @Query("UPDATE Items SET itemCategory = :newCategory WHERE :id LIKE itemID")
    void updateCategory(String newCategory, int id);

    @Query("UPDATE Items SET itemStatus = 'listed' WHERE :id LIKE itemID")
    void updateItemStatusToListed(int id);

    @Query("UPDATE Items SET itemStatus = 'sold' WHERE :id LIKE itemID")
    void updateItemStatusToSold(int id);

    @Query("DELETE FROM Items WHERE :id LIKE itemID")
    void deleteSingleItem(int id);

    @Query("DELETE FROM Items")
    void deleteAllItems();
}
