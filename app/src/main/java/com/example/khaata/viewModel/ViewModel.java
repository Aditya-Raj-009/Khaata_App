package com.example.khaata.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.khaata.DataBASE;
import com.example.khaata.dao.Daily_Dao;
import com.example.khaata.entity.ExpenseList;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private static Daily_Dao dao;
    private static DataBASE dataBASE;
   private static ViewModel instance;

   public static ViewModel getInstance(Context context)
   {
       if(instance==null)
       {
           instance = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModel.class);
           dataBASE = DataBASE.getInstance(context);
           dao = dataBASE.getDao();

       }
       return instance;
   }


    MutableLiveData<ArrayList<ExpenseList>> data = new MutableLiveData<>();

        public MutableLiveData<ArrayList<ExpenseList>> getAllData()
        {

            data.setValue((ArrayList<ExpenseList>) dao.getAllExpense());
            return data;

        }

        public int DeleteExpense(ExpenseList expenseList)
        {

            return dao.deleteData(expenseList);
        }

        public void InsertExpense(ExpenseList expenseList)
        {


                    dao.insertData(expenseList);

        }





    }
