package com.safar.pccoehackathon.owner.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safar.pccoehackathon.R;
import com.safar.pccoehackathon.databinding.FragmentOwnerCustomerBinding;
import com.safar.pccoehackathon.databinding.FragmentOwnerFoodBinding;

public class OwnerCustomerFragment extends Fragment {
    private FragmentOwnerCustomerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOwnerCustomerBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }
}