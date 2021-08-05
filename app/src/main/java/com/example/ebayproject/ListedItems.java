package com.example.ebayproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;

public class ListedItems extends AppCompatActivity {

    ItemDB itemDB;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listed_items);

        itemDB = Room.databaseBuilder(getApplicationContext(),
                ItemDB.class, "itemdb").fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.unlistedRecyclerView);

        //Initialize and set Items array with every item with "unlisted" in status
        Items listedStatus[];
        listedStatus = itemDB.itemDao().getItemFromStatus("listed");

        //Get length of array so we can loop
        int length = listedStatus.length;

        //Initialize all variables
        String name[] = new String[length];
        String desc[] = new String[length];
        String category[] = new String[length];
        double buyPrice[] = new double[length];
        double sellPrice[] = new double[length];
        int itemID[] = new int[length];


        //For loop to populate arrays for "unlisted" items
        for(int i = 0; i<listedStatus.length; i++){
            name[i] = listedStatus[i].getName();
            desc[i] = listedStatus[i].getDesc();
            category[i] = listedStatus[i].getCategory();
            buyPrice[i] = listedStatus[i].getBuyPrice();
            sellPrice[i] = listedStatus[i].getSellPrice();
            itemID[i] = listedStatus[i].getItemID();
        }

        //Get recycler set up with adapter
        ListedAdapter listedAdapter = new ListedAdapter(this, name, desc, buyPrice, sellPrice, category, itemID);
        recyclerView.setAdapter(listedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}