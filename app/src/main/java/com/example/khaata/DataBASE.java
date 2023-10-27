package com.example.khaata;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.khaata.dao.Daily_Dao;
import com.example.khaata.entity.ExpenseDetails;
import com.example.khaata.entity.ExpenseList;
import com.example.khaata.viewModel.ExpenseDetailsConverter;

@Database(entities = {ExpenseList.class},version = 1)
@TypeConverters({ExpenseDetailsConverter.class})
public abstract class DataBASE extends RoomDatabase {
    private static DataBASE instance;
    public abstract Daily_Dao getDao();


    public static synchronized DataBASE getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context,DataBASE.class,"Khaata")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
