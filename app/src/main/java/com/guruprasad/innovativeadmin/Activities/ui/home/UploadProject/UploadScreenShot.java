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
import com.guruprasad.innovativeadmin.Adapters.UploadScreenShotAdapter;
import com.guruprasad.innovativeadmin.Adapters.UploadedProjectAdapter;
import com.guruprasad.innovativeadmin.Constants.Constants;
import com.guruprasad.innovativeadmin.Model.UploadProjectModel;
import com.guruprasad.innovativeadmin.R;
import com.guruprasad.innovativeadmin.databinding.ActivityUploadScreenShotBinding;

public class UploadScreenShot extends AppCompatActivity {

    ActivityUploadScreenShotBinding binding ;

    UploadScreenShotAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadScreenShotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.actionbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.actionbar.activityName.setText("Upload Screenshot");
        binding.actionbar.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadScreenShot.this, HomeActivity.class));
            }
        });



        ProgressDialog pd = Constants.progress_dialog(UploadScreenShot.this,"Please Wait","Fetching Data From Server");
        pd.show();


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(UploadScreenShot.this));
        FirebaseRecyclerOptions<UploadProjectModel> options = new FirebaseRecyclerOptions.Builder<UploadProjectModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Buy Project"),UploadProjectModel.class).build();

        adapter = new UploadScreenShotAdapter(options)
        {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                pd.dismiss();
            }

            @Override
            public void onError(@NonNull DatabaseError error) {
                super.onError(error);
                Constants.error(UploadScreenShot.this,"Error : "+error.getMessage());
                pd.dismiss();
            }
        };
        adapter.startListening();
        binding.recyclerview.setAdapter(adapter);


    }
}