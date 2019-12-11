package com.hdsx.lwgl.statanalysis.service;

import com.hdsx.lwgl.statanalysis.entity.MainTainCluster;

import java.util.List;

public interface IHyfxService {
    /**
     * 获取某年月份
     * @param year
     * @return
     */
    List getDateList(String year);

    /**
     * 获取指定年份每月的养护次数
     * @param year
     * @return
     */
    List statMaintainTimesPerMonth(String year,int closeable);
    /**
     * 获取指定年份每月的养护里程
     * @param year
     * @return
     */
    List statMaintainMilePerMonth(String year,int closeable);

    /**
     * 获取路网影响分析列表
     * @return
     */
    List<MainTainCluster> getNetWorkEffectList();
}
