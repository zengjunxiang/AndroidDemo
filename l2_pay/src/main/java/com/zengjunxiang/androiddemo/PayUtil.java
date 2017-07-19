package com.zengjunxiang.androiddemo;

import android.app.Activity;

import com.zengjunxiang.androiddemo.alipay.AliPay;
import com.zengjunxiang.androiddemo.wxapi.WxPay;

/**
 * Created by ZJX on 2017/7/18.
 */

public class PayUtil {

    public static void pay(Activity activity, PayType type, String payParam, PayResultCallBack callBack){
        switch (type){
            case ALI:
                new AliPay(activity, payParam, callBack).doPay();
                break;

            case WX:
                new WxPay(activity,payParam,callBack).doPay();

                break;

        }
    }

    public static void aliPay(Activity activity, String payParam, PayResultCallBack callBack){
        new AliPay(activity, payParam, callBack).doPay();
    }

    public static void initWx(String wx_appid) {
        WxPay.setsAppId(wx_appid);
    }
}
