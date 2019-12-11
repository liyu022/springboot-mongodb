package com.hdsx.lwgl.statanalysis.service;

import com.hdsx.lwgl.statanalysis.entity.GPSMultiLineStirng;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITrfficFlowService {

    /**
     * 获取某年月份，或某月的天数
     * @param date
     * @param interval
     * @return
     */
    List getDateList(String date, String interval);

    /**
     * 获取指定日期的全省入口交通量
     * @param date
     * @param carType
     * @param interval
     * @return
     */
    List getProvinceHighWayByDate(String date, int carType, String interval);

    /**
     * 获取指定年份的各地市出入口交通量
     * @param year
     * @return
     */
    List getCityHighWayByYear(String year, String interval,String direction);

    /**
     * 获取指定日期全省入口环比交通量
     * @param date
     * @param carType
     * @return
     */
    List getRingRateByDate(String date, int carType, String interval);

    /**
     * 获取指定类型高速公路断面交通量
     * @param date
     * @param carType
     * @param interval
     * @return
     */
    Map getTrafficSection(String date, int carType, String interval);

    /**
     * 获取各地市属性信息
     * @return
     */
    List getLocalCityInfo(String level);

    /**
     * 获取本省和临时之间的来往交通量
     * @param date
     * @param carType
     * @param interval
     * @return
     */
    Map getBoundTrafficFlow(String date, int carType, String interval,String direction);

    /**
     * 导出数据--获取交调站数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String,Object>> getExportTrifficFlow(Date startDate, Date endDate);
    /**
     * 计算路段车辆gps实时排名情况
     * @return
     */
    List<GPSMultiLineStirng> ShapeCountByLine();
}
