package com.hdsx.lwgl.tjfx.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class FeatureLineAttribute implements Serializable {

    private String id;

    private String lxdm;

    private String lxmc;

    private BigDecimal qdzh;

    private BigDecimal zdzh;

    private BigDecimal lxlx;

    private BigDecimal sxxfx;

    private String xzqhbm;

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

    public BigDecimal getLxlx() {
        return lxlx;
    }

    public void setLxlx(BigDecimal lxlx) {
        this.lxlx = lxlx;
    }

    public BigDecimal getSxxfx() {
        return sxxfx;
    }

    public void setSxxfx(BigDecimal sxxfx) {
        this.sxxfx = sxxfx;
    }

    public String getXzqhbm() {
        return xzqhbm;
    }

    public void setXzqhbm(String xzqhbm) {
        this.xzqhbm = xzqhbm;
    }
}
