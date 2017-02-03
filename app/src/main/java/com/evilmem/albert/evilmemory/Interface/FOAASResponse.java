package com.evilmem.albert.evilmemory.Interface;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Albert on 03/02/2017.
 */
public class FOAASResponse {
    @SerializedName("message") String m;
    @SerializedName("subtitle") String s;
    @SerializedName("lat") Double lat;
    @SerializedName("lat") Double lon;
    @SerializedName("APPID") String appid;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
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
}
