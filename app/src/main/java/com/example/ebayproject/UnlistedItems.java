package com.example.ebayproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

public class UnlistedItems extends AppCompatActivity {

    ItemDB itemDB;
    RecyclerView recyclerView;

    String name[], desc[], status[], category[];
    double buyPrice[], sellPrice[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlisted_items);

        itemDB = Room.databaseBuilder(getApplicationContext(),
                ItemDB.class, "itemdb").fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.unlistedRecyclerView);

        //Fill variables with database items
        name = itemDB.itemDao().getItemNames();
        desc = itemDB.itemDao().getItemDesc();
        status = itemDB.itemDao().getItemStatus();
        category = itemDB.itemDao().getItemCategory();
        buyPrice = itemDB.itemDao().getItemBuyPrice();
        sellPrice = itemDB.itemDao().getItemSellPrice();

        UnlistedAdapter unlistedAdapter = new UnlistedAdapter(this, name, desc, status, buyPrice, sellPrice, category);
        recyclerView.setAdapter(unlistedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
