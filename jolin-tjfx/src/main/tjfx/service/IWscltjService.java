package com.hdsx.lwgl.statanalysis.service;

import com.hdsx.lwgl.statanalysis.entity.OutCar;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 外省车辆统计接口
 */
public interface IWscltjService {
    /**
     * 外省车辆统计代码
     * @return
     */
    List<OutCar> getWscltjAll(String begintime, String endtime);

    /**
     * 导出数据--获取外省车辆数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String,Object>> getExportWscl(Date startDate, Date endDate);
}
