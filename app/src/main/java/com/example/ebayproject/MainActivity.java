package com.example.ebayproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToUnlisted(View view){
        Intent intent = new Intent(this, UnlistedItems.class);
        startActivity(intent);
    }

    public void goToAddItems(View view){
        Intent intent = new Intent(this, AddItems.class);
        startActivity(intent);
    }

    public void goToListedItems(View view){
        Intent intent = new Intent(this, ListedItems.class);
        startActivity(intent);
    }

    public void goToSoldItems(View view){
        Intent intent = new Intent(this, SoldItems.class);
        startActivity(intent);
    }

    public void goToEdit(View view){
        //Intent intent = new Intent(this, EditItems.class);
        //intent.putExtra("activity", 0);
        //startActivity(intent);
    }
}
