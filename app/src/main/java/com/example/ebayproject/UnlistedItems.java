package com.example.ebayproject;

import androidx.appcompat.app.AppCompatActivity;
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

        int count;

        count = itemDB.itemDao().tableSize();
        Toast.makeText(UnlistedItems.this, String.valueOf(count), Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.unlistedRecyclerView);


    }
}
