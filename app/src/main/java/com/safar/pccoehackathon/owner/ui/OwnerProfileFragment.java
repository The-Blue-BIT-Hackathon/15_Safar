package com.safar.pccoehackathon.owner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.safar.pccoehackathon.databinding.FragmentOwnerProfileBinding;

public class OwnerProfileFragment extends Fragment {

    private FragmentOwnerProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOwnerProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }
}