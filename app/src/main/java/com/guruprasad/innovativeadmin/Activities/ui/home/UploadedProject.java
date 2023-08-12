package com.guruprasad.innovativeadmin.Activities.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.guruprasad.innovativeadmin.R;
import com.guruprasad.innovativeadmin.databinding.ActivityUploadedProjectBinding;

public class UploadedProject extends AppCompatActivity {

    ActivityUploadedProjectBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadedProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }
}