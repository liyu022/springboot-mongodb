package com.hdsx.lwgl.tjfx.entity;

import java.io.Serializable;

public class FeatureGPS extends Feature implements Serializable {

    public FeatureGpsAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(FeatureGpsAttribute attributes) {
        this.attributes = attributes;
    }

    //业务属性
    private FeatureGpsAttribute attributes;
}
