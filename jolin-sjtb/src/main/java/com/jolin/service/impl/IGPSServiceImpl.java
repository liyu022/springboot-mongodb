package com.jolin.service.impl;
import com.jolin.entity.GPSEntity;
import com.jolin.entity.OutCarEntity;
import com.jolin.mapper.GPSMapper;
import com.jolin.service.IGPSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IGPSServiceImpl implements IGPSService {

    @Autowired
    private GPSMapper gpsMapper;

    @Override
    public List<GPSEntity> GetGPSList() {
       return gpsMapper.GetGPSList();
    }

    @Override
    public Date GetGPSMaxTime() {
        return gpsMapper.GetGPSMaxTime();
    }

    @Override
    public Boolean GetGPSSynchronous() {
        return true;
    }

    @Override
    public List<GPSEntity> GetGPSListByDate(Date addtime, Date gpszx) {
        Map<String,Date> map = new HashMap();
        map.put("addtime",addtime);
        map.put("gpszx",gpszx);
        return gpsMapper.GetGPSListByDate(map);
    }

    @Override
    public Date outGetGPSMaxTime() {
        return gpsMapper.outGetGPSMaxTime();
    }

    @Override
    public List<OutCarEntity> outGetGPSList() {
        return gpsMapper.outGetGPSList();
    }

    @Override
    public List<OutCarEntity> OurGetGPSListByDate(Date timestampstr, Date oracleCarMaxTime,int beginnumber,int endnumber) {
        Map<String,Object> map = new HashMap();
        map.put("mongodbmaxtime",timestampstr);
        map.put("oraclemaxtime",oracleCarMaxTime);
        map.put("beginnumber",beginnumber);
        map.put("endnumber",endnumber);
        return gpsMapper.OutGetGPSListByDate(map);
    }
}
