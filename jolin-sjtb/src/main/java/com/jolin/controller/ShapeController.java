package com.jolin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jolin.entity.GPSMultiLineStirng;
import com.jolin.service.IGPSMongodbService;
import com.mongodb.util.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.Document;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.store.ContentFeatureSource;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("shape")
@Api(description = "空间数据")
public class ShapeController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IGPSMongodbService igpsMongodbService;

    @ApiOperation(value = "Shape线数据转化存储")
    @RequestMapping(value = "ShapeConMongodb", method = RequestMethod.GET)
    public void ShapeConMongodb(){
        final String SHAPE_FILE = "E:\\GIS_LX\\GIS_SXLX\\GIS_SXLX.shp";
        File shapeFile = new File(SHAPE_FILE);
        ShapefileDataStore store = null;
        Document document = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            store = new ShapefileDataStore(shapeFile.toURI().toURL());
            store.setCharset(Charset.forName("GBK"));
            ContentFeatureSource sfSource = store.getFeatureSource();
            SimpleFeatureIterator sfIter = sfSource.getFeatures().features();
            // 从ShapeFile文件中遍历每一个Feature，然后将Feature转为GeoJSON字符串，最后将字符串插入到mongodb的Collection中
            while (sfIter.hasNext()) {
                SimpleFeature feature = (SimpleFeature) sfIter.next();
                // Feature转GeoJSON
                FeatureJSON fjson = new FeatureJSON();
                StringWriter writer = new StringWriter();
                fjson.writeFeature(feature, writer);
                Object shapeModel = JSON.parse(writer.toString());
                System.out.println("db.gis_sxld.insert("+shapeModel+");");
            }
            System.out.println("数据导入完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "计算点在线上的数据")
    @RequestMapping(value = "ShapeCountByLine", method = RequestMethod.GET)
    public void ShapeCountByLine(){
        try {
            List<GPSMultiLineStirng> list = igpsMongodbService.ShapeCountByLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
