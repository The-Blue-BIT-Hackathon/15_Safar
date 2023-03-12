package com.safar.pccoehackathon.customer.ui;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.type.LatLngOrBuilder;
import com.safar.pccoehackathon.databinding.FragmentHomeBinding;

import java.io.IOException;
import java.util.List;

public class CustomerHomeFragment extends Fragment implements OnMapReadyCallback {

    private FragmentHomeBinding binding;


    GoogleMap googleMap;
    SupportMapFragment mapFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = binding.searchView.getQuery().toString();
                List<Address> addressList = null;

                if(location!=null || !location.equals(""))
                {
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(location,1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new com.google.android.gms.maps.model.LatLng(address.getLatitude(),address.getLongitude());

                    Log.d("TAG", "hello -"+latLng);


                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return binding.getRoot();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}