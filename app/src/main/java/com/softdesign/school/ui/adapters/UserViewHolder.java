package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.school.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class UserViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.full_name) TextView fullName;


    public UserViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

