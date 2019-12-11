package com.jolin.entity;
import org.jfaster.mango.annotation.Sharding;
import org.jfaster.mango.sharding.TableShardingStrategy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection="outCarHistory")
@Sharding
public class OutCarEntity implements TableShardingStrategy<Integer> {
        @Field("carnum")  //指定字段的主键字段
        private String carnum;
        @Field("carcolor")
        private String carcolor;
        @Field("timestampstr")
        private Date timestampstr;
        @Field("lon")
        private BigDecimal lon;
        @Field("lat")
        private BigDecimal lat;
        @Field("vec1")
        private BigDecimal vec1;
        @Field("vec2")
        private BigDecimal vec2;
        @Field("vec3")
        private BigDecimal vec3;
        @Field("direction")
        private BigDecimal direction;
        @Field("altitude")
        private BigDecimal altitude;
        @Field("state")
        private BigDecimal state;
        @Field("alarm")
        private BigDecimal alarm;
    public OutCarEntity(){}
    @Override
    public String getTargetTable(String table, Integer uid) {
        int num = uid <= 1000 ? 0 : 1;
        return table + "_" + num;
    }
    public OutCarEntity(String carnum, String carcolor, Date timestampstr, BigDecimal lon, BigDecimal lat, BigDecimal vec1, BigDecimal vec2, BigDecimal vec3, BigDecimal direction, BigDecimal altitude, BigDecimal state, BigDecimal alarm) {
        this.carnum = carnum;
        this.carcolor = carcolor;
        this.timestampstr = timestampstr;
        this.lon = lon;
        this.lat = lat;
        this.vec1 = vec1;
        this.vec2 = vec2;
        this.vec3 = vec3;
        this.direction = direction;
        this.altitude = altitude;
        this.state = state;
        this.alarm = alarm;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getCarcolor() {
        return carcolor;
    }

    public void setCarcolor(String carcolor) {
        this.carcolor = carcolor;
    }

    public Date getTimestampstr() {
        return timestampstr;
    }

    public void setTimestampstr(Date timestampstr) {
        this.timestampstr = timestampstr;
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

    public BigDecimal getVec1() {
        return vec1;
    }

    public void setVec1(BigDecimal vec1) {
        this.vec1 = vec1;
    }

    public BigDecimal getVec2() {
        return vec2;
    }

    public void setVec2(BigDecimal vec2) {
        this.vec2 = vec2;
    }

    public BigDecimal getVec3() {
        return vec3;
    }

    public void setVec3(BigDecimal vec3) {
        this.vec3 = vec3;
    }

    public BigDecimal getDirection() {
        return direction;
    }

    public void setDirection(BigDecimal direction) {
        this.direction = direction;
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

    @Override
    public String toString() {
        return "OutCarEntity{" +
                "carnum='" + carnum + '\'' +
                ", carcolor='" + carcolor + '\'' +
                ", timestampstr=" + timestampstr +
                ", lon=" + lon +
                ", lat=" + lat +
                ", vec1=" + vec1 +
                ", vec2=" + vec2 +
                ", vec3=" + vec3 +
                ", direction=" + direction +
                ", altitude=" + altitude +
                ", state=" + state +
                ", alarm=" + alarm +
                '}';
    }
}
