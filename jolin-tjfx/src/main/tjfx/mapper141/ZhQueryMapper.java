package com.hdsx.lwgl.statanalysis.mapper141;

import com.github.pagehelper.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mapstruct.Mapper;

@Mapper
public interface ZhQueryMapper
{
    public abstract Page<HashMap<String, Object>> getPageLzzfAllDate(Map<String, Object> paramMap);

    public abstract Page<HashMap<String, Object>> getPagezccfAllDate(Map<String, Object> paramMap);

    public abstract List getTimeList(HashMap<String, Object> paramHashMap);

    public abstract List getLawEngorcementCaseStatistics(HashMap<String, Object> paramHashMap);

    public abstract List getRingRateByDate(HashMap<String, Object> paramHashMap);

    public abstract List getLegendList();

    public abstract List getBarDateList(HashMap<String, Object> paramHashMap);

    public abstract List getOverRunPunishCaseStatistics(HashMap<String, Object> paramHashMap);

    public abstract List getZCRingRateByDate(HashMap<String, Object> paramHashMap);

    public abstract List getZCBarDateList(HashMap<String, Object> paramHashMap);

    public abstract List getZCLegendList();

    public abstract List selectAllList(Map<String, Object> paramMap);
}
