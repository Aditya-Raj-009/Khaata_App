package com.example.khaata.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ExpenseDetails implements Parcelable {

    private String itemName;
    private String ItemDesc="-";

    private double price;

    protected ExpenseDetails(Parcel in) {
        itemName = in.readString();
        ItemDesc = in.readString();
        price = in.readDouble();
    }

    public static final Creator<ExpenseDetails> CREATOR = new Creator<ExpenseDetails>() {
        @Override
        public ExpenseDetails createFromParcel(Parcel in) {
            return new ExpenseDetails(in);
        }

        @Override
        public ExpenseDetails[] newArray(int size) {
            return new ExpenseDetails[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public String getItemDesc() {
        return ItemDesc;
    }

    public void setItemDesc(String desc) {
        this.ItemDesc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public ExpenseDetails() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(itemName);
        parcel.writeString(ItemDesc);
        parcel.writeDouble(price);
    }
}
