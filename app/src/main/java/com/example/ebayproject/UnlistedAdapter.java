package com.example.ebayproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UnlistedViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UnlistedViewHolder extends RecyclerView.ViewHolder{
        public UnlistedViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
