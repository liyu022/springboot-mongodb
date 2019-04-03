package com.jolin.entity;

import java.io.Serializable;

public class Feature implements Serializable {

    //几何属性（WKT）
    private String geometry;

    //几何类型
    private String geometryType;

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getGeometryType() {
        return geometryType;
    }

    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }
}
