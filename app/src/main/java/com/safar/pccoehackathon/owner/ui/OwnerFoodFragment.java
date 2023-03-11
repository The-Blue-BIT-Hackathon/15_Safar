package com.safar.pccoehackathon.owner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.safar.pccoehackathon.Plate;
import com.safar.pccoehackathon.R;
import com.safar.pccoehackathon.databinding.FragmentOwnerFoodBinding;

public class OwnerFoodFragment extends Fragment {

    private FragmentOwnerFoodBinding binding;
    private FirebaseFirestore firebaseFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOwnerFoodBinding.inflate(getLayoutInflater());

        firebaseFirestore = FirebaseFirestore.getInstance();

        return binding.getRoot();

    }

//    private void displayPlate() {
//        firebaseFirestore
//                .collection("Owner")
//                .document("12345") //edit
//                .collection("plates")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        for (DocumentChange dc : value.getDocumentChanges()) {
//                            String id = dc.getDocument().getData().get("id").toString();
//                            String plateName = dc.getDocument().getData().get("plateName").toString();
//                            int price = Integer.parseInt(dc.getDocument().getData().get("expiryTime").toString());
//                            switch (dc.getType()) {
//                                case ADDED:
//                                    createCard(id, plateName, price);
//                                    break;
//                                case MODIFIED:
//                                    updatePlate(id, plateName, price);
//                                    break;
//                                case REMOVED:
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
//                                    break;
//                            }
//                        }
//                    }
//                });
//    }

//    private void updatePlate(String id, String plateName, int price) {
//        for (int i = 0; i < binding.llData.getChildCount(); i++) {
//
//            TextView tvID = binding.llData.getChildAt(i).findViewById(R.id.tvID);
//            TextView tvPlateName = binding.llData.getChildAt(i).findViewById(R.id.tvPlateName);
//            TextView tvPrice = binding.llData.getChildAt(i).findViewById(R.id.tvPrice);
//
//
//            if (tvID.getText().toString().trim().equals(id)) {
//                tvPlateName.setText(plateName);
//                tvPrice.setText(price);
//
//            }
//
//        }
//    }

    private void createCard(String id, String plateName, int price) {
        View plateView = getLayoutInflater().inflate(R.layout.owner_plate_layout, null, false);

        binding.llData.addView(plateView);
    }
    private void addPlate(String plateName, int price) {
        String id = createID();
        firebaseFirestore
                .collection("Owner")
                .document("12345") //edit
                .collection("plates")
                .add(new Plate(id,plateName,price))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Plate added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to add plate", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public String createID() {
        String id = "";
        id = String.valueOf(System.currentTimeMillis());
        return id;
    }
}