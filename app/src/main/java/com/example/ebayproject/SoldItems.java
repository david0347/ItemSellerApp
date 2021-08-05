package com.example.ebayproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

public class SoldItems extends AppCompatActivity {

    ItemDB itemDB;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_items);

        itemDB = Room.databaseBuilder(getApplicationContext(),
                ItemDB.class, "itemdb").fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.unlistedRecyclerView);

        //Initialize and set Items array with every item with "unlisted" in status
        Items soldStatus[];
        soldStatus = itemDB.itemDao().getItemFromStatus("sold");

        //Get length of array so we can loop
        int length = soldStatus.length;

        //Initialize all variables
        String name[] = new String[length];
        String desc[] = new String[length];
        String category[] = new String[length];
        double buyPrice[] = new double[length];
        double sellPrice[] = new double[length];
        int itemID[] = new int[length];


        //For loop to populate arrays for "unlisted" items
        for(int i = 0; i<soldStatus.length; i++){
            name[i] = soldStatus[i].getName();
            desc[i] = soldStatus[i].getDesc();
            category[i] = soldStatus[i].getCategory();
            buyPrice[i] = soldStatus[i].getBuyPrice();
            sellPrice[i] = soldStatus[i].getSellPrice();
            itemID[i] = soldStatus[i].getItemID();
        }

        //Get recycler set up with adapter
        SoldAdapter soldAdapter = new SoldAdapter(this, name, desc, buyPrice, sellPrice, category, itemID);
        recyclerView.setAdapter(soldAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
