package com.example.ebayproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Items {


    //attributes
    @PrimaryKey(autoGenerate = true)
    private int itemID;

    @ColumnInfo(name ="itemName")
    private String name;

    @ColumnInfo(name = "itemDesc")
    private String desc;

    @ColumnInfo(name = "itemStatus")
    private String status;

    @ColumnInfo(name = "itemBuyPrice")
    private String buyPrice;

    @ColumnInfo(name = "itemSellPrice")
    private String sellPrice;

    @ColumnInfo(name = "itemCategory")
    private String category;


    //setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //Empty Constructor
    public Items(){};

}
