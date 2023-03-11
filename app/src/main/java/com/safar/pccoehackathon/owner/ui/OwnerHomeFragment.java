package com.safar.pccoehackathon.owner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.safar.pccoehackathon.databinding.FragmentOwnerHomeBinding;

public class OwnerHomeFragment extends Fragment {
    private FragmentOwnerHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOwnerHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }
}