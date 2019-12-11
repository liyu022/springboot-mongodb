package com.hdsx.lwgl.tjfx.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FeatureGpsAttribute implements Serializable {

    //几何属性（WKT）
    private String id;
    //经度
    private BigDecimal lon;

    //纬度
    private BigDecimal lat;

    //速度（卫星速度）
    private BigDecimal speed1;

    //方向
    private BigDecimal direction;

    //行驶里程
    private BigDecimal mileage;

    //海拔高度
    private BigDecimal altitude;

    //状态
    private BigDecimal state;

    //报警状态（1超速报警，2疲劳驾驶报警，3紧急报警，4进入指定区域报警，5离开指定区域报警，6路段堵塞报警，7危险路段报警，8越界报警，9盗警，10劫警，11偏离路线报警，12车辆移动报警，13超时驾驶报警，255其他报警）
    private BigDecimal alarm;

    //GPS时间
    private Date gpstime;

    //入库时间
    private Date addtime;

    //行驶记录速度（行车记录仪）
    private BigDecimal speed2;

    //路段代码
    private String lddm;

    private String lxmc;

    private String carCompany;

    private String carriageRes;

    private String certificateUnit;

    private BigDecimal carType;

    public String getCertificateUnit() {
        return certificateUnit;
    }

    public void setCertificateUnit(String certificateUnit) {
        this.certificateUnit = certificateUnit;
    }

    public String getCarriageRes() {
        return carriageRes;
    }

    public void setCarriageRes(String carriageRes) {
        this.carriageRes = carriageRes;
    }

    public String getCarCompany() {
        return carCompany;
    }

    public void setCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getSpeed1() {
        return speed1;
    }

    public void setSpeed1(BigDecimal speed1) {
        this.speed1 = speed1;
    }

    public BigDecimal getDirection() {
        return direction;
    }

    public void setDirection(BigDecimal direction) {
        this.direction = direction;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getAltitude() {
        return altitude;
    }

    public void setAltitude(BigDecimal altitude) {
        this.altitude = altitude;
    }

    public BigDecimal getState() {
        return state;
    }

    public void setState(BigDecimal state) {
        this.state = state;
    }

    public BigDecimal getAlarm() {
        return alarm;
    }

    public void setAlarm(BigDecimal alarm) {
        this.alarm = alarm;
    }

    public Date getGpstime() {
        return gpstime;
    }

    public void setGpstime(Date gpstime) {
        this.gpstime = gpstime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public BigDecimal getSpeed2() {
        return speed2;
    }

    public void setSpeed2(BigDecimal speed2) {
        this.speed2 = speed2;
    }

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }

    public BigDecimal getCarType() {
        return carType;
    }

    public void setCarType(BigDecimal carType) {
        this.carType = carType;
    }
}
