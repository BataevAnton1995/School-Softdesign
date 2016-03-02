package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.managers.storage.models.ModelUser;
import com.softdesign.school.data.managers.storage.models.User;

import java.util.ArrayList;
import java.util.List;


public class RecycleUserAdapter extends RecyclerView.Adapter<UserAdapterHolder>{


    List<User> users;

    public RecycleUserAdapter(List<User> users) {
        this.users= users;
    }

    @Override
    public UserAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.item_user_card,parent,false);
        return new UserAdapterHolder(convertView);
    }

    @Override
    public void onBindViewHolder(UserAdapterHolder holder, int position) {
        User user = users.get(position);
        holder.setUser(user);
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