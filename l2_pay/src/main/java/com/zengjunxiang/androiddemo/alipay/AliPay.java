package com.zengjunxiang.androiddemo.alipay;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.zengjunxiang.androiddemo.IPay;
import com.zengjunxiang.androiddemo.PayResultCallBack;
import com.zengjunxiang.androiddemo.PayType;

import java.util.Map;

/**
 * Created by ZJX on 2017/7/18.
 */

public class AliPay implements IPay{

    private String mParams;
    private PayTask mPayTask;
    private PayResultCallBack mCallback;


    public AliPay(Activity context, String params,  PayResultCallBack callback) {
        mParams = params;
        mCallback = callback;
        mPayTask = new PayTask(context);
    }




    @Override
    public void doPay() {

        final Handler handler=new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {

                    final Map<String, String> pay_result = mPayTask.payV2(mParams,true);

                    @Override
                    public void run() {

                        String resultStatus = pay_result.get("resultStatus");
                        if(TextUtils.equals(resultStatus, "9000")) {    //支付成功
                            mCallback.onPaySuccess(PayType.ALI);
                        } else if(TextUtils.equals(resultStatus, "8000")) { //支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            mCallback.onPayDealing(PayType.ALI);
                        } else if(TextUtils.equals(resultStatus, "6001")) {		//支付取消
                            mCallback.onPayCancel(PayType.ALI);
                        } else if(TextUtils.equals(resultStatus, "6002")) {     //网络连接出错
                            mCallback.onPayError(PayType.ALI, PayResultCallBack.ERROR_NETWORK, resultStatus);
                        } else {        //支付错误
                            mCallback.onPayError(PayType.ALI, PayResultCallBack.ERROR_PAY, resultStatus);
                        }

                    }
                });

            }
        }).start();


    }
}
