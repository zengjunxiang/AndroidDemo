package com.zengjunxiang.androiddemo.alipay;

import android.text.TextUtils;

import com.zengjunxiang.androiddemo.alipay.util.OrderInfoUtil2_0;
import com.zengjunxiang.androiddemo.alipay.util.SignUtils;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * Created by ZJX on 2017/7/18.
 */

public class AliLocalParamCreator {

    /** 支付宝支付业务：入参app_id 商户签约拿到的app_id，如：2013081700024223 */
    public static  String APPID = "2013081700024223";

    /** 支付宝账户登录授权业务：入参pid值  商户签约拿到的pid，如：2088102123816631*/

    public  static String PID = "2088102123816631";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public   String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static String RSA2_PRIVATE = "";
    public static String RSA_PRIVATE = "dfgfgfdfdgfgdfQAB";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    /**
     * 商户私钥，pkcs8格式
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个
     * RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE
     * 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成
     * <p>
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */

    public static void init( String appid,String pid,String ras_private){
        APPID=appid;
        PID=pid;
        RSA_PRIVATE=ras_private;
    };


    public  static void init_v2( String appid,String pid,String ras2_private){
        APPID=appid;
        PID=pid;
        RSA2_PRIVATE=ras2_private;
    };



    public static String create( String subject, String body, String total_amount, String notifyUrl, String outTradeNo,boolean rsa2) {
        // 订单
        Map orderInfo = localGenOrderInfo(subject, body, total_amount, notifyUrl,outTradeNo,rsa2);

        // 对订单做RSA 签名
        String sign = localSign(String.valueOf(orderInfo));
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        return orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
    }



    private static String getSignType() {
        return "sign_type=\"RSA\"";
    }


    private static String localSign(String orderInfo) {
        return SignUtils.sign(orderInfo, RSA_PRIVATE);

    }

    private static Map<String, String> localGenOrderInfo( String subject, String body, String total_amount, String notifyUrl, String outTradeNo, boolean rsa2) {
        Map<String, String> keyValues = new HashMap<String, String>();

        keyValues.put("biz_content", "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"0.01\",\"subject\":\"1\",\"body\":\"我是测试数据\",\"out_trade_no\":\"" + getOutTradeNo() +  "\"}");

        keyValues.put("charset", "utf-8");

        keyValues.put("method", "alipay.trade.app.pay");

        keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

        keyValues.put("timestamp", "2016-07-29 16:55:53");

        keyValues.put("version", "1.0");

        return keyValues;
    }


    public static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }


}
