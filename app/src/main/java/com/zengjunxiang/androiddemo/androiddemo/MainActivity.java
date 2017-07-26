package com.zengjunxiang.androiddemo.androiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zengjunxiang.androiddemo.androiddemo.ui.layer1.GlideDemoActivity;
import com.zengjunxiang.androiddemo.androiddemo.ui.layer1.OkHttpDemoActivity;
import com.zengjunxiang.androiddemo.androiddemo.ui.layer2.PayActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnPay;
    private Button mbtnOkHttp;
    private Button mbtnGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();
    }

    private void initEvent() {
        btnPay.setOnClickListener(this);
        mbtnOkHttp.setOnClickListener(this);
        mbtnGlide.setOnClickListener(this);
    }

    private void initView() {

        try {
            btnPay=(Button)findViewById(R.id.lay2_btn_pay);
            mbtnOkHttp=(Button)findViewById(R.id.lay1_btn_okhttpdemo);
            mbtnGlide=(Button)findViewById(R.id.lay1_btn_glidedemo);
            }
        catch (NullPointerException e){
            e.printStackTrace();
            Log.e("MainActivity","初始化控件异常");
        }


    }

    @Override
    public void onClick(View v) {

        int id=v.getId();

        switch (id){
            case R.id.lay2_btn_pay:
                Log.e("MainActivity","点击了支付按钮。。。。。。");

                Intent intent=new Intent(MainActivity.this, PayActivity.class);
                       startActivity(intent);

                break;

            case R.id.lay1_btn_okhttpdemo:

                Log.e("MainActivity","点击了okhttpdemo按钮");

                Intent intent1=new Intent(MainActivity.this, OkHttpDemoActivity.class);
                        startActivity(intent1);

                break;

            case R.id.lay1_btn_glidedemo:

                Log.e("MainActivity","点击了Glidedemo按钮");


                Intent intent2=new Intent(MainActivity.this, GlideDemoActivity.class);
                startActivity(intent2);

                break;

            default:
                break;

        }



    }
}
