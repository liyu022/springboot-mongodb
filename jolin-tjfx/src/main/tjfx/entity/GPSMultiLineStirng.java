package com.hdsx.lwgl.tjfx.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(collection="gis_sxld")
public class GPSMultiLineStirng extends Feature implements Serializable {

    private String id;

    private String type;

    private String lxmc;

    private BigDecimal qdzh;

    private BigDecimal zdzh;

    private String lxdm;

    private String sxxfx;

    private String ldmc;

    private String xzqhbm;

    private Date time;

    private long cou;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public BigDecimal getQdzh() {
        return qdzh;
    }

    public void setQdzh(BigDecimal qdzh) {
        this.qdzh = qdzh;
    }

    public BigDecimal getZdzh() {
        return zdzh;
    }

    public void setZdzh(BigDecimal zdzh) {
        this.zdzh = zdzh;
    }

    public String getLxdm() {
        return lxdm;
    }

    public void setLxdm(String lxdm) {
        this.lxdm = lxdm;
    }

    public String getSxxfx() {
        return sxxfx;
    }

    public void setSxxfx(String sxxfx) {
        this.sxxfx = sxxfx;
    }

    public String getLdmc() {
        return ldmc;
    }

    public void setLdmc(String ldmc) {
        this.ldmc = ldmc;
    }

    public String getXzqhbm() {
        return xzqhbm;
    }

    public void setXzqhbm(String xzqhbm) {
        this.xzqhbm = xzqhbm;
    }

    public long getCou() {
        return cou;
    }

    public void setCou(long cou) {
        this.cou = cou;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public GPSMultiLineStirng(){}
    public GPSMultiLineStirng(String id, String type, String lxmc, BigDecimal qdzh, BigDecimal zdzh, String lxdm, String sxxfx, String ldmc, String xzqhbm, long cou,Date time) {
        this.id = id;
        this.type = type;
        this.lxmc = lxmc;
        this.qdzh = qdzh;
        this.zdzh = zdzh;
        this.lxdm = lxdm;
        this.sxxfx = sxxfx;
        this.ldmc = ldmc;
        this.xzqhbm = xzqhbm;
        this.cou = cou;
        this.time = time;
    }

    @Override
    public String toString() {
        return "GPSMultiLineStirng{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", lxmc='" + lxmc + '\'' +
                ", qdzh=" + qdzh +
                ", zdzh=" + zdzh +
                ", lxdm='" + lxdm + '\'' +
                ", sxxfx='" + sxxfx + '\'' +
                ", ldmc='" + ldmc + '\'' +
                ", xzqhbm='" + xzqhbm + '\'' +
                ", cou=" + cou +
                ", geometry=" + getGeometry() +
                ", time=" + time +
                '}';
    }
}
