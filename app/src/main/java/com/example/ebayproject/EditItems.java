package com.example.ebayproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditItems extends AppCompatActivity {

    ItemDB itemDB;
    Items editedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        //get data from recycler view
        int id = getIntent().getIntExtra("itemID", -1);

        EditText txtItemName = findViewById(R.id.etxtItemName);
        EditText txtItemDesc = findViewById(R.id.etxtItemDesc);
        EditText txtBoughtFor = findViewById(R.id.etxtBoughtFor);
        EditText txtSellingFor = findViewById(R.id.etxtSoldFor);
        Spinner spnrCategory = findViewById(R.id.spnrCategory);

        //database instance builder
        itemDB = Room.databaseBuilder(getApplicationContext(),
                ItemDB.class, "itemdb").fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        editedItem = itemDB.itemDao().getItemByID(id);

        //Get spinner id set
        int i = 0;
        int spinnerIndex;
        String categoryArray[] = getResources().getStringArray(R.array.items_category);
        while(!categoryArray[i].equals(editedItem.getCategory())){
            i++;
        }
        spinnerIndex = i;

        //Set values in activity
        txtItemName.setText(editedItem.getName());
        txtItemDesc.setText(editedItem.getDesc());
        txtBoughtFor.setText(String.valueOf(editedItem.getBuyPrice()));
        txtSellingFor.setText(String.valueOf(editedItem.getSellPrice()));
        spnrCategory.setSelection(spinnerIndex);

    }

    public void confirm(View view){
        //Get data from values
        EditText txtItemName = findViewById(R.id.etxtItemName);
        EditText txtItemDesc = findViewById(R.id.etxtItemDesc);
        EditText txtBoughtFor = findViewById(R.id.etxtBoughtFor);
        EditText txtSellingFor = findViewById(R.id.etxtSoldFor);
        Spinner spnrCategory = findViewById(R.id.spnrCategory);
        int id = editedItem.getItemID();

        //Update the database
        itemDB.itemDao().updateItemName(txtItemName.getText().toString().trim(), id);
        itemDB.itemDao().updateItemDesc(txtItemDesc.getText().toString().trim(), id);
        itemDB.itemDao().updateItemBuyPrice(txtBoughtFor.getText().toString().trim(), id);
        itemDB.itemDao().updateSellPrice(txtSellingFor.getText().toString().trim(), id);
        itemDB.itemDao().updateCategory(spnrCategory.getSelectedItem().toString(), id);

        //Solves a big problem of returning to last activity
        finish();
    }

    public void cancel(View view){
        //Go back to last activity
        finish();
    }
}
