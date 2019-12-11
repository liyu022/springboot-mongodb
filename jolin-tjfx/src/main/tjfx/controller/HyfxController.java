package com.hdsx.lwgl.tjfx.controller;

import com.hdsx.lwgl.statanalysis.entity.MainTainCluster;
import com.hdsx.lwgl.statanalysis.entity.ReturnMessage;
import com.hdsx.lwgl.statanalysis.service.IHyfxService;
import com.hdsx.lwgl.statanalysis.service.IZhQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hyfx")
@Api(description = "行业分析服务")
public class HyfxController {

    private Logger logger = LoggerFactory.getLogger(HyfxController.class);

    @Autowired
    private IHyfxService hyfxService;

    @Autowired
    private IZhQueryService zhQueryService;

    @ApiOperation(value = "养护施工时段统计")
    @GetMapping("/statMaintainPeriod")
    public ReturnMessage statMaintainPeriod(@RequestParam(value = "year", required = true) String year,
                                            @RequestParam(value = "closeable", required = true) int closeable){
        closeable = (closeable ==0 || closeable ==1)? closeable: -1;
        ReturnMessage returnMessage = new ReturnMessage();
        Map map = new HashMap();
        try{
            map.put("date",hyfxService.getDateList(year));
            map.put("times", hyfxService.statMaintainTimesPerMonth(year,closeable));
            map.put("miles", hyfxService.statMaintainMilePerMonth(year,closeable));
            returnMessage.setCode(1);
            returnMessage.setData(map);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取养护施工时段统计数据时发生错误！");
            returnMessage.setCode(0);
            returnMessage.setMessage("获取养护施工时段统计数据时发生错误！");
        }
        return returnMessage;
    }

    @ApiOperation(value = "路网运行影响分析")
    @GetMapping("/NetWorkEffect")
    public ReturnMessage NetWorkEffect(){
        ReturnMessage returnMessage = new ReturnMessage();
        try{
            List<MainTainCluster> resultData = this.hyfxService.getNetWorkEffectList();
            returnMessage.setCode(1);
            returnMessage.setData(resultData);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取养护施工时段统计数据时发生错误！");
            returnMessage.setCode(0);
            returnMessage.setMessage("获取养护施工时段统计数据时发生错误！");
        }
        return returnMessage;
    }
    @ApiOperation("执法案件类型统计服务")
    @GetMapping({"/lawEngorcementCaseStatistics"})
    public ReturnMessage lawEngorcementCaseStatistics(@RequestParam(value="date", required=true) String date, @RequestParam(value="ajlx", required=true) String ajlx, @RequestParam(value="interval", required=true) String interval)
    {
        ReturnMessage returnMessage = new ReturnMessage();
        Map map = new HashMap();
        try {
            map.put("date", this.zhQueryService.getTimeList(date, interval));
            if (ajlx.equals("0")) {
                map.put("ybajtj", this.zhQueryService.getLawEngorcementCaseStatistics(date, "1", interval));
                map.put("jyajtj", this.zhQueryService.getLawEngorcementCaseStatistics(date, "2", interval));
                map.put("fxcclajtj", this.zhQueryService.getLawEngorcementCaseStatistics(date, "3", interval));
                map.put("ringrate", this.zhQueryService.getRingRateByDate(date, "0", interval));
            } else if (ajlx.equals("1")) {
                map.put("ybajtj", this.zhQueryService.getLawEngorcementCaseStatistics(date, "1", interval));
                map.put("ringrate", this.zhQueryService.getRingRateByDate(date, "1", interval));
            } else if (ajlx.equals("2")) {
                map.put("jyajtj", this.zhQueryService.getLawEngorcementCaseStatistics(date, "2", interval));
                map.put("ringrate", this.zhQueryService.getRingRateByDate(date, "2", interval));
            } else if (ajlx.equals("3")) {
                map.put("fxcclajtj", this.zhQueryService.getLawEngorcementCaseStatistics(date, "3", interval));
                map.put("ringrate", this.zhQueryService.getRingRateByDate(date, "3", interval));
            }
            returnMessage.setCode(1);
            returnMessage.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("获取执法案件类型统计服务数据时发生错误！");
            returnMessage.setCode(0);
            returnMessage.setMessage("获治执法案件类型统计服务时发生错误！");
        }
        return returnMessage;
    }

    @ApiOperation("涉案车辆归属占比统计服务")
    @GetMapping({"/involveCarAttachStatistics"})
    public ReturnMessage getInvolveCarAttachStatistics(@RequestParam(value="date", required=true) String date, @RequestParam(value="interval", required=true) String interval)
    {
        ReturnMessage returnMessage = new ReturnMessage();
        Map map = new HashMap();
        try {
            map.put("legend", this.zhQueryService.getLegendList());
            map.put("barDate", this.zhQueryService.getBarDateList(date, interval));
            returnMessage.setCode(1);
            returnMessage.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("获取涉案车辆归属占比统计服务数据时发生错误！");
            returnMessage.setCode(0);
            returnMessage.setMessage("获治涉案车辆归属占比统计服务时发生错误！");
        }
        return returnMessage;
    }

    @ApiOperation("治超处罚案由统计服务")
    @GetMapping({"/overRunPunishCaseStatistics"})
    public ReturnMessage overRunPunishCaseStatistics(@RequestParam(value="date", required=true) String date, @RequestParam(value="anyou", required=true) String anyou, @RequestParam(value="interval", required=true) String interval)
    {
        ReturnMessage returnMessage = new ReturnMessage();
        Map map = new HashMap();
        try {
            map.put("date", this.zhQueryService.getTimeList(date, interval));
            if (anyou.equals("0")) {
                map.put("wfcxystj", this.zhQueryService.getOverRunPunishCaseStatistics(date, "1", interval));
                map.put("rlcxjctj", this.zhQueryService.getOverRunPunishCaseStatistics(date, "2", interval));
                map.put("ffsytxztj", this.zhQueryService.getOverRunPunishCaseStatistics(date, "3", interval));
                map.put("tbcxjctj", this.zhQueryService.getOverRunPunishCaseStatistics(date, "4", interval));
                map.put("ringrate", this.zhQueryService.getZCRingRateByDate(date, "0", interval));
            } else if (anyou.equals("1")) {
                map.put("wfcxystj", this.zhQueryService.getOverRunPunishCaseStatistics(date, "1", interval));
                map.put("ringrate", this.zhQueryService.getZCRingRateByDate(date, "1", interval));
            } else if (anyou.equals("2")) {
                map.put("rlcxjctj", this.zhQueryService.getOverRunPunishCaseStatistics(date, "2", interval));
                map.put("ringrate", this.zhQueryService.getZCRingRateByDate(date, "2", interval));
            } else if (anyou.equals("3")) {
                map.put("ffsytxztj", this.zhQueryService.getOverRunPunishCaseStatistics(date, "3", interval));
                map.put("ringrate", this.zhQueryService.getZCRingRateByDate(date, "3", interval));
            } else if (anyou.equals("4")) {
                map.put("tbcxjctj", this.zhQueryService.getOverRunPunishCaseStatistics(date, "4", interval));
                map.put("ringrate", this.zhQueryService.getZCRingRateByDate(date, "4", interval));
            }
            returnMessage.setMessage("获取治超处罚案由统计服务成功！");
            returnMessage.setCode(1);
            returnMessage.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("获取治超处罚案由统计服务数据时发生错误！");
            returnMessage.setCode(0);
            returnMessage.setMessage("获治超处罚案由统计服务时发生错误！");
        }
        return returnMessage;
    }

    @ApiOperation("治超货物类型占比统计服务")
    @GetMapping({"/overRunPunishTypeStatistics"})
    public ReturnMessage getOverRunPunishTypeStatistics(@RequestParam(value="date", required=true) String date, @RequestParam(value="interval", required=true) String interval)
    {
        ReturnMessage returnMessage = new ReturnMessage();
        Map map = new HashMap();
        try {
            map.put("legend", this.zhQueryService.getZCLegendList());
            map.put("barDate", this.zhQueryService.getZCBarDateList(date, interval));
            returnMessage.setCode(1);
            returnMessage.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("获取治超货物类型占比统计服务数据时发生错误！");
            returnMessage.setCode(0);
            returnMessage.setMessage("获取治超货物类型占比统计服务时发生错误！");
        }
        return returnMessage;
    }
}
