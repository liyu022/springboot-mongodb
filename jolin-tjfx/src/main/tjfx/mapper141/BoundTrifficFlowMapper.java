package com.hdsx.lwgl.statanalysis.mapper141;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoundTrifficFlowMapper {

    /**
     * 获取从本省驶出的交通量
     * @param param
     * @return
     */
    List getTrafficFlowOut(HashMap<String, Object> param);

    /**
     * 获取驶入本省的交通量
     * @return
     */
    List getTrafficFlowIn(HashMap<String, Object> param);

    /**
     * 获取最后数据时间
     * @return
     */
    HashMap<String, Object> getLstDataTime();
}
