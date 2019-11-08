package com.telcco.klipmunk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.telcco.klipmunk.databinding.ActivityOtpBinding;

public class OTP extends AppCompatActivity {
    OtpViewModel otpViewModel;
    ActivityOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        otpViewModel = ViewModelProviders.of(this).get(OtpViewModel.class);
        binding = DataBindingUtil.setContentView(OTP.this,R.layout.activity_otp);
        binding.setLifecycleOwner(this);
        binding.setOtpViewModel(otpViewModel);

    }
}
