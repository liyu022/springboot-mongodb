package com.hdsx.lwgl.statanalysis.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IYdpmService {

    /**
     * 获取拥堵排名数据信息
     * @return
     */
    List getTriffic(String right);

    /**
     * 获取热力图信息查询
     * @return
     */
    List getHeatTriffic(String right);

    /**
     * 获取历史拥堵排名数据信息
     * @param startDate
     * @param endDate
     * @return
     */
    List getHisTriffic(Date startDate, Date endDate);

    /**
     *  获取指定时刻热力图信息
     * @param sjsj
     * @return
     */
    List getHisHeatTriffic(Date sjsj);

    /**
     * 获取指定时间区间拥堵数据时间序列
     * @param startDate
     * @param endDate
     * @return
     */
    List getTimeList(Date startDate, Date endDate);

    /**
     * 导出数据--获取拥堵排名数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String,Object>> getExportTriffic(Date startDate, Date endDate);
}
