package com.jolin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class BathOperatorUtil {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @description:批量更新mongodb数据
     * @author: xuyou
     * @date: 2018年8月14日 上午11:28:29
     */

//    public void bulkWriteUpdate(List<GPSEntity> documents){
//        List<WriteModel<Document>> requests = new ArrayList<WriteModel<Document>>();
//        UpdateOneModel uom = null;
//        for (GPSEntity gps : documents) {
//            try {
//                Bson filter = Filters.eq("attributes.id", gps.getAttributes().getId());
//                Document update = new Document("$set", BsonUtilsTool.toBson(gps));
//                UpdateOptions options = new UpdateOptions().upsert(true);
//                uom = new UpdateOneModel(filter, update,options);
//                System.out.println(uom.toString());
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//            requests.add(uom);
//        }
//        BulkWriteResult bulkWriteResult = mongoTemplate.getCollection("GisFeature").bulkWrite(requests);
//        System.out.println(bulkWriteResult.toString());
//    }
//    public void bulkWriteInsert(List<UserEntity> documents){
//        List<WriteModel<Document>> requests = new ArrayList<WriteModel<Document>>();
//        for (UserEntity document : documents) {
//            //构造插入单个文档的操作模型
//            InsertOneModel iom = null;
//            try {
//                iom = new InsertOneModel(BsonUtilsTool.toBson(document));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//            requests.add(iom);
//        }
//        BulkWriteResult  bulkWriteResult = mongoTemplate.getCollection("User").bulkWrite(requests);
//        System.out.println(bulkWriteResult.toString());
//    }

//    public void bulkWriteUpdate(List<UserEntity> documents){
//        List<WriteModel<Document>> requests = new ArrayList<WriteModel<Document>>();
//        UpdateOneModel uom = null;
//        for (UserEntity gps : documents) {
//            try {
//                Bson filter = Filters.eq("book.num", gps.getBook().getNum());
//                Document update = new Document("$set", BsonUtilsTool.toBson(gps));
//                UpdateOptions options = new UpdateOptions().upsert(true);
//                uom = new UpdateOneModel(filter, update,options);
//                System.out.println(uom.toString());
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//            requests.add(uom);
//        }
//        BulkWriteResult bulkWriteResult = mongoTemplate.getCollection("User").bulkWrite(requests);
//        System.out.println(bulkWriteResult.toString());
//    }


}