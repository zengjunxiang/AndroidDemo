package com.zengjunxiang.androiddemo.layer4_buttom_tab.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


import com.zengjunxiang.androiddemo.layer4_buttom_tab.R;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.AttentionFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.DisconverFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.HomeFragment;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.fragment.ProfileFragment;


/**
 * Created by ZJX on 2017/8/31.
 */



public class FragmentTabHostActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{

    private FragmentTabHost mTabHost;

    private Fragment []mFragments;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_fragmenttabhost_fragment_buttom_tab);

        mFragments=getFragments("");

        initView();


    }

    private void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        // 关联TabHost
        mTabHost.setup(this,getSupportFragmentManager(),R.id.home_container);
        //注意，监听要设置在添加Tab之前
        mTabHost.setOnTabChangedListener(this);


        for(int i=0; i<4;i++){

            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabTitle[i]).setIndicator(getTabView(this,i));

            mTabHost.addTab(tabSpec, mFragments[i].getClass(),null);

        }
//
//        TabHost.TabSpec tabSpec1 = mTabHost.newTabSpec("首页").setIndicator("首页1");
//        mTabHost.addTab(tabSpec1,HomeFragment.newInstance("from").getClass(),null);


        //去掉Tab 之间的分割线
        mTabHost.getTabWidget().setDividerDrawable(null);

        //
        mTabHost.setCurrentTab(0);


    }

    @Override
    public void onTabChanged(String tabId) {

        updateTabState();
    }



    /**
     * 更新Tab 的状态
     */
    private void updateTabState(){
        TabWidget tabWidget = mTabHost.getTabWidget();
        for (int i=0;i<tabWidget.getTabCount();i++){
            View view = tabWidget.getChildTabViewAt(i);
            ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
           TextView  tabText = (TextView) view.findViewById(R.id.tab_content_text);
            if(i == mTabHost.getCurrentTab()){
               tabIcon.setImageResource(mTabResPressed[i]);
                tabText.setTextColor(getResources().getColor(android.R.color.black));
            }else{
                tabIcon.setImageResource(mTabRes[i]);
               tabText.setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
        }
    }




    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public  View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }

}
