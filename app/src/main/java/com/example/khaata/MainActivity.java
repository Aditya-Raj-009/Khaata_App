package com.example.khaata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.khaata.Adapter.RecyclerAdapter;
import com.example.khaata.databinding.ActivityMainBinding;
import com.example.khaata.entity.ExpenseList;
import com.example.khaata.viewModel.ViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static ActivityMainBinding binding;
    public static ArrayList<ExpenseList> lists = new ArrayList<>();
    public static RecyclerAdapter adapter;
    private static ViewModel databaseModel;
   private static SharedPreferences preferences;
    public static double total_exp = 0.0;
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(1024,1024);

        setSupportActionBar(binding.toolbar);
        context = this;
        preferences = getSharedPreferences("DATA",MODE_PRIVATE);
        setTotalExpense();

        databaseModel = ViewModel.getInstance(this);
        getAllData();

        databaseModel.getAllData().observe(this, new Observer<ArrayList<ExpenseList>>() {

            @Override
            public void onChanged(ArrayList<ExpenseList> expenseLists) {

                adapter.notifyDataSetChanged();
//                Log.v("lstInfo",lists.get(0).getTitle());
//                Log.v("lstInfo",lists.get(0).getDescription());
//                Log.v("lstInfo",lists.get(0).getExpList().get(0).getItemDesc());

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle(lists.get(viewHolder.getAdapterPosition()).getTitle());
                dialog.setMessage("Do you want to delete this ?");
                dialog.setIcon(R.drawable.baseline_delete_24);
                dialog.setCancelable(false);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int position = viewHolder.getAdapterPosition();
                       int p= databaseModel.DeleteExpense(lists.get(position));
                        Toast.makeText(MainActivity.this, p+"", Toast.LENGTH_SHORT).show();
                        total_exp-=lists.get(position).getTotal();
                        SharedPreferences.Editor edit = preferences.edit();
                        edit.putFloat("total_exp", (float) total_exp);
                        edit.apply();
                        lists.remove(position);
                        adapter.notifyItemRemoved(position);
                        setTotalExpense();


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.show();


            }
        }).attachToRecyclerView(binding.recyclerView2);




        binding.addNewExpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetDialog  dialog = new BottomSheetDialog(binding.recyclerView2,binding);

                if(!dialog.isAdded())
                {

                    dialog.show(getSupportFragmentManager(),"");

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return true;
    }

    public static void setTotalExpense()
    {

        if(preferences!=null)
        {
            total_exp = preferences.getFloat("total_exp",0.0f);
            binding.totalTxt.setText("₹ "+total_exp);
            Log.v("total",preferences.getFloat("total_exp",0.0f)+"");


        }
    }

    public static void getAllData()
    {
        lists = databaseModel.getAllData().getValue();
        adapter = new RecyclerAdapter(lists,context,expenseList -> {
            Intent detailsActivity = new Intent(context, ExpenseDetailsActivity.class);
            detailsActivity.putExtra("ExpenseDetails",expenseList);
           context.startActivity(detailsActivity);
        });
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(context));

        binding.recyclerView2.setAdapter(adapter);
        binding.recyclerView2.setHasFixedSize(true);
        adapter.notifyDataSetChanged();


    }
}