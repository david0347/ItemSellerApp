package com.example.ebayproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

public class UnlistedItems extends AppCompatActivity {

    ItemDB itemDB;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlisted_items);

        itemDB = Room.databaseBuilder(getApplicationContext(),
                ItemDB.class, "itemdb").fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.unlistedRecyclerView);

        //Initialize and set Items array with every item with "unlisted" in status
        Items unlistedStatus[];
        unlistedStatus = itemDB.itemDao().getItemFromStatus("unlisted");

        //Get length of array so we can loop
        int length = unlistedStatus.length;

        //Initialize all variables
        String name[] = new String[length];
        String desc[] = new String[length];
        String category[] = new String[length];
        double buyPrice[] = new double[length];
        double sellPrice[] = new double[length];
        int itemID[] = new int[length];


        //For loop to populate arrays for "unlisted" items
        for(int i = 0; i<unlistedStatus.length; i++){
            name[i] = unlistedStatus[i].getName();
            desc[i] = unlistedStatus[i].getDesc();
            category[i] = unlistedStatus[i].getCategory();
            buyPrice[i] = unlistedStatus[i].getBuyPrice();
            sellPrice[i] = unlistedStatus[i].getSellPrice();
            itemID[i] = unlistedStatus[i].getItemID();
        }

        //Get recycler set up with adapter
        UnlistedAdapter unlistedAdapter = new UnlistedAdapter(this, name, desc, buyPrice, sellPrice, category, itemID);
        recyclerView.setAdapter(unlistedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
