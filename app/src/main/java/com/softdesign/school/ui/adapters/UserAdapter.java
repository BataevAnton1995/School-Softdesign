package com.softdesign.school.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.softdesign.school.R;
import com.softdesign.school.data.managers.storage.models.ModelUser;
import com.softdesign.school.data.managers.storage.models.User;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends BaseAdapter{

    Context mContext;
    List<User> mUser;
    LayoutInflater mInflater;

    public UserAdapter(List<User> mUser) {
        this.mContext = mContext;
        this.mUser = mUser;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mUser.size();
    }

    @Override
    public Object getItem(int position) {
        return mUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = mInflater.inflate(R.layout.item_user_card, parent, false);
        }

        User user = (User) getItem(position);
       // ImageView avatar = (ImageView) itemView.findViewById(R.id.user_avatar);
       // avatar.setImageDrawable(user.getImage());
        TextView teamName = (TextView) itemView.findViewById(R.id.text_name_teams);
        TextView fullName = (TextView) itemView.findViewById(R.id.full_name);
        fullName.setText(user.getFirstName() + " " + user.getLastName());
        teamName.setText(user.getTeams());

        return itemView;
    }
}