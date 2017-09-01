package com.zengjunxiang.androiddemo.androiddemo.ui.layer1;

import android.app.Activity;
import android.icu.util.TimeUnit;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zengjunxiang.androiddemo.androiddemo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ZJX on 2017/7/21.
 */

public class OkHttpDemoActivity  extends Activity implements View.OnClickListener{

    public static final String GET_URL = "http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&index=1&size=60&bigid=0";
    public static final String POST_URL = "http://zhushou.72g.com/app/gift/gift_list/";
    //    请求条件：platform=2&gifttype=2&compare=60841c5b7c69a1bbb3f06536ed685a48

    private Button mPostBtn;
    private Button mGetBtn;
    private OkHttpClient client;
    private TextView mTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layer1_okhttpdemo);

        initView();

        initEvent();

        initData();

    }



    private void initData() {
        initOkHttpClient();

    }

    private void initOkHttpClient() {

        client = new OkHttpClient.Builder()
                .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .build();
    }

    private void initEvent() {
        mGetBtn.setOnClickListener(this);
        mPostBtn.setOnClickListener(this);
    }

    private void initView() {

        mGetBtn= (Button)findViewById(R.id.activity_layer1_okhttp_get);
        mPostBtn= (Button)findViewById(R.id.activity_layer1_okhttp_post);
        mTextView=(TextView)findViewById(R.id.activity_layer1_okhttp_tv_show);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        switch (id){
            case R.id.activity_layer1_okhttp_get:
                Log.e("OkHttpDemoActivity","点击了okhttpdemo get按钮");
                okHttpGet();

                break;

            case R.id.activity_layer1_okhttp_post:

                Log.e("OkHttpDemoActivity","点击了okhttpdemo post按钮");

                okHttpPost();

                break;
        }
    }

    private void okHttpPost() {

        RequestBody requestBody=new FormBody.Builder()
                                    .add("page","1")
                                    .add("code", "news")
                                    .add("pageSize", "20")
                                    .add("parentid", "0")
                                     .add("type", "1")
                                     .build();


        Request request=new Request.Builder()
                            .url(POST_URL)
                            .post(requestBody)
                            .build();

        client.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string=response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(OkHttpDemoActivity.this,"POST请求回调成功",Toast.LENGTH_LONG).show();
                        Log.e("OkHttp__onResponse___","string:"+string);
                        mTextView.setText(string);

                    }
                });

            }
        });


    }

    private void okHttpGet() {

        Request request = new Request
                           .Builder()
                           .get()
                           .url(GET_URL)
                           .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("client newCall ",".....请求回调失败，，，，，");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(OkHttpDemoActivity.this,"GET请求回调成功",Toast.LENGTH_LONG).show();
                                Log.e("OkHttp__onResponse___","string:"+string);
                                mTextView.setText(string);
                            }
                        });

            }
        });

    }
}
