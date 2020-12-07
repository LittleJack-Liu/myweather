package com.jie.weather.utils;

import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jie.weather.weather.Cast;
import com.jie.weather.weather.Forecasts;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author panilsy@icloud.com
 * @description JSON转Cast类助手
 */
public class JsonToCast {
    private static final String TAG = "JsonToCast";
    private String json = "";
    private CallBack cb = null;

    public JsonToCast() {

    }

    public JsonToCast(String json) {
        this.json = json;
    }

    /**
     * @author panilsy@icloud.com
     * @description 主要函数
     * @return
     */
    public Forecasts getCast() {
        // 新创建一个Forecasts实例
        Forecasts resultForecasts = new Forecasts();

        try {
            JSONObject jsonObject = new JSONObject(this.json);
            String requestStatus = jsonObject.getString("status");
            if (!requestStatus.equals("1")) {
                cb.run("获取数据失败，请检查网络");
                return resultForecasts;
            }

            JSONArray forecasts = jsonObject.getJSONArray("forecasts");

            String forecast = forecasts.get(0).toString();
            JSONObject forecastObj = new JSONObject(forecast);

            resultForecasts.setAdcode(forecastObj.getString("adcode"));
            resultForecasts.setCity(forecastObj.getString("city"));
            resultForecasts.setProvince(forecastObj.getString("province"));
            resultForecasts.setReporttime(forecastObj.getString("reporttime"));

            JSONArray casts = forecastObj.getJSONArray("casts");

            Gson gson = new Gson();
            List<Cast> castList = gson.fromJson(casts.toString(), new TypeToken<List<Cast>>(){}.getType());

            resultForecasts.setCasts(castList);

            return resultForecasts;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return resultForecasts;
        }


    }
}
