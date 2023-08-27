package com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.guruprasad.innovativeadmin.Activities.HomeActivity;
import com.guruprasad.innovativeadmin.Activities.ui.home.SessionActivity;
import com.guruprasad.innovativeadmin.Constants.Constants;
import com.guruprasad.innovativeadmin.Constants.UniquesIDGenerator;
import com.guruprasad.innovativeadmin.Model.UploadProjectModel;
import com.guruprasad.innovativeadmin.R;
import com.guruprasad.innovativeadmin.databinding.ActivityUploadProjectBinding;

import java.util.UUID;

public class UploadProject extends AppCompatActivity {

    ActivityUploadProjectBinding binding ;
    Uri logo;
    FirebaseStorage storage  = FirebaseStorage.getInstance();
    FirebaseDatabase database  = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.actionbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.actionbar.activityName.setText("Upload Project");
        binding.actionbar.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadProject.this, HomeActivity.class));
            }
        });



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(UploadProject.this,R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        binding.category.setAdapter(adapter);

        binding.generatedid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniquesIDGenerator generator = new UniquesIDGenerator();
                UUID id =  generator.generateUniqueId();
                binding.etReferenceId.setText(id.toString());

            }
        });

        binding.uploadProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 ProjectDetails();
            }
        });


        binding.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenManager(101);

                  }
        });

    }

    private void ProjectDetails() {

        String title = binding.etTitle.getText().toString();
        String reference = binding.etReferenceId.getText().toString();
        String description = binding.etDescription.getText().toString();
        String feature = binding.etFeature.getText().toString();
        String advantage = binding.etAdvantage.getText().toString();
        String disadvantage = binding.etDisadvantage.getText().toString();
        String price = binding.etPrice.getText().toString();
        String category = binding.category.getSelectedItem().toString();

        if (TextUtils.isEmpty(title))
        {
            materialdailog("Error", "Please Enter the title for the project");
            return;
        }
        if (TextUtils.isEmpty(reference))
        {
            materialdailog("Error", "Please Generate the reference Id for the project");
            return;
        }
        if (TextUtils.isEmpty(description))
        {
            materialdailog("Error","Please Enter the description for the project");
            return;
        }
        if (TextUtils.isEmpty(feature))
        {
            materialdailog("Error","Please Enter the features of the project");
            return;
        }
        if (TextUtils.isEmpty(advantage))
        {
            materialdailog("Error", "Please mention the advantage of the project");
            return;
        }
        if (TextUtils.isEmpty(disadvantage))
        {
            materialdailog("Error", "Please mention the Disadvantage of the project");
            return;
        }
        if (TextUtils.isEmpty(price))
        {
            materialdailog("Error","Please mention the price of the project");
            return;
        }
        if (category.equals("Select"))
        {
            materialdailog("Error","Select the category for the project");
            return;
        }
        if (logo==null)
        {
            materialdailog("Error","Please Select the logo for the project");
            return;
        }


        ProgressDialog pd = Constants.progress_dialog(UploadProject.this,"Please Wait" , "Project is Uploading to the server....");
        pd.show();

        final StorageReference storageReference = storage.getReference().child("LOGO").child(title);
                storageReference.putFile(logo)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                UploadProjectModel model = new UploadProjectModel(reference,uri.toString(),title,description,feature,advantage,disadvantage,price,category);
                                database.getReference().child(category).child(reference).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful())
                                                {
                                                    database.getReference().child("Buy Project").child(reference).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful())
                                                            {
                                                            Constants.success(UploadProject.this,"Project has uploaded successfully");
                                                            pd.dismiss();
                                                            }
                                                            else
                                                            {
                                                                materialdailog("Error","Error : "+task.getException().getMessage());
                                                                pd.dismiss();
                                                            }
                                                        }
                                                    });
                                                }
                                                else
                                                {
                                                    materialdailog("Error","Error : "+task.getException().getMessage());
                                                    pd.dismiss();
                                                }
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                materialdailog("Error","Error : "+e.getMessage());
                                pd.dismiss();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        materialdailog("Error","Error : "+e.getMessage());
                        pd.dismiss();
                    }
                });

    }
    private void materialdailog(String title , String message)
    {
        new MaterialAlertDialogBuilder(UploadProject.this, R.style.RoundShapeTheme)
                .setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.img)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private void OpenManager(int requestcode) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select the Image."),requestcode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==101 && resultCode==RESULT_OK)
        {
            if (data!=null)
            {
            logo = data.getData();
            Glide.with(UploadProject.this).load(logo).into(binding.logo);
            }
        }
    }
}