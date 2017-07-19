package com.zengjunxiang.androiddemo.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	

	// IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;



	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
    	api = WXAPIFactory.createWXAPI(this, WxPay.getsAppId(), false);
		api.handleIntent(getIntent(), this);

    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		setIntent(intent);
        api.handleIntent(intent, this);
	}


	@Override
	public void onReq(BaseReq baseReq) {

	}

	@Override
	public void onResp(BaseResp baseResp) {

		if(baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

			if(WxPay.getCurrentIns() != null) {
				if(baseResp.errStr != null) {
					Log.e("wxpay", "errstr=" + baseResp.errStr);
				}
				WxPay.getCurrentIns().onResp(baseResp.errCode);
			}
			finish();
		}

	}
}