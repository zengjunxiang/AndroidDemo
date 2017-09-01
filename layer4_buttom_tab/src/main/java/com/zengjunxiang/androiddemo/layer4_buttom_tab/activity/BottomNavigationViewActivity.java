package com.zengjunxiang.androiddemo.layer4_buttom_tab.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.zengjunxiang.androiddemo.layer4_buttom_tab.R;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.AttentionFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.DisconverFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.HomeFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.ProfileFragment;

/**
 * Created by ZJX on 2017/9/1.
 */

public class BottomNavigationViewActivity extends FragmentActivity {


    private Fragment[]mFragments;


    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = DisconverFragment.newInstance();
        fragments[2] = AttentionFragment.newInstance();
        fragments[3] = ProfileFragment.newInstance();
        return fragments;
    }

    private BottomNavigationView mBottomNavigation;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnavigationview_fragment_bottom_tab);

        mFragments=getFragments("");

        initView();


    }

    private void initView() {

        mBottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);


        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onTabItemSelected(item.getItemId());

                return true;
            }
        });

        // 由于第一次进来没有回调onNavigationItemSelected，因此需要手动调用一下切换状态的方法
        onTabItemSelected(R.id.tab_menu_home);
    }


    private void onTabItemSelected(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.tab_menu_home:
                fragment = mFragments[0];
                break;
            case R.id.tab_menu_discovery:
                fragment = mFragments[1];
                break;

            case R.id.tab_menu_attention:
                fragment = mFragments[2];
                break;
            case R.id.tab_menu_profile:
                fragment = mFragments[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }
}
