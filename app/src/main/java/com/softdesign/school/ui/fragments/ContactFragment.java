package com.softdesign.school.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.managers.storage.models.ModelUser;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.RecycleUserAdapter;

import java.util.ArrayList;

public class ContactFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ModelUser> mUsers = new ArrayList<ModelUser>();
    View mainView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        generateData();
        mAdapter = new RecycleUserAdapter(mUsers);
    }


    private void generateData(){
        mUsers.add(new ModelUser(getResources().getDrawable(R.drawable.tool_avatart), "Test", "Test"));
        mUsers.add(new ModelUser(getResources().getDrawable(R.drawable.tool_avatart), "Test2", "Test2"));
        mUsers.add(new ModelUser(getResources().getDrawable(R.drawable.tool_avatart), "Test3", "Test3"));
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_contact, container, false);
            getActivity().setTitle(getResources().getString(R.string.drawer_contacts));
        }
        ((MainActivity) getActivity()).lockAppBar(true);
        generateData();

        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recycle_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleUserAdapter(mUsers);
        mRecyclerView.setAdapter(mAdapter);



        return mainView;
    }


}
