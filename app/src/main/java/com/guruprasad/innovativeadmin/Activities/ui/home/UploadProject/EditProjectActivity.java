package com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.guruprasad.innovativeadmin.Activities.HomeActivity;
import com.guruprasad.innovativeadmin.Activities.logoDailog;
import com.guruprasad.innovativeadmin.Activities.ui.home.SessionActivity;
import com.guruprasad.innovativeadmin.Constants.Constants;
import com.guruprasad.innovativeadmin.Model.UploadProjectModel;
import com.guruprasad.innovativeadmin.R;
import com.guruprasad.innovativeadmin.databinding.ActivityEditProjectBinding;

import java.util.HashMap;
import java.util.Map;

public class EditProjectActivity extends AppCompatActivity {

    ActivityEditProjectBinding binding ;
    FirebaseDatabase database ;
    FirebaseStorage storage ;

    Uri logouri ;

    public static  String logo = null;
    public static  String title = null;
    public static  String category = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

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

        Intent intent = getIntent();
        String id  = intent.getStringExtra("projectId");

        ProgressDialog pd = Constants.progress_dialog(EditProjectActivity.this,"Please Wait","Fetching Data From Server");
        pd.show();



        database.getReference().child("Buy Project").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UploadProjectModel model = snapshot.getValue(UploadProjectModel.class);

                if (model!=null && !isDestroyed())
                {
                    logo = model.getLogourl();
                    title = model.getTitle();
                    category = model.getCategory();
                    Glide.with(EditProjectActivity.this).load(model.getLogourl()).placeholder(R.drawable.img).into(binding.logo);
                    binding.etTitle.setText(model.getTitle());
                    binding.etReferenceId.setText(model.getProjecId());
                    binding.etDescription.setText(model.getDescription());
                    binding.etFeature.setText(model.getFeature());
                    binding.etAdvantage.setText(model.getAdvantage());
                    binding.etDisadvantage.setText(model.getDisadvantage());
                    binding.etPrice.setText(model.getPrice());
                }
                else
                {
                    Constants.error(EditProjectActivity.this,"No Data Found Please Try Again");
                }

                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Constants.error(EditProjectActivity.this,"Error : "+error.getMessage());
                pd.dismiss();
            }
        });


        binding.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showLogoDialog();
            }
        });

        binding.logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                OpenManager(101);
                return false;
            }
        });

        binding.uploadlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (logouri==null)
                {
                    Constants.error(EditProjectActivity.this,"Please Select the image file for logo");
                    return;
                }

                ProgressDialog pd = Constants.progress_dialog(EditProjectActivity.this,"Please Wait", "Uploading Your Logo....");
                pd.show();

               final StorageReference reference =  storage.getReference().child("LOGO").child(title);
                        reference.putFile(logouri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                           @Override
                           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                               reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                   @Override
                                   public void onSuccess(Uri uri) {

                                       Map<String,Object> update = new HashMap<>();
                                       update.put("logourl",uri.toString());

                                       database.getReference().child("Buy Project").child(id).updateChildren(update).addOnSuccessListener(new OnSuccessListener<Void>() {
                                           @Override
                                           public void onSuccess(Void unused) {
                                                database.getReference().child(category).child(id).updateChildren(update).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful())
                                                            {
                                                                Constants.success(EditProjectActivity.this,"Logo Has Update Successfully");
                                                                pd.dismiss();
                                                            }
                                                            else
                                                            {
                                                                Constants.error(EditProjectActivity.this,"Failed to update logo : "+task.getException().getMessage());
                                                                pd.dismiss();
                                                            }
                                                    }
                                                });
                                           }
                                       }).addOnFailureListener(new OnFailureListener() {
                                           @Override
                                           public void onFailure(@NonNull Exception e) {
                                                    Constants.error(EditProjectActivity.this,"Error : "+e.getMessage());
                                                    pd.dismiss();
                                           }
                                       });
                                   }
                               }).addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull Exception e) {
                                        Constants.error(EditProjectActivity.this,"Error : "+e.getMessage());
                                        pd.dismiss();
                                   }
                               });
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                                Constants.error(EditProjectActivity.this,"Error : "+e.getMessage());
                                pd.dismiss();
                           }
                       });
            }
        });

        binding.updateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = binding.etTitle.getText().toString();
                String description = binding.etDescription.getText().toString();
                String feature = binding.etFeature.getText().toString();
                String advantage = binding.etAdvantage.getText().toString();
                String disadvantage = binding.etDisadvantage.getText().toString();
                String price = binding.etPrice.getText().toString();

                if (TextUtils.isEmpty(title))
                {
                    materialdailog("Error", "Please Enter the title for the project");
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

                ProgressDialog pd = Constants.progress_dialog(EditProjectActivity.this,"Please Wait","Updating Project Details To Database");
                pd.show();

                Map<String,Object> update = new HashMap<>();
                update.put("title",title);
                update.put("description",description);
                update.put("feature",feature);
                update.put("advantage",advantage);
                update.put("disadvantage",disadvantage);
                update.put("price",price);

                database.getReference().child("Buy Project").child(id).updateChildren(update)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    database.getReference().child(category).child(id).updateChildren(update)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful())
                                                    {
                                                        Constants.success(EditProjectActivity.this,"Project Details Successfully Updated");
                                                        pd.dismiss();
                                                    }
                                                    else
                                                    {
                                                        Constants.error(EditProjectActivity.this,"Error : "+task.getException().getMessage());
                                                        pd.dismiss();
                                                    }
                                                }
                                            });
                                }
                                else
                                {
                                    Constants.error(EditProjectActivity.this,"Error : "+task.getException().getMessage());
                                    pd.dismiss();
                                }
                            }
                        });
            }
        });
    }

    private void materialdailog(String title , String message)
    {
        new MaterialAlertDialogBuilder(EditProjectActivity.this, R.style.RoundShapeTheme)
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

    private void showLogoDialog() {
        logoDailog logoDailog = new logoDailog(EditProjectActivity.this);
        logoDailog.setImageView(logo);
        logoDailog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==101 && resultCode==RESULT_OK)
        {
            if (data!=null)
            {
                logouri = data.getData();
                Glide.with(EditProjectActivity.this).load(logouri).into(binding.logo);
            }
        }
    }
}