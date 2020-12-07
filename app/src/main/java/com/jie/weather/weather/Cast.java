package com.jie.weather.weather;

public class Cast {
    private String date;
    private String week;
    private String dayweather;
    private String nightweather;
    private String daytemp;
    private String nighttemp;
    private String daywind;
    private String nightwind;
    private String daypower;
    private String nightpower;

    public Cast() {
    }

    public Cast(String date, String week, String dayweather, String nightweather, String daytemp, String nighttemp, String daywind, String nightwind, String daypower, String nightpower) {
        this.date = date;
        this.week = week;
        this.dayweather = dayweather;
        this.nightweather = nightweather;
        this.daytemp = daytemp;
        this.nighttemp = nighttemp;
        this.daywind = daywind;
        this.nightwind = nightwind;
        this.daypower = daypower;
        this.nightpower = nightpower;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDayweather() {
        return dayweather;
    }

    public void setDayweather(String dayweather) {
        this.dayweather = dayweather;
    }

    public String getNightweather() {
        return nightweather;
    }

    public void setNightweather(String nightweather) {
        this.nightweather = nightweather;
    }

    public String getDaytemp() {
        return daytemp;
    }

    public void setDaytemp(String daytemp) {
        this.daytemp = daytemp;
    }

    public String getNighttemp() {
        return nighttemp;
    }

    public void setNighttemp(String nighttemp) {
        this.nighttemp = nighttemp;
    }

    public String getDaywind() {
        return daywind;
    }

    public void setDaywind(String daywind) {
        this.daywind = daywind;
    }

    public String getNightwind() {
        return nightwind;
    }

    public void setNightwind(String nightwind) {
        this.nightwind = nightwind;
    }

    public String getDaypower() {
        return daypower;
    }

    public void setDaypower(String daypower) {
        this.daypower = daypower;
    }

    public String getNightpower() {
        return nightpower;
    }

    public void setNightpower(String nightpower) {
        this.nightpower = nightpower;
    }
}
