package com.hdsx.lwgl.statanalysis.mapper71;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrifficMapper {

    List getTriffic(Map<String, Object> param);

    List getHeatTriffic(Map<String, Object> param);

    List getHisTriffic(Map<String, Object> param);

    List getHisHeatTriffic(Map<String, Object> param);

    List getTimeList(Map<String, Object> param);

    List<Map<String, Object>> getExportTriffic(Map<String, Object> param);
}
