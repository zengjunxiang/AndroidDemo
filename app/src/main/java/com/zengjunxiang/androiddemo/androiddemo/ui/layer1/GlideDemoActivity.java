package com.zengjunxiang.androiddemo.androiddemo.ui.layer1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.zengjunxiang.androiddemo.androiddemo.R;

/**
 * Created by ZJX on 2017/7/26.
 */

public class GlideDemoActivity extends Activity implements View.OnClickListener{

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private ImageView mImageView;
    private RequestManager requestManger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layer1_glidedemo);

        requestManger = Glide.with(this);

        initView();

        initEvent();

//      initData();

    }

    private void initData() {

    }

    private void initEvent() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

    }

    private void initView() {

        try{
            mButton1=(Button)findViewById(R.id.activity_layer1_glide_btn_1);
            mButton2=(Button)findViewById(R.id.activity_layer1_glide_btn_2);
            mButton3=(Button)findViewById(R.id.activity_layer1_glide_btn_3);
            mButton4=(Button)findViewById(R.id.activity_layer1_glide_btn_4);
            mImageView=(ImageView)findViewById(R.id.activity_layer1_glide_iv);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {

        int id=v.getId();

        switch (id){

            case R.id.activity_layer1_glide_btn_1:
                Log.e("GlideDemoActivity","btn1被点击");

                requestManger.load("https://www.baidu.com/img/bdlogo.png").into(mImageView);

                break;

            case R.id.activity_layer1_glide_btn_2:
                Log.e("GlideDemoActivity","btn2被点击");


                break;

            case R.id.activity_layer1_glide_btn_3:
                Log.e("GlideDemoActivity","btn3被点击");
                break;

            case R.id.activity_layer1_glide_btn_4:
                Log.e("GlideDemoActivity","btn4被点击");
                break;

            default:

                break;
        }
    }
}
