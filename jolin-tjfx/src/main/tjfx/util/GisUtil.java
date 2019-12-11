package com.hdsx.lwgl.statanalysis.util;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.geo.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GisUtil {
    /**  The GeometryFactory. */
    private static GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory( null );

    /** The wkt writer. */
    private static WKTWriter wktWriter=new WKTWriter();

    /** The wkt reader. */
    private static WKTReader wktReader=new WKTReader(JTSFactoryFinder.getGeometryFactory( null ));

    /**
     * 读取
     *
     * @param wkt WKT格式数据
     * @return the geometry JTS空间对象
     */
    public static Geometry wkt2Geo(String wkt)
    {
        try {
            return wktReader.read(wkt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入
     *
     * @param geometry JTS空间对象
     * @return the string WKT格式数据
     */
    public static String geo2Wkt(Geometry geometry)
    {
        return  wktWriter.write(geometry);
    }

    /**
     * create multiLine
     * @return
     */
    public static MultiLineString createMLine(LineString[] lineStrings){
        MultiLineString ms = geometryFactory.createMultiLineString(lineStrings);
        return ms;
    }

    /**
     * create multiLine
     * @return
     */
    public static GeometryCollection createGeoCollection(Geometry[] geos){
        GeometryCollection geoCollection = new GeometryCollection(geos,geometryFactory);
        return geoCollection;
    }

    /**
     * 长度转角度
     * @param meters
     * @return
     */
    public static double meterToRadian(double meters){
        double radian=meters*(360/(2*Math.PI * 6378137.0));
        return radian;
    }

    /**
     * 生成缓冲区
     * @param geo
     * @param distanceMeter
     * @return
     */
    public static Geometry bufferGeo(Geometry geo,double distanceMeter){
        double radian=meterToRadian(distanceMeter);
        return geo.buffer(radian);
    }

    /**
     * 生成缓冲区
     * @param geoWkt
     * @param distanceMeter
     * @return
     */
    public static Geometry bufferGeo(String geoWkt,double distanceMeter){
        Geometry bufferGeometry = null;
        if(geoWkt!=null && !geoWkt.isEmpty()){
            WKTReader reader=new WKTReader();
            try {
                Geometry geometry=reader.read(geoWkt);
                bufferGeometry= bufferGeo(geometry,distanceMeter);
            } catch (ParseException e) {

            }
        }
        return bufferGeometry;
    }

    /**
     * 生成缓冲区
     * @param geoCollection
     * @param distanceMeter
     * @return
     */
    public static Geometry bufferGeo(GeometryCollection geoCollection, double distanceMeter){
        double radian=meterToRadian(distanceMeter);
        return geoCollection.buffer(radian);
    }

    /**
     * 创建点
     * @param lat 经度
     * @param lat 维度
     * @return
     */
    public static Point createPoint(double lon,double lat){
        return geometryFactory.createPoint(new Coordinate(lon,lat));

    }

    /**
     * 创建线
     * @param points 点集合
     * @return
     */
    public static LineString createLineString(Point[] points){
        Coordinate[] coordinates = new Coordinate[points.length];
        if(points!=null){
            int i = 0;
            for (Point p: points) {
                coordinates[i++] = p.getCoordinate();
            }
        }
        return geometryFactory.createLineString(coordinates);
    }

    public static GeoJson toMongGeoJson(Geometry geo){
        GeoJson geoJson = null;
        String getType = geo.getGeometryType();
        if(getType.equals("Point")) {
            geoJson = new GeoJsonPoint(geo.getCoordinates()[0].x,geo.getCoordinates()[0].x);
        }else if(getType.equals("LineString") || getType.equals("LinearRing")) {
            Coordinate[] coordinates = geo.getCoordinates();
            List<org.springframework.data.geo.Point> points = new ArrayList<org.springframework.data.geo.Point>();
            for (Coordinate coord :coordinates) {
                points.add(new org.springframework.data.geo.Point(coord.x,coord.y));
            }
            geoJson = new GeoJsonLineString(points);
        }
        else if(getType.equals("Polygon")) {
            List<org.springframework.data.geo.Point> points = new ArrayList<org.springframework.data.geo.Point>();
            Coordinate[] coordinates = geo.getCoordinates();
            for (Coordinate coord :coordinates) {
                points.add(new org.springframework.data.geo.Point(coord.x,coord.y));
            }
            geoJson = new GeoJsonPolygon(points);
        }
        else if(getType.equals("MultiPoint")) {
            List<org.springframework.data.geo.Point> points = new ArrayList<org.springframework.data.geo.Point>();
            Coordinate[] coordinates = geo.getCoordinates();
            for (Coordinate coord :coordinates) {
                points.add(new org.springframework.data.geo.Point(coord.x,coord.y));
            }
            geoJson = new GeoJsonMultiPoint(points);
        }
        else if(getType.equals("MultiLineString")) {
            List<GeoJsonLineString> points = new ArrayList<GeoJsonLineString>();
            int nums = geo.getNumGeometries();
            for(int i = 0;i < nums; i++) {
                points.add((GeoJsonLineString)GisUtil.toMongGeoJson(geo.getGeometryN(i)));
            }
            geoJson = new GeoJsonMultiLineString(points);
        }
        else if(getType.equals("MultiPolygon")) {
            List<GeoJsonPolygon> points = new ArrayList<GeoJsonPolygon>();
            int nums = geo.getNumGeometries();
            for(int i = 0;i < nums; i++) {
                points.add((GeoJsonPolygon)GisUtil.toMongGeoJson(geo.getGeometryN(i)));
            }
            geoJson = new GeoJsonMultiPolygon(points);
        }
        else if(getType.equals("GeometryCollection")) {
            List<GeoJson<?>> points = new ArrayList<GeoJson<?>>();
            int nums = geo.getNumGeometries();
            for(int i = 0;i < nums; i++) {
                points.add((GisUtil.toMongGeoJson(geo.getGeometryN(i))));
            }
            geoJson = new GeoJsonGeometryCollection(points);
        }
        return geoJson;
    }

    public static String wktToGeojson(String wkt) {
        String json = null;
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            StringWriter writer = new StringWriter();
            GeometryJSON g = new GeometryJSON();
            g.write(wkt2Geo(wkt), writer);
            map.put("$geometry", writer);
            json = JSONObject.toJSONString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Geometry GeoJsonToGeometry(String geojson) {
        GeometryJSON g = new GeometryJSON();
        Reader reader = new StringReader(geojson);
        try {
            return g.read(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
