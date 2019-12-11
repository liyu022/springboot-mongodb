package com.jolin.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class FeatureLineAttribute implements Serializable {

    private String id;

    private String lxdm;

    private String lxmc;

    private String qdmc;

    private BigDecimal qdzh;

    private BigDecimal qdpy;

    private String zdmc;

    private BigDecimal zdzh;

    private BigDecimal zdpy;

    private BigDecimal lc;

    private BigDecimal ldjsdjbmbm;

    private BigDecimal sxxfx;

    private BigDecimal dllxbm;

    private String xzqhbm;
    public FeatureLineAttribute(){}
    public FeatureLineAttribute(String id, String lxdm, String lxmc, String qdmc, BigDecimal qdzh, BigDecimal qdpy, String zdmc, BigDecimal zdzh, BigDecimal zdpy, BigDecimal lc, BigDecimal ldjsdjbmbm, BigDecimal sxxfx, BigDecimal dllxbm, String xzqhbm) {
        this.id = id;
        this.lxdm = lxdm;
        this.lxmc = lxmc;
        this.qdmc = qdmc;
        this.qdzh = qdzh;
        this.qdpy = qdpy;
        this.zdmc = zdmc;
        this.zdzh = zdzh;
        this.zdpy = zdpy;
        this.lc = lc;
        this.ldjsdjbmbm = ldjsdjbmbm;
        this.sxxfx = sxxfx;
        this.dllxbm = dllxbm;
        this.xzqhbm = xzqhbm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLxdm() {
        return lxdm;
    }

    public void setLxdm(String lxdm) {
        this.lxdm = lxdm;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public String getQdmc() {
        return qdmc;
    }

    public void setQdmc(String qdmc) {
        this.qdmc = qdmc;
    }

    public BigDecimal getQdzh() {
        return qdzh;
    }

    public void setQdzh(BigDecimal qdzh) {
        this.qdzh = qdzh;
    }

    public BigDecimal getQdpy() {
        return qdpy;
    }

    public void setQdpy(BigDecimal qdpy) {
        this.qdpy = qdpy;
    }

    public String getZdmc() {
        return zdmc;
    }

    public void setZdmc(String zdmc) {
        this.zdmc = zdmc;
    }

    public BigDecimal getZdzh() {
        return zdzh;
    }

    public void setZdzh(BigDecimal zdzh) {
        this.zdzh = zdzh;
    }

    public BigDecimal getZdpy() {
        return zdpy;
    }

    public void setZdpy(BigDecimal zdpy) {
        this.zdpy = zdpy;
    }

    public BigDecimal getLc() {
        return lc;
    }

    public void setLc(BigDecimal lc) {
        this.lc = lc;
    }

    public BigDecimal getLdjsdjbmbm() {
        return ldjsdjbmbm;
    }

    public void setLdjsdjbmbm(BigDecimal ldjsdjbmbm) {
        this.ldjsdjbmbm = ldjsdjbmbm;
    }

    public BigDecimal getSxxfx() {
        return sxxfx;
    }

    public void setSxxfx(BigDecimal sxxfx) {
        this.sxxfx = sxxfx;
    }

    public BigDecimal getDllxbm() {
        return dllxbm;
    }

    public void setDllxbm(BigDecimal dllxbm) {
        this.dllxbm = dllxbm;
    }

    public String getXzqhbm() {
        return xzqhbm;
    }

    public void setXzqhbm(String xzqhbm) {
        this.xzqhbm = xzqhbm;
    }

    @Override
    public String toString() {
        return "FeatureLineAttribute{" +
                "id='" + id + '\'' +
                ", lxdm='" + lxdm + '\'' +
                ", lxmc='" + lxmc + '\'' +
                ", qdmc='" + qdmc + '\'' +
                ", qdzh=" + qdzh +
                ", qdpy=" + qdpy +
                ", zdmc='" + zdmc + '\'' +
                ", zdzh=" + zdzh +
                ", zdpy=" + zdpy +
                ", lc=" + lc +
                ", ldjsdjbmbm=" + ldjsdjbmbm +
                ", sxxfx=" + sxxfx +
                ", dllxbm=" + dllxbm +
                ", xzqhbm='" + xzqhbm + '\'' +
                '}';
    }
}