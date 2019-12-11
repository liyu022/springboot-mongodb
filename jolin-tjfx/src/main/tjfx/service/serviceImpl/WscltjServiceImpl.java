package com.hdsx.lwgl.statanalysis.service.serviceImpl;


import com.hdsx.lwgl.statanalysis.entity.OutCar;
import com.hdsx.lwgl.statanalysis.entity.OutCarDetail;
import com.hdsx.lwgl.statanalysis.service.IWscltjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class WscltjServiceImpl implements IWscltjService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<OutCar> getWscltjAll(String begintime,String endtime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        long time = 10*60*1000;//10分钟
        Date startTime = null; Date endTime = null;
        try {
            if(begintime ==""||begintime==null){
                startTime = new Date(now.getTime() - time);//10分钟前的时间
                endTime = new Date(now.getTime());//当前的时间
            }else{
                startTime =  format.parse(begintime);
                endTime =  format.parse(endtime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Aggregation aggregation = newAggregation(
                match(Criteria.where("time").gte(startTime).lt(endTime).and("plateNumbers").regex("^陕","i")),
                group("plateNumbers","xzqh").first("name").as("name"),
                group("_id.xzqh").first("name").as("name").first("_id.xzqh").as("xzqh").count().as("coun"),
                sort(DESC, "coun")
        );
        AggregationResults<OutCar> results = mongoTemplate.aggregate(aggregation,"gpssj_tj_detail",OutCar.class);
        List<OutCar> carList = results.getMappedResults();
        for(OutCar outcar:carList){
           System.out.println(outcar.toString());
        }
        return carList;
    }

    @Override
    public List<Map<String, Object>> getExportWscl(Date startDate, Date endDate) {
        List<Map<String, Object>> list = new ArrayList();
        String begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate);
        String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
        Aggregation aggregation = newAggregation(
                match(Criteria.where("time").gte(startDate).lt(endDate).and("xzqh").ne("610000").and("plateNumbers").regex("^陕","i")),
                group("plateNumbers","xzqh","name").count().as("coun"),
                project("plateNumbers","xzqh","name","coun"),
                sort(DESC, "coun")
        );
        AggregationResults<OutCarDetail> results = mongoTemplate.aggregate(aggregation,"gpssj_tj_detail",OutCarDetail.class);
        List<OutCarDetail> carList = results.getMappedResults();
        for(OutCarDetail outcar:carList){
            Map<String, Object> map = new HashMap();
            map.put("plateNumbers",outcar.getPlateNumbers());
            map.put("xzqh",outcar.getXzqh());
            map.put("name",outcar.getName());
            map.put("coun",outcar.getCoun()*10+"分钟");
            map.put("time",begin+"-"+end);
            list.add(map);
        }
        return list;
    }
}
