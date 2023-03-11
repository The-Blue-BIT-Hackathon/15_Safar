package com.safar.pccoehackathon.customer.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.safar.pccoehackathon.databinding.FragmentDashboardBinding;
import com.safar.pccoehackathon.databinding.FragmentOwnerHomeBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }
}