package com.guruprasad.innovativeadmin.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textview.MaterialTextView;
import com.guruprasad.innovativeadmin.Activities.logoDailog;
import com.guruprasad.innovativeadmin.Activities.ui.home.UploadProject.EditProjectActivity;
import com.guruprasad.innovativeadmin.Constants.Constants;
import com.guruprasad.innovativeadmin.Model.UploadProjectModel;
import com.guruprasad.innovativeadmin.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProjectAdapter extends FirebaseRecyclerAdapter<UploadProjectModel , EditProjectAdapter.onviewholder> {

    public EditProjectAdapter(@NonNull FirebaseRecyclerOptions<UploadProjectModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull onviewholder holder, int position, @NonNull UploadProjectModel model) {

        if (model!=null)
        {
            holder.title.setText(model.getTitle());
            holder.category.setText(model.getCategory());
            holder.referenceId.setText(model.getProjecId().substring(0,12)+"....");
            Glide.with(holder.itemView.getContext()).load(model.getLogourl()).placeholder(R.drawable.img).into(holder.logo);
        }
        else
        {
            Constants.error(holder.itemView.getContext(),"No Data Found Please Try again later");
        }

        holder.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoDailog logoDailog = new logoDailog(holder.itemView.getContext());
                logoDailog.setImageView(model.getLogourl());
                logoDailog.show();
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), EditProjectActivity.class).putExtra("projectId",model.getProjecId()));
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

        CircleImageView logo ;
        MaterialTextView title , referenceId , category ;

        ImageButton button ;


        public onviewholder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.logo);
            title = itemView.findViewById(R.id.projectTitle);
            referenceId = itemView.findViewById(R.id.price);
            category = itemView.findViewById(R.id.project_category);
            button = itemView.findViewById(R.id.imgbutton);
        }
    }
}
