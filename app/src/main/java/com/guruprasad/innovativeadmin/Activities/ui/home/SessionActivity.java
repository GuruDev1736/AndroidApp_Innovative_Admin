package com.guruprasad.innovativeadmin.Activities.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.guruprasad.innovativeadmin.Activities.HomeActivity;
import com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject.EditProjectActivity;
import com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject.EditProjectList;
import com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject.UploadProject;
import com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject.UploadScreenShot;
import com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject.UploadedProject;
import com.guruprasad.innovativeadmin.databinding.ActivitySessionBinding;

public class SessionActivity extends AppCompatActivity {

    ActivitySessionBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySessionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        binding.actionbar.activityName.setText("Upload Project");
        binding.actionbar.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionActivity.this, HomeActivity.class));
            }
        });

        binding.actionbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionActivity.this, HomeActivity.class));
            }
        });



        binding.uploadProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionActivity.this, UploadProject.class));
            }

        });

        binding.projectDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionActivity.this, UploadedProject.class));
            }
        });

        binding.uploadss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionActivity.this, UploadScreenShot.class));
            }
        });

        binding.editProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionActivity.this, EditProjectList.class));
            }
        });
    }
}