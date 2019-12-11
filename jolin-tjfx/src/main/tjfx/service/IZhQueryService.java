package com.hdsx.lwgl.statanalysis.service;

import com.github.pagehelper.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract interface IZhQueryService
{
    public abstract Page<HashMap<String, Object>> getPageLzzfAllDate(Map<String, Object> paramMap);

    public abstract Page<HashMap<String, Object>> getPagezccfAllDate(Map<String, Object> paramMap);

    public abstract List getTimeList(String paramString1, String paramString2);

    public abstract List getLawEngorcementCaseStatistics(String paramString1, String paramString2, String paramString3);

    public abstract List getRingRateByDate(String paramString1, String paramString2, String paramString3);

    public abstract List getLegendList();

    public abstract List getBarDateList(String paramString1, String paramString2);

    public abstract List getOverRunPunishCaseStatistics(String paramString1, String paramString2, String paramString3);

    public abstract List getZCRingRateByDate(String paramString1, String paramString2, String paramString3);

    public abstract List getZCBarDateList(String paramString1, String paramString2);

    public abstract List getZCLegendList();

    public abstract List selectAllList(Map<String, Object> paramMap);
}