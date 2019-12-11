package com.hdsx.lwgl.statanalysis.entity;

/**
 * 交通量实体
 */
public class TrifficFlow {

    private String month; //月份日期
    private int area;   //行政区划
    private int rkjtl;  //入口交通量
    private int ckjtl;  //出口交通量

    public TrifficFlow() {
    }

    public TrifficFlow(String month, int area, int rkjtl, int ckjtl) {
        this.month = month;
        this.area = area;
        this.rkjtl = rkjtl;
        this.ckjtl = ckjtl;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getRkjtl() {
        return rkjtl;
    }

    public void setRkjtl(int rkjtl) {
        this.rkjtl = rkjtl;
    }

    public int getCkjtl() {
        return ckjtl;
    }

    public void setCkjtl(int ckjtl) {
        this.ckjtl = ckjtl;
    }

    @Override
    public String toString() {
        return "TrifficFlow{" +
                "month='" + month + '\'' +
                ", area=" + area +
                ", rkjtl=" + rkjtl +
                ", ckjtl=" + ckjtl +
                '}';
    }
}
