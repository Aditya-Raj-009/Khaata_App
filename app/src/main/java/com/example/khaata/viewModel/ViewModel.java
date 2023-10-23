package com.example.khaata.viewModel;

import android.app.Application;
import android.os.Looper;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.khaata.DataBASE;
import com.example.khaata.dao.Daily_Dao;
import com.example.khaata.entity.ExpenseDetails;
import com.example.khaata.entity.ExpenseList;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

public class ViewModel extends AndroidViewModel {
    private Daily_Dao dao;
    private DataBASE dataBASE;
    public ViewModel(@NonNull Application application) {
        super(application);
        dataBASE = DataBASE.getInstance(application);
        dao = dataBASE.getDao();

    }

    MutableLiveData<ArrayList<ExpenseList>> data = new MutableLiveData<>();

        public MutableLiveData<ArrayList<ExpenseList>> getAllData()
        {

            data.setValue((ArrayList<ExpenseList>) dao.getAllExpense());
            return data;

        }

        public void DeleteExpense(ExpenseList expenseList)
        {
            ExecutorService service = Executors.newSingleThreadExecutor();
           service.execute(new Runnable() {
               @Override
               public void run() {
                   dao.deleteData(expenseList);
               }
           });
        }

        public void InsertExpense(ExpenseList expenseList)
        {
            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(new Runnable() {
                @Override
                public void run() {
                    dao.insertData(expenseList);
                }
            });
        }





    }
