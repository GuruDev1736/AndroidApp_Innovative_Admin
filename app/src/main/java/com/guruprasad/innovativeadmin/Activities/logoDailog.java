package com.guruprasad.innovativeadmin.Activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.guruprasad.innovativeadmin.R;

public class logoDailog extends Dialog {

    ImageView imageView ;
    ProgressBar progressBar ;


    public logoDailog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.logodialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imageView = findViewById(R.id.logoimg);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

    }

    public void setImageView(String url )
    {

        Glide.with(getContext()).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.INVISIBLE);
                return false;
            }
        }).placeholder(R.drawable.img).into(imageView);
    }

}
