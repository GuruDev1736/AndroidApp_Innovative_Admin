package com.guruprasad.innovativeadmin.Activities.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject.UploadProject;
import com.guruprasad.innovativeadmin.R;
import com.guruprasad.innovativeadmin.databinding.ActivitySessionBinding;

public class SessionActivity extends AppCompatActivity {

    ActivitySessionBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySessionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.uploadProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionActivity.this, UploadProject.class));
            }

        });

        binding.uploadedProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionActivity.this, UploadedProject.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}