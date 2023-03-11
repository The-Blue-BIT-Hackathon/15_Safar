package com.safar.pccoehackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.safar.pccoehackathon.databinding.ActivitySignUpCustomerBinding;

public class SignUpCustomer extends AppCompatActivity {

    ActivitySignUpCustomerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpCustomer.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}