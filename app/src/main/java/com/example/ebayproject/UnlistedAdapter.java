package com.example.ebayproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class UnlistedAdapter extends RecyclerView.Adapter<UnlistedAdapter.UnlistedViewHolder> {

    //Initialize variables that go into the recycler
    String name[], desc[], status[], category[];
    double buyPrice[], sellPrice[];
    int id[];
    Context context;
    ItemDB itemDB;

    public UnlistedAdapter(Context ct, String itemName[], String itemDesc[],
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

    //Put the card in view
    @NonNull
    @Override
    public UnlistedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.unlisted_row, parent, false);
        return new UnlistedViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UnlistedViewHolder holder, int position) {
        //Fill the recycler view with variables
        holder.titleText.setText(name[position]);
        holder.descText.setText(desc[position]);
        holder.boughtForText.setText(String.valueOf(buyPrice[position]));
        holder.sellForText.setText(String.valueOf(sellPrice[position]));
        holder.position = position;

    }

    //Number of recycler elements
    @Override
    public int getItemCount() {
        return name.length;
    }

    public class UnlistedViewHolder extends RecyclerView.ViewHolder{

        TextView titleText, descText, boughtForText, sellForText;
        int position;


        public UnlistedViewHolder(@NonNull final View itemView) {
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


                    //update database to make status listed
                    itemDB.itemDao().updateItemStatusToListed(itemID);
                    //Jump to main page
                    Intent intent = new Intent(itemView.getContext(), MainActivity.class);
                    itemView.getContext().startActivity(intent);


                    Toast.makeText(itemView.getContext(), "Successfully Listed " + name[position], Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
