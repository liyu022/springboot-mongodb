package com.jolin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  车辆GPS信息实体类
 */
public class VEHICLE_GPS implements Serializable {

    //车牌号码
    private String plateNumbers;

    //流水号
    private Long id;

    //经度
    private BigDecimal lon;

    //纬度
    private BigDecimal lat;

    //速度（卫星速度）
    private BigDecimal speed1;

    //方向
    private BigDecimal direction;

    //空间位置（WKT）
    private String geometry;

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

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers == null ? null : plateNumbers.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
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

    @Override
    public String toString() {
        StringBuffer toString = new StringBuffer();
        toString.append("[");
        toString.append("plateNumbers=").append(this.plateNumbers);
        toString.append(",lon=").append(this.lon);
        toString.append(",lat=").append(this.lat);
        toString.append(",speed1=").append(this.speed1);
        toString.append(",speed2=").append(this.speed2);
        toString.append(",direction=").append(this.direction);
        toString.append(",geometry=").append(this.geometry);
        toString.append(",mileage=").append(this.mileage);
        toString.append(",altitude=").append(this.altitude);
        toString.append(",state=").append(this.state);
        toString.append(",alarm=").append(this.alarm);
        toString.append(",gpstime=").append(this.gpstime);
        toString.append(",addtime=").append(this.addtime);
        toString.append(",lddm=").append(this.lddm);
        toString.append("]");
        return toString.toString();
    }

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }
}