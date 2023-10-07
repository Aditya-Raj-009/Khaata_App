package com.example.khaata.viewModel;

import androidx.room.TypeConverter;

import com.example.khaata.entity.ExpenseDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExpenseDetailsConverter {

    @TypeConverter
    public static ArrayList<ExpenseDetails> fromString(String value) {
        Type listType = new TypeToken<ArrayList<ExpenseDetails>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toString(ArrayList<ExpenseDetails> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
