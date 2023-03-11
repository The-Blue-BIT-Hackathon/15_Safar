package com.safar.pccoehackathon.owner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.safar.pccoehackathon.databinding.FragmentOwnerFoodBinding;

public class OwnerFoodFragment extends Fragment {

    private FragmentOwnerFoodBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOwnerFoodBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }
}