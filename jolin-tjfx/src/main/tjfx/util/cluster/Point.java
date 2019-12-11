package com.hdsx.lwgl.statanalysis.util.cluster;

public class Point {
    private double x;//坐标x轴
    private double y;//坐标y轴
    private boolean isVisit;//是佛访问标记
    private int cluster;//所属簇类
    private boolean isNoised;//是否是噪音数据

    public Point(double x,double y) {
        this.x = x;
        this.y = y;
        this.isVisit = false;
        this.cluster = 0;
        this.isNoised = false;
    }

    public double getDistance(Point point) {//计算两点间距离
        return Math.sqrt((x-point.x)*(x-point.x)+(y-point.y)*(y-point.y));
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setVisit(boolean isVisit) {
        this.isVisit = isVisit;
    }

    public boolean getVisit() {
        return isVisit;
    }

    public int getCluster() {
        return cluster;
    }

    public void setNoised(boolean isNoised) {
        this.isNoised = isNoised;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public boolean getNoised() {
        return this.isNoised;
    }

    @Override
    public String toString() {
        return x+" "+y+" "+cluster+" "+(isNoised?1:0);
    }

}
