package com.safar.pccoehackathon.customer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.safar.pccoehackathon.R;
import com.safar.pccoehackathon.customer.CustomerMessInfoActivity;
import com.safar.pccoehackathon.databinding.FragmentHomeBinding;

public class CustomerHomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private FirebaseFirestore firebaseFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        firebaseFirestore = FirebaseFirestore.getInstance();

        getAllOwners();

        return binding.getRoot();

    }

    private void getAllOwners() {

        Log.d("TAG", "getAllOwners: "+"hi");
        firebaseFirestore
                .collection("Owner")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            String id = dc.getDocument().getId();
                            Log.d("TAG", "onEvent: "+id);
                            String messname = dc.getDocument().getData().get("messname").toString();
                            String location = dc.getDocument().getData().get("location").toString();
                            String monthlyPrice = dc.getDocument().getData().get("monthlyPrice").toString();

                            createCard(id, messname, location, monthlyPrice);
                        }
                    }
                });

    }

    private void createCard(String id, String messname, String location, String monthlyPrice) {
        View messView = getLayoutInflater().inflate(R.layout.activity_layout_customer_mess_container, null, false);

        TextView tvMessName, tvMonthlyPrice, tvLocation;
        ImageView ivNext;

        tvMessName = messView.findViewById(R.id.tvMessName);
        tvMonthlyPrice = messView.findViewById(R.id.tvMonthlyPrice);
        tvLocation = messView.findViewById(R.id.tvLocation);
        ivNext = messView.findViewById(R.id.ivNext);

        tvMessName.setText(messname);
        tvMonthlyPrice.setText(monthlyPrice);
        tvLocation.setText(location);

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CustomerMessInfoActivity.class));
            }
        });

        binding.llData.addView(messView);
    }
}