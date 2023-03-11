package com.safar.pccoehackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.safar.pccoehackathon.databinding.ActivityCustomerSignUpBinding;

public class CustomerSignUpActivity extends AppCompatActivity {


    ActivityCustomerSignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CustomerSignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}