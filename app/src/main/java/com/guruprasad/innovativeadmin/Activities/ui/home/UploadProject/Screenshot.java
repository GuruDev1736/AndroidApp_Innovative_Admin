package com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.guruprasad.innovativeadmin.R;
import com.guruprasad.innovativeadmin.databinding.ActivityScreenshotBinding;

public class Screenshot extends AppCompatActivity {

    ActivityScreenshotBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreenshotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





    }
}