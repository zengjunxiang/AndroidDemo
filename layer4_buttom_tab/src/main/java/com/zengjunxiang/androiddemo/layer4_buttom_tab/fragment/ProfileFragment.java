package com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zengjunxiang.androiddemo.layer4_buttom_tab.R;


public class ProfileFragment  extends android.support.v4.app.Fragment {



    public static ProfileFragment newInstance(){

        ProfileFragment disconverFragment = new ProfileFragment();

        return disconverFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_layout,null);
        return  view;
    }
}
