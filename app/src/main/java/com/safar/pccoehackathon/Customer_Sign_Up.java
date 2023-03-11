package com.safar.pccoehackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.safar.pccoehackathon.databinding.ActivityCustomerSignUp2Binding;

public class Customer_Sign_Up extends AppCompatActivity {

    ActivityCustomerSignUp2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerSignUp2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Customer_Sign_Up.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}