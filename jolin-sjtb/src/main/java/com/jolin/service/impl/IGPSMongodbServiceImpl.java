package com.jolin.service.impl;

import com.hdsx.lwgl.sjtb.entity.FeatureGpsAttribute;
import com.hdsx.lwgl.sjtb.entity.GPSEntity;
import com.hdsx.lwgl.sjtb.entity.GeoJsonPoint;
import com.hdsx.lwgl.sjtb.service.IGPSMongodbService;
import com.hdsx.lwgl.sjtb.util.DateConvert;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.WriteModel;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Service
public class IGPSMongodbServiceImpl implements IGPSMongodbService {
    @Autowired
    private MongoTemplate mongoTemplate;

    LocalDate localDate = LocalDate.now();

    @Override
    public void SaveGPS() {
        GPSEntity gps = new GPSEntity();
        BigDecimal[] decimal = new BigDecimal[2];
        decimal[0] = BigDecimal.valueOf(34.256986);
        decimal[1] = BigDecimal.valueOf(107.569874);
        GeoJsonPoint point = new GeoJsonPoint(decimal);
        FeatureGpsAttribute gpsAttribute = new FeatureGpsAttribute("陕AEZ031",BigDecimal.valueOf(34.256986),BigDecimal.valueOf(34.256986),BigDecimal.valueOf(23),BigDecimal.valueOf(14),BigDecimal.valueOf(34),BigDecimal.valueOf(3),BigDecimal.valueOf(65),BigDecimal.valueOf(6),new DateConvert().LocalDateTimeToUdate(LocalDateTime.now()),new DateConvert().LocalDateTimeToUdate(LocalDateTime.now()),BigDecimal.valueOf(3.2),"G65","gaosu","1","43543", "shengqign", BigDecimal.valueOf(1), "1","1");
        gps.setLocation(point);
        gps.setGeometry("Point (108.107751 32.2668)");
        gps.setGeometryType("Point");
        gps.setAttributes(gpsAttribute);
        gps.setLocationId( Calendar.getInstance().getTimeInMillis()+"");
        mongoTemplate.save(gps);
    }

    @Override
    public List<GPSEntity> GetGPSByMongodb(){
        SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Query query = null;
        try {
            query = new Query(
                    Criteria.where("geometryType").is("Point").andOperator(Criteria.where("attributes.addtime").gte(format.parse("2001-01-10 10:00:00")).lt(format.parse("2021-01-10 10:00:00"))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        return mongoTemplate.findAll(query,GPSEntity.class);
        return mongoTemplate.find(query,GPSEntity.class);
    }
    @Override
    public GPSEntity GetGPSMongodbMaxTime() {
         // Query query = new Query().with(new Sort(new Sort.Order(Sort.Direction.DESC, "attributes.addtime"))).limit(1);
         // 测试类型1如果有一条数据返回结果Sun Mar 31 11:08:06 CST 2019
         // 测试如果一条都么有返回的是null
        Criteria criteria = new Criteria();
        criteria.where("attributes.addtime").ne("").ne(null).exists(true);
        Query query = new Query(criteria).with(new Sort(new Sort.Order(Sort.Direction.DESC,"attributes.addtime"))).limit(1);
         return mongoTemplate.findOne(query,GPSEntity.class);
    }

    @Override
    public Collection insertAll(List<GPSEntity> gpslist) {
       return mongoTemplate.insertAll(gpslist);
    }

    @Override
    public Collection updateManyByID(List<GPSEntity> gpslist) {
        Criteria criteria = new Criteria();
        criteria.where("attributes.addtime").ne("").ne(null).exists(true);
        Query query = new Query(criteria).with(new Sort(new Sort.Order(Sort.Direction.DESC,"attributes.addtime"))).limit(1);
        return mongoTemplate.find(query,GPSEntity.class);
    }

    @Override
    public Collection insertAllByID(List<GPSEntity> gpslist) {
        return mongoTemplate.insertAll(gpslist);
    }

    @Override
    public void BatchUpdateUserList(List<GPSEntity> documents) {
//        List<WriteModel<Document>> requests = new ArrayList<WriteModel<Document>>();
//        InsertOneModel iom = null;
//        UpdateOneModel uom = null;
//        for (GPSEntity gps : documents) {
//            try {
//                String str = gps.getAttributes().getId();
//                Bson filter = Filters.eq("attributes._id", str);
//                Document update = new Document("$set", BsonUtilsTool.toBson(gps));
//                UpdateOptions options = new UpdateOptions().upsert(true);
////                FindOneAndUpdateOptions findOneAndUpdateOptions = new FindOneAndUpdateOptions();
////                findOneAndUpdateOptions.upsert(true);
//                  uom = new UpdateOneModel(filter, update,options);
////                mongoTemplate.getCollection("GisFeature").findOneAndUpdate(filter, update,findOneAndUpdateOptions);
//
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//            requests.add(iom);
//            requests.add(uom);
//        }
//        BulkWriteResult bulkWriteResult = mongoTemplate.getCollection("GisFeature").bulkWrite(requests);
//        System.out.println(bulkWriteResult.toString());
         BatchDeleteUserList(documents);
         BatchInsertUserList(documents);

    }

    public Boolean GetGPSListByID(String gpslist) {
        Boolean flag = true;
        Criteria criteria = new Criteria();
        criteria.where("attributes._id").nin(gpslist);
        Query query = new Query(criteria);
        List<GPSEntity> insertGpsEntity = mongoTemplate.find(query,GPSEntity.class);
        if(insertGpsEntity==null ||insertGpsEntity.isEmpty()){
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }

    @Override
    public void BatchInsertUserList(List<GPSEntity> documents) {
        for (GPSEntity document : documents) {
            mongoTemplate.insert(document);
        }
    }

    public void BatchDeleteUserList(List<GPSEntity> documents){
        List<WriteModel<Document>> requests = new ArrayList<WriteModel<Document>>();
        for (GPSEntity document : documents) {
            //删除条件
            Document queryDocument = new Document("attributes._id",document.getAttributes().getId());
            //构造删除单个文档的操作模型，
            DeleteOneModel<Document> dom = new DeleteOneModel<Document>(queryDocument);
            requests.add(dom);
        }
        BulkWriteResult bulkWriteResult = mongoTemplate.getCollection("GisFeature").bulkWrite(requests);
        System.out.println(bulkWriteResult.toString());
    }


}
