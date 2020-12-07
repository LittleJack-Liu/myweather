package com.jie.weather.utils;

import android.content.SharedPreferences;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author panilsy@icloud.com
 * @description 获取天气助手函数
 */
public class WeatherHelper {

    private static final String TAG = "WeatherHelper";

    private String city = "110101";
    private String extensions = "all";
    private CallBack cb = null;
    private final String key = "d226ad5341f28e6bb80d510c3ed9417a";
    private final String output = "json";
    private final String baseUrl = "https://restapi.amap.com/v3/weather/weatherInfo?";

    public WeatherHelper() {

    }

    public WeatherHelper(CallBack cb) {
        this.cb = cb;
    }

    public WeatherHelper(String city, CallBack cb) {
        this.city = city;
        this.cb = cb;
    }

    public WeatherHelper(String city, String extensions, CallBack cb) {
        this.city = city;
        this.extensions = extensions;
        this.cb = cb;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    private String buildBaseUrl() {
        return this.baseUrl + "key=" + this.key + "&city=" + this.city + "&extensions=" + this.extensions + "&output=" + this.output;
    }

    public void getWeatherNotCache(SharedPreferences pref) {
        try {
            // 创建实例和请求
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(this.buildBaseUrl()).build();

            Response response = client.newCall(request).execute();
            String resData = response.body().string();

            // 缓存数据
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("lives-" + this.city, resData);
            editor.commit();

            // 回调函数
            this.cb.run(resData);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void getWeatherWithCache(SharedPreferences pref) {
        String live = pref.getString("lives-" + this.city,"");
        if (!live.equals("")) {
            this.cb.run(live);
        } else {
            this.getWeatherNotCache(pref);
        }
    }
}
