package com.safar.pccoehackathon.customer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.safar.pccoehackathon.R;

public class CustomerMessInfoActivity extends AppCompatActivity {

    private String email;
    private FirebaseFirestore firebaseFirestore;
    private LinearLayout llData;
    private TextView tvMessName, tvLocation, tvPhoneNumber, tvEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.customer_mess_info);

        email = getIntent().getStringExtra("email");
        firebaseFirestore = FirebaseFirestore.getInstance();

        tvMessName = findViewById(R.id.tvMessName);
        tvLocation = findViewById(R.id.tvLocation);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvEmail = findViewById(R.id.tvEmail);

        llData = findViewById(R.id.llData);

        setHeader(email);

        getDishes(email);

    }

    private void getDishes(String email) {
        firebaseFirestore
                .collection("Owner")
                .document(email)
                .collection("plates")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            String id = dc.getDocument().getId();
                            String plateName = dc.getDocument().getData().get("plateName").toString();
                            String type = dc.getDocument().getData().get("type").toString();
                            String allergies = dc.getDocument().getData().get("allergies").toString();
                            String price = dc.getDocument().getData().get("price").toString();
                            String contents = dc.getDocument().getData().get("contents").toString();
                            String available = dc.getDocument().getData().get("available").toString();
                            switch (dc.getType()) {
                                case ADDED:
                                    createCard(id, plateName, price, available, contents, allergies, type);
                                    break;
                                case MODIFIED:
                                    updatePlate(id, plateName, price, available, contents, allergies, type);
                                    break;
                                case REMOVED:
                                    for (int i = 0; i < llData.getChildCount(); i++) {

                                        TextView tvID = llData.getChildAt(i).findViewById(R.id.tvID);

                                        String firebase_id = tvID.getText().toString().trim();

                                        if (firebase_id.equals(id)) {
                                            llData.removeView(llData.getChildAt(i));
                                        }
                                    }
                                    break;
                            }
                        }

                    }
                });
    }

    private void updatePlate(String id, String plateName, String price, String available, String contents, String allergies, String type) {
        for (int i = 0; i < llData.getChildCount(); i++) {

            TextView tvDishName, tvPrice, tvID, tvAvailable, tvContents, tvAllergies, tvType;

            tvDishName = llData.getChildAt(i).findViewById(R.id.tvDishName);
            tvPrice = llData.getChildAt(i).findViewById(R.id.tvPrice);
            tvID = llData.getChildAt(i).findViewById(R.id.tvID);
            tvAvailable = llData.getChildAt(i).findViewById(R.id.tvAvailable);
            tvContents = llData.getChildAt(i).findViewById(R.id.tvContents);
            tvAllergies = llData.getChildAt(i).findViewById(R.id.tvAllergies);
            tvType = llData.getChildAt(i).findViewById(R.id.tvType);

            if (tvID.getText().toString().trim().equals(id)) {
                tvDishName.setText(plateName);
                tvPrice.setText(price);
                tvContents.setText(contents);
                tvType.setText(type);
                tvAllergies.setText(allergies);
                tvAvailable.setText(available);

            }

        }
    }

    private void createCard(String id, String plateName, String price, String available, String contents, String allergies, String type) {
        View messDishView = getLayoutInflater().inflate(R.layout.layout_customer_mess_dish_info, null, false);

        TextView tvDishName, tvPrice, tvID, tvAvailable, tvContents, tvAllergies, tvType;

        tvDishName = messDishView.findViewById(R.id.tvDishName);
        tvPrice = messDishView.findViewById(R.id.tvPrice);
        tvID = messDishView.findViewById(R.id.tvID);
        tvAvailable = messDishView.findViewById(R.id.tvAvailable);
        tvContents = messDishView.findViewById(R.id.tvContents);
        tvAllergies = messDishView.findViewById(R.id.tvAllergies);
        tvType = messDishView.findViewById(R.id.tvType);

        tvDishName.setText(plateName);
        tvPrice.setText(price);
        tvID.setText(id);
        tvAvailable.setText(available);
        tvContents.setText(contents);
        tvAllergies.setText(allergies);
        tvType.setText(type);

        llData.addView(messDishView);
    }

    private void setHeader(String email) {
        firebaseFirestore
                .collection("Owner")
                .document(email)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        String messName = value.getString("messname");
                        String location = value.getString("location");
                        String phoneNumber = value.getString("ownerphone");
                        String email = value.getString("email");

                        tvMessName.setText(messName);
                        tvLocation.setText(location);
                        tvPhoneNumber.setText(phoneNumber);
                        tvEmail.setText(email);
                    }
                });
    }
}