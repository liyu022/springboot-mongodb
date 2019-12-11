package com.hdsx.lwgl.tjfx.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection="gpssj_tj")
public class OutCar {
    private String xzqh;
    private String name;
    private int coun;
    private Date time;

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoun() {
        return coun;
    }

    public void setCoun(int coun) {
        this.coun = coun;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OutCar{" +
                "xzqh='" + xzqh + '\'' +
                ", name='" + name + '\'' +
                ", coun=" + coun +
                ", time=" + time +
                '}';
    }
}
