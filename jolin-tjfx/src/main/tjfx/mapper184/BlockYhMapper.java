package com.hdsx.lwgl.statanalysis.mapper184;

import com.hdsx.lwgl.statanalysis.entity.T_BLOCK_YH_CURRENT;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BlockYhMapper {
    /**
     * 获取指定时间类型下的月份或天数
     * @param param
     * @return
     */
    List getDateList(HashMap<String, Object> param);
    /**
     * 获取指定年份每月的养护次数
     * @param param
     * @return
     */
    List statMaintainTimesPerMonth(HashMap<String, Object> param);
    /**
     * 获取指定年份每月的养护里程
     * @param param
     * @return
     */
    List statMaintainMilePerMonth(HashMap<String, Object> param);

    /**
     * 获取路线列表
     * @param param
     * @return
     */
    List<T_BLOCK_YH_CURRENT> selectRoad(Map<String, Object> param);

    /**
     * 通用查询
     * @param param
     * @return
     */
    List<T_BLOCK_YH_CURRENT> selectList(Map<String, Object> param);
}
