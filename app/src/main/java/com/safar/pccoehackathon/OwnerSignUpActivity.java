package com.safar.pccoehackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.safar.pccoehackathon.databinding.ActivityOwnerSignUpBinding;

public class OwnerSignUpActivity extends AppCompatActivity {

    ActivityOwnerSignUpBinding binding;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOwnerSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Register Now");
        progressDialog.setMessage("Please wait loading");

        binding.etemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable s) {

                if(Patterns.EMAIL_ADDRESS.matcher(binding.etemail.getText().toString()).matches())
                {
                    Drawable drawable = ContextCompat.getDrawable(OwnerSignUpActivity.this, R.drawable.baseline_check_circle_24);
                    drawable = DrawableCompat.wrap(drawable);
                    drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

                    binding.etemail.setCompoundDrawables(null, null, drawable, null);
                }
                else
                {
                    Drawable drawable = ContextCompat.getDrawable(OwnerSignUpActivity.this, R.drawable.baseline_check_circle_outline_24);
                    drawable = DrawableCompat.wrap(drawable);
                    drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    binding.etemail.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });


        binding.btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = binding.etname.getText().toString();
                String messname = binding.etmessname.getText().toString();
                String ownerphone = binding.etphone.getText().toString();
                String monthlyrate = binding.etrate.getText().toString();
                String email = binding.etemail.getText().toString();
                String password = binding.etpassword.getText().toString();

                progressDialog.show();

                auth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent = new Intent(OwnerSignUpActivity.this,LoginActivity.class);
                                startActivity(intent);
                                progressDialog.cancel();

                                firebaseFirestore.collection("Owner")
                                        .document(FirebaseAuth.getInstance().getUid())
                                        .set(new UserModel(name,messname,ownerphone,monthlyrate,email,password));

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(OwnerSignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.cancel();

                            }
                        });

            }
        });




    }
}