package com.hdsx.lwgl.tjfx.entity;

import java.io.Serializable;

public class FeatureLine extends Feature implements Serializable {

    public FeatureLineAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(FeatureLineAttribute attributes) {
        this.attributes = attributes;
    }

    //业务属性
    private FeatureLineAttribute attributes;
}
