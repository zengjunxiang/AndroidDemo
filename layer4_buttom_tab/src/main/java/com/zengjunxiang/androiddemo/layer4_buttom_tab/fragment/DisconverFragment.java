package com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zengjunxiang.androiddemo.layer4_buttom_tab.R;

/**
 * Created by ZJX on 2017/8/30.
 */

public class DisconverFragment extends Fragment{

    public static DisconverFragment newInstance(){

        DisconverFragment disconverFragment = new DisconverFragment();

        return disconverFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

                View view =inflater.inflate(R.layout.fragment_discovery_layout,null);

                return view;
    }
}
