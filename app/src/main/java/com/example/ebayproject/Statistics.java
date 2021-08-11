package com.example.ebayproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Statistics extends AppCompatActivity {

    ItemDB itemDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //Decimal format instance
        DecimalFormat df = new DecimalFormat("#,###,##0.00");

        //Create shared preference instance
        SharedPreferences items = this.getSharedPreferences("com.example.statistics", Context.MODE_PRIVATE);

        //Get item instances
        TextView txtUnlistedItems = findViewById(R.id.txtItemsUnlisted);
        TextView txtListedItems = findViewById(R.id.txtItemsListed);
        TextView txtSoldItems = findViewById(R.id.txtItemsSold);
        TextView txtTotalRevenue = findViewById(R.id.txtTotalRevenue);
        TextView txtTotalProfit = findViewById(R.id.txtTotalProfit);

        //Set values
        int totalUnlistedItems = items.getInt("unlistedItem", 0);
        int totalListedItems = items.getInt("listedItem", 0);
        int totalSoldItems = items.getInt("soldItem", 0);
        float totalCost = items.getFloat("totalCost", 0);
        float totalRevenue = items.getFloat("totalRevenue", 0);
        txtUnlistedItems.setText(String.valueOf(totalUnlistedItems));
        txtListedItems.setText(String.valueOf(totalListedItems));
        txtSoldItems.setText(String.valueOf(totalSoldItems));
        txtTotalRevenue.setText("$" + df.format(totalRevenue));
        txtTotalProfit.setText("$" + df.format(totalRevenue - totalCost));


    }

    public void goToMain(View view){
        finish();
    }

    public void resetData(View view){
        itemDB = Room.databaseBuilder(getApplicationContext(),
                ItemDB.class, "itemdb").fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();



        new AlertDialog.Builder(this)
                .setTitle("Reset All Data")
                .setMessage("Would You Like To Reset All Data?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Get shared preference and reset all data
                        SharedPreferences items = getSharedPreferences("com.example.statistics", Context.MODE_PRIVATE);
                        items.edit().putInt("unlistedItem", 0).apply();
                        items.edit().putInt("listedItem", 0).apply();
                        items.edit().putInt("soldItem", 0).apply();
                        items.edit().putFloat("totalCost", 0).apply();
                        items.edit().putFloat("totalRevenue", 0).apply();

                        //Reset database and then print success message
                        itemDB.itemDao().deleteAllItems();
                        Toast.makeText(Statistics.this, "Successfully Reset All Data", Toast.LENGTH_SHORT).show();

                        //Go back to Main Menu
                        finish();
                    }
                })
                .setNegativeButton("No", null).show();
    }
}
