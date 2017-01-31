package com.evilmem.albert.evilmemory.Adapter;

/**
 * Created by Albert on 31/01/2017.
 */
public class RankingPlayer {
    private int icon;
    private String name;
    private String phone;

    public RankingPlayer(int icon, String name, String phone){
        this.icon = icon;
        this.name = name;
        this.phone = phone;

    }
    RankingPlayer(){

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
