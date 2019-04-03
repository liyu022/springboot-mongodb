package com.jolin.service.impl;

import com.hdsx.lwgl.sjtb.entity.GPSEntity;
import com.hdsx.lwgl.sjtb.mapper.GPSMapper;
import com.hdsx.lwgl.sjtb.service.IGPSService;
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
}
