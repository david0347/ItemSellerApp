package com.example.ebayproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Items.class}, version = 1)
public abstract class ItemDB extends RoomDatabase {
    public abstract ItemDao itemDao();
}
