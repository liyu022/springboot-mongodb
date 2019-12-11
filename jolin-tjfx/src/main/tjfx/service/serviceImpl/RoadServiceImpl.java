package com.hdsx.lwgl.statanalysis.service.serviceImpl;

import com.hdsx.lwgl.statanalysis.entity.FeatureLine;
import com.hdsx.lwgl.statanalysis.mapper71.RoadMapper;
import com.hdsx.lwgl.statanalysis.service.IRoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 路线信息业务实现
 */
@Service
public class RoadServiceImpl implements IRoadService {

    private Logger logger = LoggerFactory.getLogger(RoadServiceImpl.class);

    @Autowired
    private RoadMapper roadMapper;

    @Override
    public List<FeatureLine> findLine(String right, String lxdm) {
        if(right!=null&& !right.isEmpty()){
            Map<String, Object> param  = new HashMap<String, Object>();
            param.put("xzqh",right);
            param.put("lxdm",lxdm);

            if(right.endsWith("0000")){
                //厅级用户
                param.put("tableName","V_JSDJ_ST");
            }else if(right.endsWith("00")){
                //地市用户
                param.put("tableName","V_JSDJ_DS");
            }else{
                //区县用户
                param.put("tableName","V_JSDJ_QX");
            }

            List<FeatureLine> _list = this.roadMapper.findLine(param);
            return  _list;

        }else {
            return null;
        }
    }
}
