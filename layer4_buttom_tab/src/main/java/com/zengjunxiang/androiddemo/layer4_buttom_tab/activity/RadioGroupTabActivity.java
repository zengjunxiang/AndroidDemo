package com.zengjunxiang.androiddemo.layer4_buttom_tab.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;


import com.zengjunxiang.androiddemo.layer4_buttom_tab.R;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.AttentionFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.DisconverFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.HomeFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.ProfileFragment;

/**
 * Created by ZJX on 2017/8/30.
 */

public class RadioGroupTabActivity  extends FragmentActivity{

 private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiogroup_fragment_buttom_tab);

        initView();

        initEvent();


    }

    private void initEvent() {

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            Fragment mFragment = null;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_home:

                      mFragment = HomeFragment.newInstance("from");

                        break;


                    case R.id.radio_button_discovery:

                        mFragment = DisconverFragment.newInstance();

                        break;


                    case R.id.radio_button_attention:

                        mFragment = AttentionFragment.newInstance();

                        break;


                    case R.id.radio_button_profile:

                        mFragment = ProfileFragment.newInstance();

                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.home_container,mFragment).commit();

            }
        });

    }

    private void initView() {

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_button);

    }
}
