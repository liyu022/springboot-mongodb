package com.hdsx.lwgl.statanalysis.service.serviceImpl;
import com.hdsx.lwgl.statanalysis.entity.GPSMultiLineStirng;
import com.hdsx.lwgl.statanalysis.mapper141.BoundTrifficFlowMapper;
import com.hdsx.lwgl.statanalysis.mapper141.LocalCityTrifficFlowMapper;
import com.hdsx.lwgl.statanalysis.mapper141.TrifficFlowMapper;
import com.hdsx.lwgl.statanalysis.mapper141.TrifficSectionMapper;
import com.hdsx.lwgl.statanalysis.service.ITrfficFlowService;
import com.hdsx.lwgl.statanalysis.util.GisUtil;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TrfficFlowServiceImpl implements ITrfficFlowService {

    @Autowired
    private TrifficFlowMapper trifficFlowMapper;
    @Autowired
    private LocalCityTrifficFlowMapper localCityTrifficFlowMapper;
    @Autowired
    private TrifficSectionMapper trifficSectionMapper;

    @Autowired
    private BoundTrifficFlowMapper boundTrifficFlowMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List getDateList(String date, String interval) {
        List result = new ArrayList();
        HashMap<String, Object> param = new HashMap<>();
        param.put("date", date);
        param.put("interval", interval);
        result = trifficFlowMapper.getDateList(param);
        return result;
    }

    @Override
    public List getProvinceHighWayByDate(String date, int carType, String interval) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("date",date);
        param.put("carType", carType);
        param.put("interval", interval);
        return this.trifficFlowMapper.getProvinceHighWayByDate(param);
    }

    @Override
    public List getCityHighWayByYear(String date, String interval,String direction) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("date",date);
        param.put("interval",interval);
        param.put("direction",direction);
        List<HashMap<String,Object>> list = localCityTrifficFlowMapper.getCityHighWayByYear(param);
        List<HashMap<String,Object>> list1 = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            HashMap<String,Object> map = new HashMap<>();
            map.put("name",list.get(i).get("name"));
            List l = new ArrayList();
            l.add(list.get(i).get("lon"));
            l.add(list.get(i).get("lat"));
            map.put("coordinates",l);
            List l1 = new ArrayList();
            l1.add(list.get(i).get("ckjtl"));
            l1.add(list.get(i).get("rkjtl"));
            map.put("data",l1);
            list1.add(map);
        }
        return list1;
    }

    @Override
    public List getRingRateByDate(String date, int carType, String interval) {
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("date",date);
        param.put("interval", interval);
        if(carType == 1 || carType == 2){
            param.put("carType", carType);
        }
        return this.trifficFlowMapper.getRingRateByDate(param);
    }

    @Override
    public Map getTrafficSection(String date, int carType, String interval) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("date", date);
        param.put("carType", carType);
        param.put("interval", interval);
        List<HashMap<String,Object>> list = trifficSectionMapper.getTrafficSection(param);

        Map<String, Object> map = new HashMap<>();
        List nameList = new ArrayList();
        List kclList = new ArrayList();
        List hclList = new ArrayList();
        for(int i=0;i<list.size();i++){

            nameList.add(list.get(i).get("ldmc"));

            kclList.add(list.get(i).get("kcjtl"));

            hclList.add(list.get(i).get("hcjtl"));
        }
        map.put("name", nameList);
        map.put("kcjtl", kclList);
        map.put("hcjtl", hclList);
        return map;
    }

    @Override
    public List getLocalCityInfo(String level) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("level", level);
        return localCityTrifficFlowMapper.getLocalCityInfo(param);
    }

    @Override
    public Map getBoundTrafficFlow(String date, int carType, String interval,String direction) {
        Map<String,Object> result = new HashMap<String,Object>();

        String provinceName = null;
        //查找临近各省位置信息
        HashMap<String, Object> coordMap = new HashMap<>();
        HashMap<String, Object> param = new HashMap<>();
        param.put("level", "p");
        List list = localCityTrifficFlowMapper.getLocalCityInfo(param);
        for (Object obj: list) {
            HashMap h = (HashMap)obj;
            String code = h.get("code") == null?"":h.get("code").toString();
            String name = h.get("name") == null?"":h.get("name").toString();
            if(code!=null && code.equals("610000")){
                //获取本省名称
                provinceName = name;
            }
            BigDecimal lon = h.get("lon") == null?new BigDecimal(0): new BigDecimal(h.get("lon").toString());
            BigDecimal lat = h.get("lat") == null?new BigDecimal(0): new BigDecimal(h.get("lat").toString());
            BigDecimal[] coordArr = new BigDecimal[]{lon,lat};
            coordMap.put(name,coordArr);
        }
        result.put("coordMap",coordMap);
        if(direction!=null && (direction.equals("c") || direction.equals("a"))){
            //获取驶出的交通量
            List localJtl = new ArrayList();
            param = new HashMap<>();
            param.put("date", date);
            param.put("carType", carType);
            param.put("interval", interval);
            list = boundTrifficFlowMapper.getTrafficFlowOut(param);
            result.put("original_out",list);
            for (Object obj: list) {
                HashMap<String, Object> fromMap = new HashMap<>();
                HashMap<String, Object> toMap = new HashMap<>();
                fromMap.put("name",provinceName);

                HashMap h = (HashMap)obj;
                String name = h.get("name") == null?"":h.get("name").toString();
                BigDecimal jtl = h.get("jtl") == null?new BigDecimal(0): new BigDecimal(h.get("jtl").toString());
                toMap.put("name",name);
                toMap.put("value",jtl);

                List temp = new ArrayList();
                temp.add(fromMap);
                temp.add(toMap);
                localJtl.add(temp);
            }
            result.put(provinceName,localJtl);
        }

        if(direction!=null && (direction.equals("r") || direction.equals("a"))) {
            //获取驶入的交通量
            param = new HashMap<>();
            param.put("date", date);
            param.put("carType", carType);
            param.put("interval", interval);
            list = boundTrifficFlowMapper.getTrafficFlowIn(param);
            result.put("original_in",list);
            for (Object obj : list) {
                HashMap<String, Object> fromMap = new HashMap<>();
                HashMap<String, Object> toMap = new HashMap<>();

                HashMap h = (HashMap) obj;
                String name = h.get("name") == null ? "" : h.get("name").toString();
                if (name.equals(provinceName)) {
                    //排除本省
                    continue;
                }
                BigDecimal jtl = h.get("jtl") == null ? new BigDecimal(0) : new BigDecimal(h.get("jtl").toString());
                fromMap.put("name", name);
                toMap.put("name", provinceName);
                toMap.put("value", jtl);

                List temp = new ArrayList();
                temp.add(fromMap);
                temp.add(toMap);
                List otherJtl = new ArrayList();
                otherJtl.add(temp);
                result.put(name, otherJtl);
            }
        }

        HashMap<String, Object> map = this.boundTrifficFlowMapper.getLstDataTime();
        if(map!=null && map.containsKey("lastDataTime")) {
            result.put("lastDataTime",map.get("lastDataTime"));
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getExportTrifficFlow(Date startDate, Date endDate) {
        Map<String, Object> param  = new HashMap<String, Object>();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        return trifficFlowMapper.getExportTrifficFlow(param);
    }

    @Override
    public List<GPSMultiLineStirng> ShapeCountByLine() {
        List<GPSMultiLineStirng> byLine =mongoTemplate.findAll(GPSMultiLineStirng.class);
        Geometry geometry = null;
        Query quer = new Query();
/*        this.mongoTemplate.dropCollection("gis_sxld_count");*/
        this.mongoTemplate.remove(quer,"gis_sxld_count");
        for (GPSMultiLineStirng gpsl:byLine) {
            geometry= GisUtil.GeoJsonToGeometry(gpsl.getGeometry());
            try {
                GeoJson json = GisUtil.toMongGeoJson(GisUtil.bufferGeo(geometry, 50));
                Query query = new Query(Criteria.where("location").intersects(json));
                long cou = this.mongoTemplate.count(query,"gps_data");
                gpsl.setCou(cou);
                gpsl.setTime(new Date());
                gpsl.setGeometry(null);
                gpsl.setGeometryType(null);
                this.mongoTemplate.insert(gpsl,"gis_sxld_count");
            }catch (Exception ex){
                ex.getStackTrace();
            }
        }
        Sort sort = new Sort(Sort.Direction.DESC, "cou");
        Query query = new Query();
        List<GPSMultiLineStirng> couLine =  this.mongoTemplate.find(query.with(sort),GPSMultiLineStirng.class,"gis_sxld_count");
        return couLine;
    }
}
