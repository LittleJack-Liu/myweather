package com.jie.weather.utils;

import com.jie.weather.weather.Lives;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author panilsy@icloud.com
 * @description json to lives 助手
 */
public class JsonToLives {
    private static final String TAG = "JsonToCast";
    private String json = "";
    private CallBack cb = null;

    public JsonToLives() {
    }

    public JsonToLives(String json) {
        this.json = json;
        this.cb = cb;
    }

    public JsonToLives(String json, CallBack cb) {
        this.json = json;
        this.cb = cb;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public CallBack getCb() {
        return cb;
    }

    public void setCb(CallBack cb) {
        this.cb = cb;
    }

    public Lives getLive() {
        // 新创建一个Forecasts实例
        Lives lives = new Lives();

        try {
            JSONObject jsonObject = new JSONObject(this.json);
            String requestStatus = jsonObject.getString("status");
            if (!requestStatus.equals("1")) {
                cb.run("获取数据失败，请检查网络");
                return lives;
            }



            JSONArray livesArr = jsonObject.getJSONArray("lives");

            String livesString = livesArr.get(0).toString();
            JSONObject liveObj = new JSONObject(livesString);

            lives.setAdcode(liveObj.getString("adcode"));
            lives.setCity(liveObj.getString("city"));
            lives.setHumidity(liveObj.getString("humidity"));
            lives.setProvince(liveObj.getString("province"));
            lives.setReporttime(liveObj.getString("reporttime"));
            lives.setTemperature(liveObj.getString("temperature"));
            lives.setWeather(liveObj.getString("weather"));
            lives.setWinddirection(liveObj.getString("winddirection"));
            lives.setWindpower(liveObj.getString("windpower"));

            return lives;
        } catch (Exception e) {
            return lives;
        }


    }
}
