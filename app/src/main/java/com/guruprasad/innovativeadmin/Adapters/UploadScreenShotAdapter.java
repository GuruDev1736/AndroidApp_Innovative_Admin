package com.guruprasad.innovativeadmin.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textview.MaterialTextView;
import com.guruprasad.innovativeadmin.Activities.logoDailog;
import com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject.Screenshot;
import com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject.UploadScreenShot;
import com.guruprasad.innovativeadmin.Constants.Constants;
import com.guruprasad.innovativeadmin.Model.UploadProjectModel;
import com.guruprasad.innovativeadmin.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UploadScreenShotAdapter extends FirebaseRecyclerAdapter<UploadProjectModel, UploadScreenShotAdapter.onviewholder> {


    public UploadScreenShotAdapter(@NonNull FirebaseRecyclerOptions<UploadProjectModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull onviewholder holder, int position, @NonNull UploadProjectModel model) {
        if (model!=null)
        {
            holder.title.setText(model.getTitle());
            holder.price.setText(model.getPrice());
            holder.category.setText(model.getCategory());
            Glide.with(holder.itemView.getContext()).load(model.getLogourl()).placeholder(R.drawable.img).into(holder.logo);
        }
        else
        {
            Constants.error(holder.itemView.getContext(),"Doesn't find any thing");
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), Screenshot.class)
                        .putExtra("title",model.getTitle())
                        .putExtra("reference",model.getProjecId())
                        .putExtra("category",model.getCategory())
                );
            }
        });

        holder.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoDailog logoDailog = new logoDailog(holder.itemView.getContext());
                logoDailog.setImageView(model.getLogourl());
                logoDailog.show();
            }
        });

    }

    @NonNull
    @Override
    public onviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.screenshotviewlayout,parent,false);
       return new onviewholder(view);
    }

    public class onviewholder extends RecyclerView.ViewHolder {

        MaterialTextView title , price , category ;
        CircleImageView logo ;

        CardView cardView ;
        ImageButton button ;

        public onviewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.projectTitle);
            price = itemView.findViewById(R.id.price);
            category = itemView.findViewById(R.id.project_category);
            logo = itemView.findViewById(R.id.logo);
            cardView = itemView.findViewById(R.id.cardview);
            button = itemView.findViewById(R.id.imgbutton);
        }
    }
}
