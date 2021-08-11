package com.example.ebayproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class AddItems extends AppCompatActivity {
    public static ItemDB itemDB;
    public static DecimalFormat df = new DecimalFormat ("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);


        //Creates item database instance
        itemDB = Room.databaseBuilder(getApplicationContext(),
                ItemDB.class, "itemdb").fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public void goToMainActivity(View view){
        finish();
    }

    public void addItem(View view){
        //Should find a better way to make these global
        EditText itemName = findViewById(R.id.etxtItemName);
        EditText itemDesc = findViewById(R.id.etxtItemDesc);
        EditText itemBuy = findViewById(R.id.etxtBuyPrice);
        EditText itemSell = findViewById(R.id.etxtAskingPrice);
        Spinner category = findViewById(R.id.spnrCategories);

        //Initialize variables
        double[] prices;
        double boughtFor, sellFor;
        Items newItem = new Items();

        //If the values aren't null, continue checking
        if(attributesHaveValue()){
            prices = parsingDoubles(itemBuy.getText().toString(), itemSell.getText().toString());
            boughtFor = Double.parseDouble(df.format(prices[0]));
            sellFor = Double.parseDouble(df.format(prices[1]));

            //If values are greater than 0, create object parameters
            if(boughtFor > 0 || sellFor > 0){
                newItem.setCategory(category.getSelectedItem().toString().trim());
                newItem.setName(itemName.getText().toString().trim());
                newItem.setDesc(itemDesc.getText().toString().trim());
                newItem.setStatus("unlisted");
                newItem.setBuyPrice(boughtFor);
                newItem.setSellPrice(sellFor);

                itemDB.itemDao().addItem(newItem);


                //Running total of new unlisted items for statistics
                SharedPreferences unlistedItemPreference = this.getSharedPreferences("com.example.statistics", Context.MODE_PRIVATE);
                int totalUnlisted = unlistedItemPreference.getInt("unlistedItem", 0);
                totalUnlisted+=1;
                unlistedItemPreference.edit().putInt("unlistedItem", totalUnlisted).apply();

                //Print successful
                Toast.makeText(AddItems.this, "Added Item", Toast.LENGTH_SHORT).show();

                //Go back to main
                finish();

                //Else print out error message and reset bought/sell prices
            }else{
                Toast.makeText(AddItems.this, "Enter numerical values", Toast.LENGTH_SHORT).show();
                itemBuy.setText("");
                itemSell.setText("");
            }
        }
    }

    public boolean attributesHaveValue(){
        //Set editText values
        EditText itemName = findViewById(R.id.etxtItemName);
        EditText itemDesc = findViewById(R.id.etxtItemDesc);
        EditText itemBuy = findViewById(R.id.etxtBuyPrice);
        EditText itemSell = findViewById(R.id.etxtAskingPrice);

        //If any field is empty return a toast indicating empty field and return false
        if(itemName.getText().toString().equals("")){
            Toast.makeText(AddItems.this, "Give the item a name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(itemDesc.getText().toString().equals("")){
            Toast.makeText(AddItems.this, "Give the item a description", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(itemBuy.getText().toString().equals("")){
            Toast.makeText(AddItems.this, "Set the price you bought the item for", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(itemSell.getText().toString().equals("")){
            Toast.makeText(AddItems.this, "Set the price you are selling it for", Toast.LENGTH_SHORT).show();
            return false;
        }
        //If values are full return true
        return true;
    }

    //Check if strings can be parsed
    public double[] parsingDoubles(String buyPrice, String sellingPrice){
        double parsedDoubles[] = {-1,-1};

        //check buyPrice
        try{
            parsedDoubles[0] = Double.parseDouble(buyPrice);
        }catch(Exception e){

        }
        //check askingPrice
        try{
            parsedDoubles[1] = Double.parseDouble(sellingPrice);
        }catch(Exception e){

        }
        return parsedDoubles;
    }
}
