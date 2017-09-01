package com.zengjunxiang.androiddemo.layer4_buttom_tab;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zengjunxiang.androiddemo.layer4_buttom_tab.activity.BottomNavigationViewActivity;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.activity.CustomTabViewActivity;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.activity.FragmentTabHostActivity;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.activity.RadioGroupTabActivity;
import com.zengjunxiang.androiddemo.layer4_buttom_tab.activity.TabLayoutActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    private   Button mRgbtn;
    private   Button mtabHostBtn;
    private   Button mTabLayoutBtn;
    private   Button mBottomNagitionViewBtn;
    private   Button mCustomTabViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initView();
       initEvent();

    }

    private void initEvent() {
        mRgbtn.setOnClickListener(this);
        mtabHostBtn.setOnClickListener(this);
        mTabLayoutBtn.setOnClickListener(this);
        mBottomNagitionViewBtn.setOnClickListener(this);
        mCustomTabViewBtn.setOnClickListener(this);
    }

    private void initView() {

         mRgbtn= (Button) findViewById(R.id.activity_main_rgBtn);
         mtabHostBtn=(Button)findViewById(R.id.activity_main_tabHostBtn);
         mTabLayoutBtn= (Button) findViewById(R.id.activity_main_tablayoutBtn);
         mBottomNagitionViewBtn=(Button)findViewById(R.id.activity_main_bottomNagitionViewBtn);
        mCustomTabViewBtn = (Button) findViewById(R.id.activity_main_customTabViewBtn);


    }

    @Override
    public void onClick(View v) {

        int id=v.getId();

        switch (id){

            case R.id.activity_main_rgBtn:

                startActivity(new Intent(this,RadioGroupTabActivity.class));
                break;

            case R.id.activity_main_tabHostBtn:

                startActivity(new Intent(this,FragmentActivity.class));
                break;


            case R.id.activity_main_tablayoutBtn:

                startActivity(new Intent(this,TabLayoutActivity.class));
                break;

            case R.id.activity_main_bottomNagitionViewBtn:

                startActivity(new Intent(this,BottomNavigationViewActivity.class));

                break;

            case R.id.activity_main_customTabViewBtn:

                startActivity(new Intent(this,CustomTabViewActivity.class));

                break;
        }
    }
}
