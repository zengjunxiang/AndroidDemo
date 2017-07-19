package com.zengjunxiang.androiddemo.wxapi;

import android.content.Context;
import android.text.TextUtils;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zengjunxiang.androiddemo.IPay;
import com.zengjunxiang.androiddemo.PayResultCallBack;
import com.zengjunxiang.androiddemo.PayType;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ZJX on 2017/7/19.
 */

public class WxPay implements IPay {

    private static String sAppId;

    private static WxPay sWxPay;

    private IWXAPI mWXApi;
    private String mPayParam;
    private PayReq mPayReq;
    private CallBackProxy mCallbackProxy;

    private boolean mUseStringParam = true;


    public WxPay(Context context, String payParam, PayResultCallBack callBack) {
        mWXApi = WXAPIFactory.createWXAPI(context, null);
        mWXApi.registerApp(sAppId);

        mPayParam = payParam;
        mCallbackProxy = new CallBackProxy(callBack);

        mUseStringParam = true;

        sWxPay = this;
    }

    public WxPay(Context context, PayReq payReq, PayResultCallBack callBack) {
        mWXApi = WXAPIFactory.createWXAPI(context, null);
        mWXApi.registerApp(sAppId);

        mPayReq = payReq;
        mCallbackProxy = new CallBackProxy(callBack);

        mUseStringParam = false;

        sWxPay = this;
    }



    public static String getsAppId() {
        return sAppId;
    }

    public static void setsAppId(String sAppId) {
        WxPay.sAppId = sAppId;
    }

    public static WxPay getCurrentIns() {
        return sWxPay;
    }



    @Override
    public void doPay() {

        //检测是否支持微信支付
        if (!check()) {
            mCallbackProxy.onError(PayType.WX, PayResultCallBack.NO_OR_LOW_WX, PayResultCallBack.NO_OR_LOW_WX);

            return;
        }

        //拼接订单参数
        PayReq req;

        if (mUseStringParam){ //使用字符串参数
            JSONObject param;
            try {
                param = new JSONObject(mPayParam);
            } catch (JSONException e) {
                e.printStackTrace();
                mCallbackProxy.onError(PayType.WX, PayResultCallBack.ERROR_PAY_PARAM, PayResultCallBack.ERROR_PAY_PARAM);

                return;
            }

            if (TextUtils.isEmpty(param.optString("appid")) || TextUtils.isEmpty(param.optString("partnerid"))
                    || TextUtils.isEmpty(param.optString("prepayid")) || TextUtils.isEmpty(param.optString("packageValue")) ||
                    TextUtils.isEmpty(param.optString("noncestr")) || TextUtils.isEmpty(param.optString("timestamp")) ||
                    TextUtils.isEmpty(param.optString("sign"))) {

                mCallbackProxy.onError(PayType.WX, PayResultCallBack.ERROR_PAY_PARAM, PayResultCallBack.ERROR_PAY_PARAM);
                return;
            }

            req = new PayReq();
            req.appId = param.optString("appid");
            req.partnerId = param.optString("partnerid");
            req.prepayId = param.optString("prepayid");
            req.packageValue = param.optString("packageValue");
            req.nonceStr = param.optString("noncestr");
            req.timeStamp = param.optString("timestamp");
            req.sign = param.optString("sign");

        } else {
            req = mPayReq;
        }

        mWXApi.sendReq(req);


    }


    //检测是否支持微信支付
    private boolean check() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }



    private static class CallBackProxy {
        PayResultCallBack callBack;

        CallBackProxy(PayResultCallBack callBack) {
            this.callBack = callBack;
        }

        void onCancel(PayType type) {
            if (callBack != null) {
                callBack.onPayCancel(type);
            }
            recycle();
        }

        void onError(PayType type, String errorCode, String rawErrorCode) {
            if (callBack != null) {
                callBack.onPayError(type, errorCode, rawErrorCode);
            }
            recycle();
        }

        void onDealing(PayType type) {
            if (callBack != null) {
                callBack.onPayDealing(type);
            }
            recycle();
        }

        void onSuccess(PayType type) {
            if (callBack != null) {
                callBack.onPaySuccess(type);
            }
            recycle();
        }
    }

    public static void recycle() {
        sWxPay = null;
    }


    //支付回调响应
    void onResp(int error_code) {
        if (error_code == 0) {   //成功
            mCallbackProxy.onSuccess(PayType.WX);
        }else if (error_code == -2) {   //取消
            mCallbackProxy.onCancel(PayType.WX);
        } else { //失败
            mCallbackProxy.onError(PayType.WX, PayResultCallBack.ERROR_PAY, error_code+"");
        }
    }

}
