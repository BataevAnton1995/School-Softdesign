package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.managers.storage.models.ModelUser;

import java.util.ArrayList;


public class RecycleUserAdapter extends RecyclerView.Adapter<UserViewHolder>{


    ArrayList<ModelUser> users;

    public RecycleUserAdapter(ArrayList<ModelUser> users) {
        this.users= users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.item_user_card,parent,false);
        return new UserViewHolder(convertView);
    }


    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        ModelUser user = users.get(position);
        holder.fullName.setText(user.getmFirstName()+" "+user.getmLastName());
        holder.avatar.setImageDrawable(user.getmImage());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


}