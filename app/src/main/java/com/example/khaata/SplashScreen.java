package com.example.khaata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(1024,1024);


    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getSharedPreferences("PaymentInfo",MODE_PRIVATE).getString("PayeeName",null)!=null)
                {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));

                }
                else {
                    startActivity(new Intent(SplashScreen.this,PaymentInfoActivity.class));

                }
                finish();

            }
        },3000);
    }

}