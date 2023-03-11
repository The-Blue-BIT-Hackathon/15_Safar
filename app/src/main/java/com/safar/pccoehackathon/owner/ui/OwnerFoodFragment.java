package com.safar.pccoehackathon.owner.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.safar.pccoehackathon.Plate;
import com.safar.pccoehackathon.R;
import com.safar.pccoehackathon.databinding.FragmentOwnerFoodBinding;

import java.util.HashMap;
import java.util.Map;

public class OwnerFoodFragment extends Fragment {

    private FragmentOwnerFoodBinding binding;
    private FirebaseFirestore firebaseFirestore;

    private FirebaseAuth firebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOwnerFoodBinding.inflate(getLayoutInflater());

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        displayPlate();

        binding.fabAddPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.owner_food_new_layout);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                EditText etPlateName, etPlatePrice;
                AutoCompleteTextView actvPlateType, actvAllergies;
                CheckBox cbGlutenFree;
                Button btnAddDish;

                etPlateName = dialog.findViewById(R.id.etPlateName);
                etPlatePrice = dialog.findViewById(R.id.etPlatePrice);

                actvPlateType = dialog.findViewById(R.id.actvPlateType);
                actvAllergies = dialog.findViewById(R.id.actvAllergies);

                cbGlutenFree = dialog.findViewById(R.id.cbGlutenFree);

                btnAddDish = dialog.findViewById(R.id.btnAddDish);

                btnAddDish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String plateName, allergies, glutenFree, price, type;

                        glutenFree = "No";

                        plateName = etPlateName.getText().toString().trim();
                        price = etPlatePrice.getText().toString().trim();
                        allergies = actvAllergies.getText().toString().trim();
                        if (cbGlutenFree.isChecked()) {
                            glutenFree = "Yes";
                        }
                        type = actvPlateType.getText().toString().trim();

                        addPlate(plateName, type, allergies, glutenFree, price);
                    }
                });
                dialog.show();
            }
        });

        return binding.getRoot();

    }

    private void displayPlate() {
        firebaseFirestore
                .collection("Owner")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("plates")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        //String plateName, String type, String allergies, String glutenFree, String price
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            String id = dc.getDocument().getId();
                            String plateName = dc.getDocument().getData().get("plateName").toString();
                            String type = dc.getDocument().getData().get("type").toString();
                            String allergies = dc.getDocument().getData().get("allergies").toString();
                            String glutenFree = dc.getDocument().getData().get("glutenFree").toString();
                            String price = dc.getDocument().getData().get("price").toString();
                            switch (dc.getType()) {
                                case ADDED:
                                    createCard(id, plateName, type, allergies, glutenFree, price);
                                    break;
                                case MODIFIED:
//                                    updatePlate(id, plateName, type, allergies, glutenFree, price);
                                    break;
                                case REMOVED:
//                                    for (int i = 0; i < binding.llData.getChildCount(); i++) {
//
//                                        TextView tvID = binding.llData.getChildAt(i).findViewById(R.id.tvID);
//
//                                        String firebase_id = tvID.getText().toString().trim();
//
//                                        if (firebase_id.equals(id)) {
//                                            binding.llData.removeView(binding.llData.getChildAt(i));
//                                        }
//                                    }
                                    break;
                            }
                        }
                    }
                });
    }

    private void updatePlateData(String id, String plateName, String type, String allergies, String glutenFree, String price) {
        Map<String, Object> data = new HashMap<>();
        data.put("plateName", plateName);
        data.put("type", type);
        data.put("allergies", allergies);
        data.put("glutenFree", glutenFree);
        data.put("price", price);

        firebaseFirestore
                .collection("Owner")
                .document(firebaseAuth.getCurrentUser().getEmail()) //edit
                .collection("plates")
                .document(id)
                .update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Plate Data Updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
//    private void updatePlate(String id, String plateName, String type, String allergies, String glutenFree, String price) {
//        for (int i = 0; i < binding.llData.getChildCount(); i++) {
//
//            TextView tvID = binding.llData.getChildAt(i).findViewById(R.id.tvID);
//            TextView etPlateName = binding.llData.getChildAt(i).findViewById(R.id.etPlateName);
//            TextView etPlatePrice = binding.llData.getChildAt(i).findViewById(R.id.etPlatePrice);
//            AutoCompleteTextView actvPlateType = binding.llData.getChildAt(i).findViewById(R.id.actvPlateType);
//            AutoCompleteTextView actvAllergies = binding.llData.getChildAt(i).findViewById(R.id.actvAllergies);
//            AutoCompleteTextView cbGlutenFree = binding.llData.getChildAt(i).findViewById(R.id.cbGlutenFree);
//
//
//            if (tvID.getText().toString().trim().equals(id)) {
//                etPlateName.setText(plateName);
//                etPlatePrice.setText(price);
//                actvPlateType.setText(type);
//                actvAllergies.setText(allergies);
//                cbGlutenFree.setText(glutenFree);
//
//            }
//
//        }
//    }

    private void createCard(String id, String plateName, String type, String allergies, String glutenFree, String price) {
        View plateView = getLayoutInflater().inflate(R.layout.owner_food_layout, null, false);

        TextView tvDishName, tvID, tvContents, tvGluttenFree, tvAllergies, tvType;

        tvID = plateView.findViewById(R.id.tvID);
        tvDishName = plateView.findViewById(R.id.tvDishName);
        tvContents = plateView.findViewById(R.id.tvContents);
        tvGluttenFree = plateView.findViewById(R.id.tvGluttenFree);
        tvAllergies = plateView.findViewById(R.id.tvAllergies);
        tvType = plateView.findViewById(R.id.tvType);

        tvID.setText(id);
        tvDishName.setText(plateName);
        tvContents.setText(id);
        tvGluttenFree.setText(id);
        tvAllergies.setText(id);
        tvType.setText(type);

        binding.llData.addView(plateView);
    }

    private void addPlate(String plateName, String type, String allergies, String glutenFree, String price) {
        String id = String.valueOf(System.currentTimeMillis());
        firebaseFirestore
                .collection("Owner")
                .document(firebaseAuth.getCurrentUser().getEmail()) //edit
                .collection("plates")
                .document(id)
                .set(new Plate(plateName, type, allergies, glutenFree, price))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Plate added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to add plate", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}