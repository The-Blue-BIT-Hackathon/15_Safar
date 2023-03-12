package com.safar.pccoehackathon.customer.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safar.pccoehackathon.R;
import com.safar.pccoehackathon.databinding.FragmentCustomerDietBinding;
import com.safar.pccoehackathon.databinding.FragmentHomeBinding;

public class CustomerDietFragment extends Fragment {

    FragmentCustomerDietBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCustomerDietBinding.inflate(getLayoutInflater());



        return binding.getRoot();
    }
}