package com.example.ebayproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.text.DecimalFormat;

public class ListedAdapter extends RecyclerView.Adapter<ListedAdapter.ListedViewHolder> {

    //Initialize variables that go into the recycler
    String name[], desc[], status[], category[];
    double buyPrice[], sellPrice[];
    int id[];
    Context context;
    ItemDB itemDB;

    public ListedAdapter(Context ct, String itemName[], String itemDesc[],
                           double itemBuyPrice[], double itemSellPrice[], String itemCategory[], int itemID[]){
        //set the recycler constructor values to the variables
        context = ct;
        name = itemName;
        desc = itemDesc;
        buyPrice = itemBuyPrice;
        sellPrice = itemSellPrice;
        category = itemCategory;
        id = itemID;

    }

    @NonNull
    @Override
    public ListedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listed_row, parent, false);
        return new ListedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListedViewHolder holder, int position) {
        //Format into currency
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        holder.boughtForText.setText("$" + df.format(buyPrice[position]));
        holder.sellForText.setText("$" + df.format(sellPrice[position]));

        //Fill the recycler view with variables
        holder.titleText.setText(name[position]);
        holder.descText.setText(desc[position]);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class ListedViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, descText, boughtForText, sellForText;
        int position;

        public ListedViewHolder(@NonNull final View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.txtName);
            descText = itemView.findViewById(R.id.txtDesc);
            boughtForText = itemView.findViewById(R.id.txtBoughtFor);
            sellForText = itemView.findViewById(R.id.txtSellingFor);

            //Build itemDB
            itemDB = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDB.class, "itemdb").fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();



            //On click listener to move to listed
            itemView.findViewById(R.id.btnList).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemID = id[position];
                    SharedPreferences listedItemsPreference = context.getSharedPreferences("com.example.statistics", Context.MODE_PRIVATE);
                    int totalSold = listedItemsPreference.getInt("soldItem", 0);
                    float totalCost = listedItemsPreference.getFloat("totalCost", 0);
                    float totalRevenue = listedItemsPreference.getFloat("totalRevenue", 0);

                    totalSold+=1;
                    listedItemsPreference.edit().putInt("soldItem", totalSold).apply();

                    totalCost+= itemDB.itemDao().getItemBuyPrice(itemID);
                    listedItemsPreference.edit().putFloat("totalCost", totalCost).apply();

                    totalRevenue+= itemDB.itemDao().getItemSellPrice(itemID);
                    listedItemsPreference.edit().putFloat("totalRevenue", totalRevenue).apply();

                    //update database to make status listed
                    itemDB.itemDao().updateItemStatusToSold(itemID);
                    //Jump to main page
                    Intent intent = new Intent(itemView.getContext(), MainActivity.class);
                    itemView.getContext().startActivity(intent);


                    Toast.makeText(itemView.getContext(), "Successfully Sold " + name[position], Toast.LENGTH_SHORT).show();
                }
            });

            itemView.findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Start intent to send to EditItems, send the ItemID to edit
                    Intent goToEdit = new Intent(itemView.getContext(), EditItems.class);
                    goToEdit.putExtra("itemID", id[position]);
                    goToEdit.putExtra("activity", 2);
                    itemView.getContext().startActivity(goToEdit);
                }
            });
        }
    }
}
