package com.jie.weather.Location;

/**
 * @author panilsy@icloud.com
 * @description 该类储存的是各个区县
 */
public class District {
    private String adcode;
    private String name;
    private String center;
    private String level;

    public District() {
    }

    public District(String adcode, String name, String center, String level) {
        this.adcode = adcode;
        this.name = name;
        this.center = center;
        this.level = level;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
