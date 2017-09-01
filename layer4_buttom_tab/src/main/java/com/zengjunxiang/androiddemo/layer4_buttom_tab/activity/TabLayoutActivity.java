package com.zengjunxiang.androiddemo.layer4_buttom_tab.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zengjunxiang.androiddemo.layer4_buttom_tab.R;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.AttentionFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.DisconverFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.HomeFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.ProfileFragment;


/**
 * Created by ZJX on 2017/8/31.
 */

public class TabLayoutActivity extends FragmentActivity{

  private TabLayout mTabLayout;

    private Fragment[]mFragments;

    public static final String []mTabTitle = new String[]{"首页","发现","关注","我的"};
    public static final int []mTabRes = new int[]{R.drawable.tab_home_selector,R.drawable.tab_discovery_selector,R.drawable.tab_attention_selector,R.drawable.tab_profile_selector};
    public static final int []mTabResPressed = new int[]{R.drawable.ic_tab_strip_icon_feed_selected,R.drawable.ic_tab_strip_icon_category_selected,R.drawable.ic_tab_strip_icon_pgc_selected,R.drawable.ic_tab_strip_icon_profile_selected};


    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = DisconverFragment.newInstance();
        fragments[2] = AttentionFragment.newInstance();
        fragments[3] = ProfileFragment.newInstance();
        return fragments;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_fragment_buttom_tab);

        mFragments=getFragments("");

        initView();

    }

    private void initView() {

        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        for (int i = 0; i < 4; i++) {

            android.support.design.widget.TabLayout.Tab tab = mTabLayout.newTab().setCustomView(getTabView(this, i));

            mTabLayout.addTab(tab);

        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                onTabItemSelected(tab.getPosition());

                for (int i=0;i<mTabLayout.getTabCount();i++){
                    View view = mTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                    TextView text = (TextView) view.findViewById(R.id.tab_content_text);
                    if(i == tab.getPosition()){
                        icon.setImageResource(mTabResPressed[i]);
                        text.setTextColor(getResources().getColor(android.R.color.black));
                    }else{
                        icon.setImageResource(mTabRes[i]);
                        text.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


        /**
         * 获取Tab 显示的内容
         * @param context
         * @param position
         * @return
         */
    public View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
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
