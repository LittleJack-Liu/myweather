package com.jie.weather.utils;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author panilsy@icloud.com
 * @description 区县获取帮助类，封装请求
 */
public class DistrictHelper {
    private static final String TAG = "DistrictHelper";

    private String keywords = "";
    private String subdistrict = "1";
    private String offset = "100";

    private CallBack cb = null;
    private final String key = "d226ad5341f28e6bb80d510c3ed9417a";
    private final String output = "json";
    private final String baseUrl = "https://restapi.amap.com/v3/config/district?";

    public DistrictHelper() {
    }

    public DistrictHelper(CallBack cb) {
        this.cb = cb;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public CallBack getCb() {
        return cb;
    }

    public void setCb(CallBack cb) {
        this.cb = cb;
    }

    public String getKey() {
        return key;
    }

    public String getOutput() {
        return output;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @author panilsy@icloud.com
     * @description 编译基础地址
     * @return
     */
    private String buildBaseUrl() {
        if (this.keywords.equals("")) {
            return this.baseUrl + "key=" + this.key + "&subdistrict=" + this.subdistrict + "&offset=" + this.offset + "&output=" + this.output;

        } else {
            return this.baseUrl + "key=" + this.key + "&subdistrict=" + this.subdistrict + "&offset=" + this.offset + "&output=" + this.output + "&keywords=" + this.keywords;

        }
    }

    /**
     * @author panilsy@icloud.com
     * @description 获取省
     */
    public void getFirstDistrict() {
        try {
            // 创建实例和请求
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(this.buildBaseUrl()).build();

            Response response = client.newCall(request).execute();
            String resData = response.body().string();
            Log.d(TAG, resData);
            // 回调函数
            this.cb.run(resData);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * @author panilsy@icloud.com
     * @description 获取地级市
     * @param code
     */
    public void getSecondDistrict(String code) {
        this.subdistrict = "1";
        this.keywords = code;
        try {
            // 创建实例和请求
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(this.buildBaseUrl()).build();

            Response response = client.newCall(request).execute();
            String resData = response.body().string();
            Log.d(TAG, resData);
            // 回调函数
            this.cb.run(resData);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}
