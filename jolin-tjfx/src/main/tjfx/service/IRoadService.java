package com.hdsx.lwgl.statanalysis.service;

import com.hdsx.lwgl.statanalysis.entity.FeatureLine;

import java.util.List;

/**
 * 路线信息业务接口
 */
public interface IRoadService {
    /**
     * 通过路线代码查询路线
     *
     * @param lxdm
     * @return
     */
    List<FeatureLine> findLine(String right, String lxdm);
}
