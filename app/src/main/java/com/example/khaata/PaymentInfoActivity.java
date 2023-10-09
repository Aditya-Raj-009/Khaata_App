package com.example.khaata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.khaata.databinding.ActivityPaymentInfoBinding;
import com.example.khaata.databinding.CustomSnackbarBinding;
import com.google.android.material.snackbar.Snackbar;

public class PaymentInfoActivity extends AppCompatActivity {

    ActivityPaymentInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(1024,1024);
        View customSnackBarView = CustomSnackbarBinding.inflate(getLayoutInflater()).getRoot();

        Snackbar customSnackbar = Snackbar.make(findViewById(android.R.id.content),"",Snackbar.LENGTH_LONG);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) customSnackbar.getView();

        snackbarLayout.setBackgroundColor(getResources().getColor(R.color.black));
        snackbarLayout.addView(customSnackBarView,0);
        TextView snackBarText = customSnackBarView.findViewById(R.id.snackbar_text);

        String nameEt = binding.nameEt.getText().toString().trim();
        String VPA = binding.vpaEt.getText().toString().trim();
        String balance = binding.balanceET.getText().toString().trim();

        binding.proceedBtn.setOnClickListener(view -> {
            if(nameEt.isEmpty() && VPA.isEmpty() && balance.isEmpty())
            {
                snackBarText.setText("Please! Enter Payee Name, VPA, and Balance");
                customSnackbar.show();
            }
            else if (nameEt.isEmpty())
            {
                snackBarText.setText("Please! Enter Payee Name");
                customSnackbar.show();
            }
            else if(VPA.isEmpty())
            {
                snackBarText.setText("Please! Enter VPA");
                customSnackbar.show();
            }
            else if(balance.isEmpty())
            {
                snackBarText.setText("Please! Enter Balance");
                customSnackbar.show();
            }
            else{
                SharedPreferences sharedPreferences =  getSharedPreferences("PaymentInfo",MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("PayeeName",nameEt);
                editor.putString("VPA",VPA);
                editor.commit();
                startActivity(new Intent(this,MainActivity.class));
            }
        });

    }
}