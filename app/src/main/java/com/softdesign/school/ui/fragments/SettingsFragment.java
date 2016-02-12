package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.MainActivity;

import butterknife.ButterKnife;


public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_settings,container,false);
        CollapsingToolbarLayout mCollapsingToolBar = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        mCollapsingToolBar.setTitle(getResources().getString(R.string.drawer_setting));
       // ButterKnife.bind(this, mainView);
        ((MainActivity) getActivity()).lockAppBar(true);
        return mainView;
    }
}
