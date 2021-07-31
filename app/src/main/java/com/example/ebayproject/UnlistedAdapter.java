package com.example.ebayproject;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UnlistedAdapter extends RecyclerView.Adapter<UnlistedAdapter.UnlistedViewHolder> {

    String name[], desc[], status[], category[];
    double buyPrice[], sellPrice[];
    Context context;

    public UnlistedAdapter(Context ct, String itemName[], String itemDesc[], String itemStatus[],
                           double itemBuyPrice[], double itemSellPrice[], String itemCategory[]){
        context = ct;
        name = itemName;
        desc = itemDesc;
        status = itemStatus;
        buyPrice = itemBuyPrice;
        sellPrice = itemSellPrice;
        category = itemCategory;

    }
    @NonNull
    @Override
    public UnlistedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.unlisted_row, parent, false);
        return new UnlistedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnlistedViewHolder holder, int position) {
        holder.titleText.setText(name[position]);
        holder.descText.setText(desc[position]);
        holder.boughtForText.setText(String.valueOf(buyPrice[position]));
        holder.sellForText.setText(String.valueOf(sellPrice[position]));
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class UnlistedViewHolder extends RecyclerView.ViewHolder{

        TextView titleText, descText, boughtForText, sellForText;

        public UnlistedViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.txtName);
            descText = itemView.findViewById(R.id.txtDesc);
            boughtForText = itemView.findViewById(R.id.txtBoughtFor);
            sellForText = itemView.findViewById(R.id.txtSellingFor);
        }
    }
}
