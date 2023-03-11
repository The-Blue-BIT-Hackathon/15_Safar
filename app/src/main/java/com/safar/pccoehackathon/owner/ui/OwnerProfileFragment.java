package com.safar.pccoehackathon.owner.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.safar.pccoehackathon.LoginActivity;
import com.safar.pccoehackathon.R;
import com.safar.pccoehackathon.UserModel;
import com.safar.pccoehackathon.databinding.FragmentOwnerProfileBinding;

import java.util.HashMap;
import java.util.Map;

public class OwnerProfileFragment extends Fragment {

    private FragmentOwnerProfileBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOwnerProfileBinding.inflate(getLayoutInflater());
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        getProfileData();
        binding.btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, messName, phoneNumber, messLocation;

                name = binding.tvName.getText().toString().trim();
                messName = binding.tvMessName.getText().toString().trim();
                phoneNumber = binding.tvPhoneNumber.getText().toString().trim();
                messLocation = binding.tvLocation.getText().toString().trim();

                createDialog(name, messName, phoneNumber, messLocation);
            }
        });

        binding.btnProfileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return binding.getRoot();

    }

    private void getProfileData() {
        Log.d("TAG", "getProfileData: "+firebaseAuth.getCurrentUser().getEmail());
        firebaseFirestore
                .collection("Owner")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        String name, messName, phoneNumber, messLocation;

                        name = value.getString("name");
                        messName = value.getString("messname");
                        phoneNumber = value.getString("ownerphone");
                        messLocation = value.getString("location");

                        Log.d("TAG", "onEvent: "+name);

                        binding.tvName.setText(name);
                        binding.tvMessName.setText(messName);
                        binding.tvPhoneNumber.setText(phoneNumber);
                        binding.tvLocation.setText(messLocation);
                    }
                });
    }

    private void createDialog(String name, String messName, String ownerphone, String messLocation) {
        Dialog dialog = new Dialog(getActivity());

        dialog.setContentView(R.layout.owner_profile_edit_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText etName, etMessName, etPhoneNumber, etLocation;
        Button btnChangeLocation, btnSaveChanges;

        btnSaveChanges = dialog.findViewById(R.id.btnSaveChanges);
        btnChangeLocation = dialog.findViewById(R.id.btnChangeLocation);

        etName = dialog.findViewById(R.id.etName);
        etMessName = dialog.findViewById(R.id.etMessName);
        etPhoneNumber = dialog.findViewById(R.id.etPhoneNumber);
        etLocation = dialog.findViewById(R.id.etLocation);

        etName.setText(name);
        etMessName.setText(messName);
        etPhoneNumber.setText(ownerphone);
        etLocation.setText(messLocation);
        
        btnChangeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Yet to Build", Toast.LENGTH_SHORT).show();
            }
        });

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> data = new HashMap<>();
                data.put("name", etName.getText().toString().trim());
                data.put("messname", etMessName.getText().toString().trim());
                data.put("ownerphone", etPhoneNumber.getText().toString().trim());
                data.put("location", etLocation.getText().toString().trim());

                firebaseFirestore
                        .collection("Owner")
                        .document(firebaseAuth.getCurrentUser().getEmail())
                        .update(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getActivity(), "Profile Updated", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Failed to Update", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
            }
        });
        dialog.show();
    }
}