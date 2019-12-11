package com.hdsx.lwgl.statanalysis.mapper141;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface LocalCityTrifficFlowMapper {

    /**
     *
     * @param param
     * @return
     */
    List getCityHighWayByYear(HashMap<String, Object> param);

    /**
     * 获取各地市属性信息
     * @return
     */
    List getLocalCityInfo(HashMap<String, Object> param);


}
