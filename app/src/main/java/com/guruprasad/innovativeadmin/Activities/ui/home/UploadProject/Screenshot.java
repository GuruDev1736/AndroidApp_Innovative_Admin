package com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.system.Os;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.guruprasad.innovativeadmin.Activities.HomeActivity;
import com.guruprasad.innovativeadmin.Constants.Constants;
import com.guruprasad.innovativeadmin.Model.UploadProjectModel;
import com.guruprasad.innovativeadmin.R;
import com.guruprasad.innovativeadmin.databinding.ActivityScreenshotBinding;

import java.util.HashMap;
import java.util.Map;

public class Screenshot extends AppCompatActivity {

    ActivityScreenshotBinding binding ;
    FirebaseDatabase database ;
    FirebaseAuth auth ;

    Uri img1 , img2 , img3 , img4 , img5 ;
//    String url1 , url2 , url3 , url4 , ur5 ;
    FirebaseStorage storage ;
    public static final String update = "Update";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreenshotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();


        binding.actionbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.actionbar.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screenshot.this, HomeActivity.class));
            }
        });

        binding.actionbar.activityName.setText("ScreenShot");


        Intent intent = getIntent();
       String title = intent.getStringExtra("title");
       String reference = intent.getStringExtra("reference");
       String category = intent.getStringExtra("category");

       binding.title.setText(title);
       binding.category.setText(category);
       binding.reference.setText(reference);

       ProgressDialog pd = Constants.progress_dialog(Screenshot.this,"Please Wait","Fetching Data....");
       pd.show();

       database.getReference("Buy Project").child(reference).addValueEventListener(new ValueEventListener() {
           @SuppressLint("CheckResult")
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

               UploadProjectModel model = snapshot.getValue(UploadProjectModel.class);
               if (model!=null)
               {
                   pd.dismiss();
                   Glide.with(Screenshot.this).load(model.getImg1()).listener(new RequestListener<Drawable>() {
                       @Override
                       public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                          // Constants.error(Screenshot.this,"Failed to load image : "+e.getMessage());
                           return false;
                       }

                       @Override
                       public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                           binding.upload1.setVisibility(View.INVISIBLE);
                           binding.update1.setVisibility(View.VISIBLE);
                           return false;
                       }
                   }).placeholder(R.drawable.upload).into(binding.img1);


                   Glide.with(Screenshot.this).load(model.getImg2()).listener(new RequestListener<Drawable>() {
                       @Override
                       public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                         //  Constants.error(Screenshot.this,"Failed to load image : "+e.getMessage());
                           return false;
                       }

                       @Override
                       public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                           binding.upload2.setVisibility(View.INVISIBLE);
                           binding.update2.setVisibility(View.VISIBLE);
                           return false;
                       }
                   }).placeholder(R.drawable.upload).into(binding.img2);
                   Glide.with(Screenshot.this).load(model.getImg3()).listener(new RequestListener<Drawable>() {
                       @Override
                       public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                          // Constants.error(Screenshot.this,"Failed to load image : "+e.getMessage());
                           return false;
                       }

                       @Override
                       public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                           binding.upload3.setVisibility(View.INVISIBLE);
                           binding.update3.setVisibility(View.VISIBLE);
                           return false;
                       }
                   }).placeholder(R.drawable.upload).into(binding.img3);
                   Glide.with(Screenshot.this).load(model.getImg4()).listener(new RequestListener<Drawable>() {
                       @Override
                       public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                         //  Constants.error(Screenshot.this,"Failed to load image : "+e.getMessage());
                           return false;
                       }

                       @Override
                       public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                           binding.upload4.setVisibility(View.INVISIBLE);
                           binding.update4.setVisibility(View.VISIBLE);
                           return false;
                       }
                   }).placeholder(R.drawable.upload).into(binding.img4);
                   Glide.with(Screenshot.this).load(model.getImg5()).listener(new RequestListener<Drawable>() {
                       @Override
                       public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                          // Constants.error(Screenshot.this,"Failed to load image : "+e.getMessage());
                           return false;
                       }

                       @Override
                       public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                           binding.upload5.setVisibility(View.INVISIBLE);
                           binding.update5.setVisibility(View.VISIBLE);
                           return false;
                       }
                   }).placeholder(R.drawable.upload).into(binding.img5);

               }
               else
               {
                   Constants.error(Screenshot.this,"Model is empty please retry");
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
                Constants.error(Screenshot.this,"Error : "+error.getMessage());
           }
       });

       binding.img1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                openManager(101);
           }
       });

       binding.img2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openManager(102);
           }
       });

       binding.img3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openManager(103);
           }
       });

       binding.img4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openManager(104);
           }
       });

       binding.img5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openManager(105);
           }
       });

       binding.upload1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (img1 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");
               }
               else {
               uploadimage(title,img1,reference,"img1",category);
               }

           }
       });

       binding.upload2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (img2 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");

               }
               else
               {
                   uploadimage(title,img2,reference,"img2",category);
               }
           }
       });

       binding.upload3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (img3 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");

               }
               else
               {
                   uploadimage(title,img3,reference,"img3",category);
               }
           }
       });

       binding.upload4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (img4 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");

               }
               else
               {
                   uploadimage(title,img4,reference,"img4",category);
               }
           }
       });

       binding.upload5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (img5 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");

               }
               else
               {
                   uploadimage(title,img5,reference,"img5",category);
               }
           }
       });

       binding.update1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (img1 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");

               }
               else
               {
                   updateChildren(title,img1,reference,"img1",category);
               }
           }
       });

       binding.update2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (img2 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");

               }
               else
               {
                   updateChildren(title,img2,reference,"img2",category);
               }

           }
       });

       binding.update3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (img3 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");

               }
               else
               {
                   updateChildren(title,img3,reference,"img3",category);
               }
           }
       });


       binding.update4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (img4 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");

               }
               else
               {
                   updateChildren(title,img4,reference,"img4",category);
               }
           }
       });

       binding.update5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (img5 == null)
               {
                   Constants.error(Screenshot.this,"Please Select the image first");

               }
               else
               {
                   updateChildren(title,img5,reference,"img5",category);
               }
           }
       });
    }


    private  void updateChildren(String title , Uri image , String referenceID , String childName , String category)
    {
        ProgressDialog pd  = Constants.progress_dialog(Screenshot.this,"Please Wait", "Updating your image.... ");
        pd.show();


        final StorageReference reference = storage.getReference("Project Images").child(title).child(String.valueOf(System.currentTimeMillis()));
        reference.putFile(image).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful())
                {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            Map<String,Object> update = new HashMap<>();
                            update.put(childName,uri.toString());

                            database.getReference("Buy Project").child(referenceID).updateChildren(update)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            database.getReference(category).child(referenceID).updateChildren(update).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful())
                                                    {
                                                        Constants.success(Screenshot.this,"Screenshot Updated Successfully");
                                                        pd.dismiss();
                                                    }
                                                    else
                                                    {
                                                        Constants.error(Screenshot.this,"Failed to Update screenshot : "+task.getException().getMessage());
                                                        pd.dismiss();
                                                    }

                                                }
                                            });
                                        }
                                    });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Constants.error(Screenshot.this,"Error : "+e.getMessage());
                            pd.dismiss();
                        }
                    });
                }
                else
                {
                    Constants.error(Screenshot.this,"Error : "+task.getException().getMessage());
                    pd.dismiss();
                }
            }
        });
    }



    private void uploadimage(String title , Uri image , String referenceID , String childName , String category) {

        ProgressDialog pd  = Constants.progress_dialog(Screenshot.this,"Please Wait", "Uploading your image.... ");
        pd.show();


        final StorageReference reference = storage.getReference("Project Images").child(title).child(String.valueOf(System.currentTimeMillis()));
        reference.putFile(image).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful())
                {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference("Buy Project").child(referenceID).child(childName).setValue(uri.toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            database.getReference(category).child(referenceID).child(childName).setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful())
                                                    {
                                                        Constants.success(Screenshot.this,"Screenshot Uploaded Successfully");
                                                        pd.dismiss();
                                                    }
                                                    else
                                                    {
                                                        Constants.error(Screenshot.this,"Failed to upload screenshot : "+task.getException().getMessage());
                                                        pd.dismiss();
                                                    }

                                                }
                                            });
                                        }
                                    });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                                Constants.error(Screenshot.this,"Error : "+e.getMessage());
                                pd.dismiss();
                        }
                    });
                }
                else
                {
                    Constants.error(Screenshot.this,"Error : "+task.getException().getMessage());
                    pd.dismiss();
                }
            }
        });
    }


    private void openManager(int requestcode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select the Image."),requestcode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK)
        {
            if (data!=null)
            {
                img1 = data.getData();
                Glide.with(Screenshot.this).load(img1)
                        .into(binding.img1);
            }
        }

        if (requestCode == 102 && resultCode == RESULT_OK)
        {
            if (data!=null)
            {
                img2 = data.getData();
                Glide.with(Screenshot.this).load(img2)
                        .into(binding.img2);
            }
        }

        if (requestCode == 103 && resultCode == RESULT_OK)
        {
            if (data!=null)
            {
                img3 = data.getData();
                Glide.with(Screenshot.this).load(img3)
                        .into(binding.img3);
            }
        }

        if (requestCode == 104 && resultCode == RESULT_OK)
        {
            if (data!=null)
            {
                img4= data.getData();
                Glide.with(Screenshot.this).load(img4)
                        .into(binding.img4);
            }
        }

        if (requestCode == 105 && resultCode == RESULT_OK)
        {
            if (data!=null)
            {
                img5 = data.getData();
                Glide.with(Screenshot.this).load(img5)
                        .into(binding.img5);
            }
        }
    }
}