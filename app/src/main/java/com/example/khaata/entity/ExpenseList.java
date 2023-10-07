package com.example.khaata.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.khaata.viewModel.ExpenseDetailsConverter;

import java.util.ArrayList;

@Entity(tableName = "ExpenseList")
@TypeConverters(ExpenseDetailsConverter.class)
public class ExpenseList {

    @ColumnInfo(name = "ExpList")
   private ArrayList<ExpenseDetails> expList;

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "Total")
    private double total;
    @ColumnInfo(name = "Title")
    private String title;

    @ColumnInfo(name = "Description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExpenseList(ArrayList<ExpenseDetails> expList, long id, double total,String title,String description) {
        this.expList = expList;
        this.id = id;
        this.total = total;
        this.title = title;
        this.description = description;
    }

    public ArrayList<ExpenseDetails> getExpList() {
        return expList;
    }

    public void setExpList(ArrayList<ExpenseDetails> expList) {
        this.expList = expList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Ignore
    public ExpenseList() {
    }
}
