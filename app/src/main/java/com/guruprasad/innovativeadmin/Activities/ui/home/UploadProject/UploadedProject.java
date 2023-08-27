package com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.guruprasad.innovativeadmin.Activities.HomeActivity;
import com.guruprasad.innovativeadmin.Activities.ui.home.SessionActivity;
import com.guruprasad.innovativeadmin.Adapters.UploadedProjectAdapter;
import com.guruprasad.innovativeadmin.Constants.Constants;
import com.guruprasad.innovativeadmin.Model.UploadProjectModel;
import com.guruprasad.innovativeadmin.databinding.ActivityUploadedProjectBinding;

public class UploadedProject extends AppCompatActivity {

    ActivityUploadedProjectBinding binding ;

    UploadedProjectAdapter adapter1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadedProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.actionbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.actionbar.activityName.setText("Uploaded Project");
        binding.actionbar.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadedProject.this, HomeActivity.class));
            }
        });



        ProgressDialog pd = Constants.progress_dialog(UploadedProject.this,"Please Wait","Fetching Data From Server");
        pd.show();


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(UploadedProject.this));
        FirebaseRecyclerOptions<UploadProjectModel> options = new FirebaseRecyclerOptions.Builder<UploadProjectModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Buy Project"),UploadProjectModel.class).build();

        adapter1 = new UploadedProjectAdapter(options)
        {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                pd.dismiss();
            }

            @Override
            public void onError(@NonNull DatabaseError error) {
                super.onError(error);
                Constants.error(UploadedProject.this,"Error : "+error.getMessage());
                pd.dismiss();
            }
        };
        adapter1.startListening();
        binding.recyclerview.setAdapter(adapter1);

    }
}