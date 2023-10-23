package com.example.khaata.entity;


public class ExpenseDetails {

    private String itemName;
    private String ItemDesc="-";

    private double price;

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




}
