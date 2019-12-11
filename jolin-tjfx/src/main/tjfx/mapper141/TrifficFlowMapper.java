package com.hdsx.lwgl.statanalysis.mapper141;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TrifficFlowMapper {

    /**
     * 获取指定时间类型下的月份或天数
     * @param param
     * @return
     */
    List getDateList(HashMap<String, Object> param);

    /**
     * 获取指定条件每天的交通量
     * @param param
     * @return
     */
    List getProvinceHighWayByDate(HashMap<String, Object> param);

    /**
     * 获取指定条件环比交通量
     * @param param
     * @return
     */
    List getRingRateByDate(HashMap<String, Object> param);

    List<Map<String, Object>> getExportTrifficFlow(Map<String, Object> param);
}
