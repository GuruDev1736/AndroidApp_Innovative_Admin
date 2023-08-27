package com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.guruprasad.innovativeadmin.Activities.HomeActivity;
import com.guruprasad.innovativeadmin.Activities.ui.home.SessionActivity;
import com.guruprasad.innovativeadmin.R;
import com.guruprasad.innovativeadmin.databinding.ActivityEditProjectBinding;

public class EditProjectActivity extends AppCompatActivity {

    ActivityEditProjectBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.actionbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.actionbar.activityName.setText("Edit Project");
        binding.actionbar.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProjectActivity.this, HomeActivity.class));
            }
        });
    }
}