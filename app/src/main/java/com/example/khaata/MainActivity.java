package com.example.khaata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.khaata.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(1024,1024);

        setSupportActionBar(binding.toolbar);
        
        SharedPreferences sharedPreferences = getSharedPreferences("PaymentInfo",MODE_PRIVATE);
        if(sharedPreferences.getString("PayeeName",null)!=null
                && sharedPreferences.getString("VPA",null)!=null
        && sharedPreferences.getString("Balance",null)!=null)
        {
            binding.balanceTxt.setText("₹ "+sharedPreferences.getString("Balance",null));
        }
        binding.addNewExpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog = new BottomSheetDialog();

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
        int id = item.getItemId();
        if(id == R.id.logout)
        {
            SharedPreferences sharedPreferences =  getSharedPreferences("PaymentInfo",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("PayeeName",null);
            editor.putString("VPA",null);
            editor.putString("Balance",null);
            editor.commit();
            startActivity(new Intent(this,PaymentInfoActivity.class));
            finish();
        }
        else {

        }
        return true;
    }
}