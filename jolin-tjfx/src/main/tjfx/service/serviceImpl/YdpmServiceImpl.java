package com.hdsx.lwgl.statanalysis.service.serviceImpl;


import com.hdsx.lwgl.statanalysis.mapper71.TrifficMapper;
import com.hdsx.lwgl.statanalysis.service.IYdpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class YdpmServiceImpl implements IYdpmService {
    @Autowired
    private TrifficMapper trifficMapper;

    @Override
    public List getTriffic(String right) {
        Map<String, Object> param  = new HashMap<String, Object>();
        param.put("xzqh",right);
        return trifficMapper.getTriffic(param);
    }

    @Override
    public List getHeatTriffic(String right) {
        Map<String, Object> param  = new HashMap<String, Object>();
        param.put("xzqh",right);
        List<HashMap<String,Object>> list = new ArrayList();
        list = trifficMapper.getHeatTriffic(param);
        //拼接数据格式
        List returnList = new ArrayList();
        for(int i=0; i<list.size(); i++){
            List l = new ArrayList();
            l.add(list.get(i).get("ptx"));//经度
            l.add(list.get(i).get("pty"));//纬度
            l.add(list.get(i).get("value"));//拥堵里程
            returnList.add(l);
        }
        return returnList;
    }

    @Override
    public List getHisTriffic(Date startDate, Date endDate) {
        Map<String, Object> param  = new HashMap<String, Object>();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        return this.trifficMapper.getHisTriffic(param);
    }

    @Override
    public List getHisHeatTriffic(Date sjsj) {
        Map<String, Object> param  = new HashMap<String, Object>();
        param.put("sjsj",sjsj);
        List<HashMap<String,Object>> list =  this.trifficMapper.getHisHeatTriffic(param);
        //拼接数据格式
        List returnList = new ArrayList();
        for(int i=0; i<list.size(); i++){
            List l = new ArrayList();
            l.add(list.get(i).get("ptx"));//经度
            l.add(list.get(i).get("pty"));//纬度
            l.add(list.get(i).get("value"));//拥堵里程
            returnList.add(l);
        }
        return returnList;
    }

    @Override
    public List getTimeList(Date startDate, Date endDate) {
        Map<String, Object> param  = new HashMap<String, Object>();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        return this.trifficMapper.getTimeList(param);
    }

    @Override
    public List<Map<String, Object>> getExportTriffic(Date startDate, Date endDate) {
        Map<String, Object> param  = new HashMap<String, Object>();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        return this.trifficMapper.getExportTriffic(param);
    }
}
