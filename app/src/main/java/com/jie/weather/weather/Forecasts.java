package com.jie.weather.weather;

import java.util.List;

public class Forecasts {
    private String city;
    private String adcode;
    private String province;
    private String reporttime;
    private List<Cast> casts;
    private Lives lives;


    public Forecasts() {

    }

    public Forecasts(String city, String adcode, String province, String reporttime, List<Cast> casts, Lives lives) {
        this.city = city;
        this.adcode = adcode;
        this.province = province;
        this.reporttime = reporttime;
        this.casts = casts;
        this.lives = lives;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public Lives getLives() {
        return lives;
    }

    public void setLives(Lives lives) {
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "Forecasts{" +
                "city='" + city + '\'' +
                ", adcode='" + adcode + '\'' +
                ", province='" + province + '\'' +
                ", reporttime='" + reporttime + '\'' +
                ", casts=" + casts +
                '}';
    }
}
