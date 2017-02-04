package com.evilmem.albert.evilmemory.Interface;

import com.evilmem.albert.evilmemory.Activities.WeatherActivity;
import com.evilmem.albert.evilmemory.Data.Coord;
import com.evilmem.albert.evilmemory.Data.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Albert on 03/02/2017.
 */
public class FOAASResponse {
    @SerializedName("message") String m;
    @SerializedName("subtitle") String s;
    @SerializedName("APPID") String appid;
    Coord coord;
    @SerializedName("weather") List<Weather> weathers;


    public Double getLat() {
        return coord.getLat();
    }

    public Double getLon() {
        return coord.getLon();
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getWeather() {
        return weathers.get(0).toString();
    }
}
