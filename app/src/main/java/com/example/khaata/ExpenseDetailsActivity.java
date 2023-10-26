package com.example.khaata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.khaata.databinding.ActivityExpenseDetailsBinding;

public class ExpenseDetailsActivity extends AppCompatActivity {

    private ActivityExpenseDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpenseDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}