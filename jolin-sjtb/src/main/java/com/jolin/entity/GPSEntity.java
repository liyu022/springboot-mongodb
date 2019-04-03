package com.jolin.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="GisFeature")
public class GPSEntity {
    @Id  //指定字段的主键字段
    private String locationId;
    @Field("geometry")
    private String geometry;
    @Field("geometryType")
    private String geometryType;
    @Field("attributes")
    private FeatureGpsAttribute attributes;
    @Field("location")
    private GeoJsonPoint location;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

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

    public FeatureGpsAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(FeatureGpsAttribute attributes) {
        this.attributes = attributes;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public GPSEntity(){}

    public GPSEntity(String locationId, String geometry, String geometryType, FeatureGpsAttribute attributes, GeoJsonPoint location) {
        this.locationId = locationId;
        this.geometry = geometry;
        this.geometryType = geometryType;
        this.attributes = attributes;
        this.location = location;
    }
}
