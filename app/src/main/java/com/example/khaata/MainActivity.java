package com.example.khaata;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
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
}