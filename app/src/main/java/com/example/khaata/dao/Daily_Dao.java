package com.example.khaata.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.khaata.entity.ExpenseDetails;
import com.example.khaata.entity.ExpenseList;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface Daily_Dao {

    @Insert
    long insertData(ExpenseList expenseList);


    @Delete
    void deleteData(ExpenseList expenseList);



    @Query("SELECT * FROM ExpenseList ORDER BY ID DESC")
    List<ExpenseList> getAllExpense();

}
