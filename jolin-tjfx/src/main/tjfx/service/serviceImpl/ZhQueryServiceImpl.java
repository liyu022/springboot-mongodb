package com.hdsx.lwgl.statanalysis.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hdsx.lwgl.statanalysis.mapper141.ZhQueryMapper;
import com.hdsx.lwgl.statanalysis.service.IZhQueryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZhQueryServiceImpl implements IZhQueryService{

    @Autowired
    private ZhQueryMapper zhQueryMapper;

    public Page<HashMap<String, Object>> getPageLzzfAllDate(Map<String, Object> param)
    {
        PageHelper.startPage(param);
        return this.zhQueryMapper.getPageLzzfAllDate(param);
    }

    public Page<HashMap<String, Object>> getPagezccfAllDate(Map<String, Object> param)
    {
        PageHelper.startPage(param);
        return this.zhQueryMapper.getPagezccfAllDate(param);
    }

    public List getTimeList(String date, String interval)
    {
        List result = new ArrayList();
        HashMap param = new HashMap();
        param.put("date", date);
        param.put("interval", interval);
        result = this.zhQueryMapper.getTimeList(param);
        return result;
    }

    public List getLawEngorcementCaseStatistics(String date, String caseType, String interval)
    {
        HashMap param = new HashMap();
        param.put("date", date);
        param.put("caseType", caseType);
        param.put("interval", interval);
        return this.zhQueryMapper.getLawEngorcementCaseStatistics(param);
    }

    public List getRingRateByDate(String date, String caseType, String interval)
    {
        HashMap param = new HashMap();
        param.put("date", date);
        param.put("caseType", caseType);
        param.put("interval", interval);
        return this.zhQueryMapper.getRingRateByDate(param);
    }

    public List getLegendList()
    {
        return this.zhQueryMapper.getLegendList();
    }

    public List getBarDateList(String date, String interval)
    {
        HashMap param = new HashMap();
        param.put("date", date);
        param.put("interval", interval);
        return this.zhQueryMapper.getBarDateList(param);
    }

    public List getOverRunPunishCaseStatistics(String date, String caseType, String interval)
    {
        HashMap param = new HashMap();
        param.put("date", date);
        param.put("caseType", caseType);
        param.put("interval", interval);
        return this.zhQueryMapper.getOverRunPunishCaseStatistics(param);
    }

    public List getZCRingRateByDate(String date, String caseType, String interval)
    {
        HashMap param = new HashMap();
        param.put("date", date);
        param.put("caseType", caseType);
        param.put("interval", interval);
        return this.zhQueryMapper.getZCRingRateByDate(param);
    }

    public List getZCBarDateList(String date, String interval)
    {
        HashMap param = new HashMap();
        param.put("date", date);
        param.put("interval", interval);
        return this.zhQueryMapper.getZCBarDateList(param);
    }

    public List getZCLegendList()
    {
        return this.zhQueryMapper.getZCLegendList();
    }

    public List selectAllList(Map<String, Object> param)
    {
        return this.zhQueryMapper.selectAllList(param);
    }
}