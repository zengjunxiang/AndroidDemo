package com.zengjunxiang.androiddemo.androiddemo.ui.lay2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zengjunxiang.androiddemo.PayResultCallBack;
import com.zengjunxiang.androiddemo.PayType;
import com.zengjunxiang.androiddemo.PayUtil;
import com.zengjunxiang.androiddemo.alipay.AliLocalParamCreator;
import com.zengjunxiang.androiddemo.alipay.AliPay;
import com.zengjunxiang.androiddemo.androiddemo.R;
import com.zengjunxiang.androiddemo.wxapi.WxPay;

/**
 * Created by ZJX on 2017/7/18.
 */

public class PayActivity extends Activity implements View.OnClickListener,PayResultCallBack{

    Button btnAliPay;
    private Button btnWxPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay2_pay);

        AliLocalParamCreator.init("23234","23r234","234r23423423dsfasdf");
        Log.e("PayActivity","支付appid等基础信息完成。。。。");

        //初始化微信
        PayUtil.initWx("wx_appid");
        Log.e("PayActivity","微信支付appid等基础信息完成。。。。");

        initView();

        initEvent();
    }

    private void initEvent() {

        btnAliPay.setOnClickListener(this);
        btnWxPay.setOnClickListener(this);
    }


    private void initView() {
        btnAliPay= (Button) findViewById(R.id.btn_alipay);
        btnWxPay=(Button)findViewById(R.id.btn_wx_pay);


    }


    @Override
    public void onClick(View v) {

        int id=v.getId();

        switch (id){

            case R.id.btn_alipay:

                Log.e("PayActivity","支付宝按钮被点击");
                String payParams =AliLocalParamCreator.create("dsfasd","dsfsdfsa","0.01",null,"20121212",false);

                PayUtil.pay(this, PayType.ALI,payParams,this);

                break;

            case R.id.btn_wx_pay:
                Log.e("PayActivity","微信按钮被点击");


                //模拟从服务器获取
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String jsonParam = mockGetWxParamFromNetWork();

                        Looper.prepare();

                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                //使用字符串参数需要 字符串 中存在 如 mockGetWxParamFromNetWork() 所列字段
                                PayUtil.pay(PayActivity.this, PayType.WX, jsonParam, PayActivity.this);

                                //如果使用自己构造的 PayReq
                                //PayReq payReq = new PayReq();
                                //payReq.appId = "wxd930ea5d5a258f4f";
                                //payReq.partnerId = "1900000109";
                                //payReq.prepayId= "1101000000140415649af9fc314aa427",;
                                //payReq.packageValue = "Sign=WXPay";
                                //payReq.nonceStr= "1101000000140429eb40476f8896f4c9";
                                //payReq.timeStamp= "1398746574";
                                //payReq.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
                                //PayUtil.wxPay(DemoActivity.this, payReq, DemoActivity.this);
                            }
                        });
                        Looper.loop();

                    }
                }).start();


                break;

            default:
                break;
        }


    }



    //模拟从服务器获取微信支付参数, json格式
    private String mockGetWxParamFromNetWork() {
        StringBuilder builder = new StringBuilder();
        builder.append("{")
                .append("\"sign\":\"7FFECB600D7157C5AA49810D2D8F28BC2811827B\"").append(",")
                .append("\"timestamp\":\"1398746574\"").append(",")
                .append("\"partnerid\":\"1900000109\"").append(",")
                .append("\"noncestr\":\"1101000000140429eb40476f8896f4c9\"").append(",")
                .append("\"prepayid\":\"1101000000140415649af9fc314aa427\"").append(",")
                .append("\"packageValue\":\"Sign=WXPay\"").append(",")
                .append("\"appid\":\"wxd930ea5d5a258f4f\"")
                .append("}");

        try {
            Thread.sleep(2000);//模拟延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }



    @Override
    public void onPaySuccess(PayType type) {

    }

    @Override
    public void onPayDealing(PayType type) {

    }

    @Override
    public void onPayError(PayType type, String errorCode, String rawErrorCode) {

    }

    @Override
    public void onPayCancel(PayType type) {

    }
}
