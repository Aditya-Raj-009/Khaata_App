package com.example.khaata.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.khaata.viewModel.ExpenseDetailsConverter;

import java.util.ArrayList;

@Entity(tableName = "ExpenseList")
@TypeConverters(ExpenseDetailsConverter.class)
public class ExpenseList implements Parcelable {

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

    @ColumnInfo(name = "Transaction Date")
    private String tDate;

    protected ExpenseList(Parcel in) {
        id = in.readLong();
        total = in.readDouble();
        title = in.readString();
        description = in.readString();
        tDate = in.readString();
    }

    public static final Creator<ExpenseList> CREATOR = new Creator<ExpenseList>() {
        @Override
        public ExpenseList createFromParcel(Parcel in) {
            return new ExpenseList(in);
        }

        @Override
        public ExpenseList[] newArray(int size) {
            return new ExpenseList[size];
        }
    };

    public String getTDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }

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

    public ExpenseList(ArrayList<ExpenseDetails> expList, String tDate, long id, double total,String title,String description) {
        this.expList = expList;
        this.id = id;
        this.total = total;
        this.title = title;
        this.tDate = tDate;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeDouble(total);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(tDate);
    }
}
