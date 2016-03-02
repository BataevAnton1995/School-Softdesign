package com.softdesign.school.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.ActiveAndroidApp;
import com.softdesign.school.ui.activities.MainActivity;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_profile,container,false);
        //ButterKnife.bind(this, mainView);
        CollapsingToolbarLayout mCollapsingToolBar = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        mCollapsingToolBar.setTitle(getResources().getString(R.string.drawer_profile));

        ((MainActivity) getActivity()).lockAppBar(false);
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.floationg_action_button);
        CoordinatorLayout.LayoutParams params=(CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        params.setAnchorId(R.id.appbar_layout);
        params.anchorGravity= Gravity.BOTTOM|Gravity.RIGHT;
        fab.setLayoutParams(params);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


      //  fab.setImageResource(R.drawable.theme_fom);
    }
}
