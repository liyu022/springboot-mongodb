package com.jolin.entity;


import java.math.BigDecimal;

public class GeoJsonPoint {

    private String type;

    private BigDecimal[] coordinates;

    public GeoJsonPoint() {
    }

    public GeoJsonPoint(BigDecimal[] coordinates) {
        this.type = "Point";
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(BigDecimal[] coordinates) {
        this.coordinates = coordinates;
    }
}
