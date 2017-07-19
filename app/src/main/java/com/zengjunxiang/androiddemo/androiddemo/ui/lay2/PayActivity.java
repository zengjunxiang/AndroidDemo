package com.zengjunxiang.androiddemo.androiddemo.ui.lay2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zengjunxiang.androiddemo.PayResultCallBack;
import com.zengjunxiang.androiddemo.PayType;
import com.zengjunxiang.androiddemo.PayUtil;
import com.zengjunxiang.androiddemo.alipay.AliLocalParamCreator;
import com.zengjunxiang.androiddemo.alipay.AliPay;
import com.zengjunxiang.androiddemo.androiddemo.R;

/**
 * Created by ZJX on 2017/7/18.
 */

public class PayActivity extends Activity implements View.OnClickListener,PayResultCallBack{

    Button btnAliPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay2_pay);

        initView();

        initEvent();
    }

    private void initEvent() {

        btnAliPay.setOnClickListener(this);
    }


    private void initView() {
        btnAliPay= (Button) findViewById(R.id.btn_alipay);

    }


    @Override
    public void onClick(View v) {

        int id=v.getId();

        switch (id){

            case R.id.btn_alipay:

                Log.e("PayActivity","支付宝按钮被点击");
                AliLocalParamCreator.init("23234","23r234","234r23423423dsfasdf");
                Log.e("PayActivity","支付appid等基础信息完成。。。。");
                String payParams =AliLocalParamCreator.create("dsfasd","dsfsdfsa","0.01",null,"20121212",false);

                PayUtil.pay(this, PayType.ALI,payParams,this);

                break;

            default:
                break;
        }


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
