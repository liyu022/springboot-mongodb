package com.hdsx.lwgl.statanalysis.service;

import java.math.BigDecimal;

public interface IRemoteService {

    /**
     * 动态分段
     * @param lxdm 路线代码
     * @return point熟知文本
     */
    String getSegment(String lxdm, BigDecimal roadstart, BigDecimal roadEnd);
}
