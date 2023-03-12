package com.safar.pccoehackathon.customer.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.safar.pccoehackathon.LoginActivity;
import com.safar.pccoehackathon.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class CustomerProfileFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private FirebaseAuth firebaseAuth;

    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    int GOOGLE_PAY_REQUEST_CODE = 123;

    final int PAY_REQUEST = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());
        firebaseAuth = FirebaseAuth.getInstance();
        binding.btnProfileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });


        binding.btnmakepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                payUsingUpi();

            }
        });


        return binding.getRoot();

    }

    private void payUsingUpi() {


        Uri uri =
                new Uri.Builder()
                        .scheme("upi")
                        .authority("pay")
                        .appendQueryParameter("pa", "aheryash2004@okicici")
                        .appendQueryParameter("pn", "Yash Sandip Aher")
//                                .appendQueryParameter("mc", "your-merchant-code")
//                                .appendQueryParameter("tr", "your-transaction-ref-id")
//                                .appendQueryParameter("tn", "your-transaction-note")
//                                .appendQueryParameter("am", "your-order-amount")
//                                .appendQueryParameter("cu", "INR")
//                                .appendQueryParameter("url", "your-transaction-url")
                        .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);


        Intent chooser = Intent.createChooser(intent, "Pay With");

        if (null != chooser.resolveActivity(getActivity().getPackageManager())) {
            startActivityForResult(chooser, PAY_REQUEST);
        } else {
            Toast.makeText(getActivity(), "No Upi Id Found", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PAY_REQUEST)
        {
            if(isInternetAvailable(getActivity()))
            {
                if(data==null)
                {
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    String temp = "nothing";
                        Toast.makeText(getActivity(), "Transaction not complete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String text = data.getStringExtra("response");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add(text);

                    upiPaymentCheck(text);
                }
            }
        }

    }



    void upiPaymentCheck(String data)
    {
        String str = data;
        String payment_cancel = "";
        String status = "";
        String response[] = str.split("&");

        for (int i=0; i<response.length;i++)
        {
            String equalStr[]  = response[i].split("");
            if(equalStr.length>=2)
            {
                if(equalStr[0].toLowerCase().equals("Status" .toLowerCase()))
                {
                    status = equalStr[1].toLowerCase();
                }
            }
            else
            {
                payment_cancel = "Payment Cancel";
            }

            if(status.equals("success"))
            {
                Toast.makeText(getActivity(), "Transaction Successfully", Toast.LENGTH_SHORT).show();
            }
            else if("Payment Cancel".equals(payment_cancel))
            {
                Toast.makeText(getActivity(), "Payment Cancel by user", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getActivity(), "Transaction Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static boolean isInternetAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null)
        {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo.isConnected() && networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable())
            {
                return true;
            }
        }
        return false;
    }

}