package com.hdsx.lwgl.tjfx.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="gpssj_tj_detail")
public class OutCarDetail {
    private String plateNumbers;
    private String xzqh;
    private String name;
    private int coun;

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

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers;
    }
}
