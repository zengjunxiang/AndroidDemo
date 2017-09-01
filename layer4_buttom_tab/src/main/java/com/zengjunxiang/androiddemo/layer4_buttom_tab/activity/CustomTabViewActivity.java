package com.zengjunxiang.androiddemo.layer4_buttom_tab.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.zengjunxiang.androiddemo.layer4_buttom_tab.R;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.AttentionFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.DisconverFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.HomeFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.ProfileFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.view.CustomTabView;

/**
 * Created by ZJX on 2017/9/1.
 */

public class CustomTabViewActivity  extends FragmentActivity implements CustomTabView.OnTabCheckListener{

    private Fragment[]mFragments;

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = DisconverFragment.newInstance();
        fragments[2] = AttentionFragment.newInstance();
        fragments[3] = ProfileFragment.newInstance();
        return fragments;
    }

    private CustomTabView mCustomTabView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab_layout);

        mFragments = getFragments("");
        
        intView();

    }

    private void intView() {

        mCustomTabView = (CustomTabView) findViewById(R.id.custom_tab_container);

        CustomTabView.Tab tabHome = new CustomTabView.Tab().setText("首页")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(android.R.color.black))
                .setNormalIcon(R.drawable.ic_tab_strip_icon_feed)
                .setPressedIcon(R.drawable.ic_tab_strip_icon_feed_selected);
        mCustomTabView.addTab(tabHome);
        CustomTabView.Tab tabDis = new CustomTabView.Tab().setText("发现")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(android.R.color.black))
                .setNormalIcon(R.drawable.ic_tab_strip_icon_category)
                .setPressedIcon(R.drawable.ic_tab_strip_icon_category_selected);
        mCustomTabView.addTab(tabDis);
        CustomTabView.Tab tabAttention = new CustomTabView.Tab().setText("关注")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(android.R.color.black))
                .setNormalIcon(R.drawable.ic_tab_strip_icon_pgc)
                .setPressedIcon(R.drawable.ic_tab_strip_icon_pgc_selected);
        mCustomTabView.addTab(tabAttention);
        CustomTabView.Tab tabProfile = new CustomTabView.Tab().setText("我的")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(android.R.color.black))
                .setNormalIcon(R.drawable.ic_tab_strip_icon_profile)
                .setPressedIcon(R.drawable.ic_tab_strip_icon_profile_selected);
        mCustomTabView.addTab(tabProfile);


        mCustomTabView.setOnTabCheckListener(this);
        mCustomTabView.setCurrentItem(0);


    }



    @Override
    public void onTabSelected(View v, int position) {

           onTabItemSelected(position);

    }


    private void onTabItemSelected(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = mFragments[0];
                break;
            case 1:
                fragment = mFragments[1];
                break;

            case 2:
                fragment = mFragments[2];
                break;
            case 3:
                fragment = mFragments[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }
}
