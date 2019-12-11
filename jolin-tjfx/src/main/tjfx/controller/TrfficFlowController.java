package com.hdsx.lwgl.tjfx.controller;

import com.hdsx.lwgl.statanalysis.entity.GPSMultiLineStirng;
import com.hdsx.lwgl.statanalysis.entity.ReturnMessage;
import com.hdsx.lwgl.statanalysis.service.ITrfficFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trffic-flow")
@Api(description = "交通流量统计服务")
public class TrfficFlowController {

    private Logger logger = LoggerFactory.getLogger(TrfficFlowController.class);

    @Autowired
    private ITrfficFlowService trfficFlowService;


    /**
     *
     * @param date  日期字符串 格式201807
     * @param carType   车辆类型 1，2，0
     * @param interval  按类型查询 Y年,M月
     * @return
     */
    @ApiOperation(value = "全省高速公路交通量分析")
    @GetMapping("/statProvinceHighWayBar")
    public ReturnMessage statProvinceHighWayBar(@RequestParam(value = "date", required = true) String date,
                                                @RequestParam(value = "carType", required = true) int carType,
                                                @RequestParam(value = "interval",required = true) String interval){
        ReturnMessage returnMessage = new ReturnMessage();
        Map map = new HashMap();
        map.put("date",trfficFlowService.getDateList(date, interval));
        if(carType == 0){  //全部查询
            map.put("kcjtl", trfficFlowService.getProvinceHighWayByDate(date, 1, interval));
            map.put("hcjtl", trfficFlowService.getProvinceHighWayByDate(date, 2, interval));
        }else if (carType == 1){    //查询客车
            map.put("kcjtl", trfficFlowService.getProvinceHighWayByDate(date, carType, interval));
            map.put("hcjtl", trfficFlowService.getProvinceHighWayByDate(date, -1, interval));
        }else if (carType == 2){    //查询货车
            map.put("kcjtl", trfficFlowService.getProvinceHighWayByDate(date, -1, interval));
            map.put("hcjtl", trfficFlowService.getProvinceHighWayByDate(date, carType, interval));
        }
        map.put("ringrate", trfficFlowService.getRingRateByDate(date, carType, interval));
        returnMessage.setCode(1);
        returnMessage.setData(map);
        return returnMessage;
    }

    @ApiOperation(value = "各地市高速公路交通量分析")
    @GetMapping("/statCityHighWayBar")
    public ReturnMessage statLocalCityHighWayBar(@RequestParam(value = "date", required = true) String date,
                                                 @RequestParam(value = "interval",required = true, defaultValue = "y") String interval,
                                                 @RequestParam(value = "direction",required = true, defaultValue = "a") String direction){
        ReturnMessage returnMessage = new ReturnMessage();
        SimpleDateFormat df = null;
        if(interval.equals("y")){
            df = new SimpleDateFormat("yyyy"); //加上时间
        }else if(interval.equals("m")){
            df = new SimpleDateFormat("yyyyMM"); //加上时间
        }else {
            interval = ("y");
            df = new SimpleDateFormat("yyyy"); //加上时间
        }
        boolean isValidate = true;
        try {
            df.parse(date);
            //System.out.println(date);
        } catch(ParseException px) {
            isValidate = false;
        }

        if(!(direction.equals("c") || direction.equals("r") || direction.equals("a"))){
            direction = "a";
        }

        if(!isValidate){
            returnMessage.setCode(1);
            returnMessage.setMessage("date参数格式不正确！");
        }else {
            try{
                List result = trfficFlowService.getCityHighWayByYear(date,interval,direction);
                returnMessage.setCode(1);
                returnMessage.setData(result);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("获取各地市高速公路交通量数据时发生错误！");
                returnMessage.setCode(0);
                returnMessage.setMessage("获取各地市高速公路交通量数据时发生错误！");
            }
        }
        return returnMessage;
    }

    @ApiOperation(value = "获取行政区划信息")
    @GetMapping("/getLocalCityInfo")
    public ReturnMessage getLocalCityInfo(@RequestParam(value = "level", required = true) String level){
        ReturnMessage returnMessage = new ReturnMessage();
        if(!(level.equals("p") || level.equals("c"))){
            returnMessage.setCode(1);
            returnMessage.setMessage("level参数格式不正确！");
        }else {
            try{
                returnMessage.setCode(1);
                returnMessage.setData(trfficFlowService.getLocalCityInfo(level));
            }catch (Exception e){
                e.printStackTrace();
                logger.error("获取行政区划信息数据时发生错误！");
                returnMessage.setCode(0);
                returnMessage.setMessage("获取行政区划信息数据时发生错误！");
            }
        }
        return returnMessage;
    }

    @ApiOperation(value = "高速公路断面交通量分析")
    @GetMapping("/statTrafficSection")
    public ReturnMessage statTrafficSection(@RequestParam(value = "date", required = true) String date,
                                            @RequestParam(value = "carType", required = true) int carType,
                                            @RequestParam(value = "interval",required = true) String interval){
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setCode(1);
        returnMessage.setData(trfficFlowService.getTrafficSection(date, carType, interval));
        return returnMessage;
    }

    @ApiOperation(value = "省界高速公路交通量统计")
    @GetMapping("/statBoundTrafficFlow")
    public ReturnMessage statBoundTrafficFlow(@RequestParam(value = "date", required = true) String date,
                                              @RequestParam(value = "carType", required = true) int carType,
                                              @RequestParam(value = "interval",required = true) String interval,
                                              @RequestParam(value = "direction",required = true, defaultValue = "a") String direction){
        ReturnMessage returnMessage = new ReturnMessage();
        SimpleDateFormat df = null;
        if(interval.equals("y")){
            df = new SimpleDateFormat("yyyy"); //加上时间
        }else if(interval.equals("m")){
            df = new SimpleDateFormat("yyyyMM"); //加上时间
        }else {
            interval = ("y");
            df = new SimpleDateFormat("yyyy"); //加上时间
        }
        boolean isValidate = true;
        try {
            df.parse(date);
            //System.out.println(date);
        } catch(ParseException px) {
            isValidate = false;
        }

        if(!isValidate){
            returnMessage.setCode(0);
            returnMessage.setMessage("date参数格式不正确！");
        }else if(!(carType == 1 || carType == 2 || carType == 0)){
            returnMessage.setCode(0);
            returnMessage.setMessage("carType参数格式不正确！");
        }else {

            if(!(direction.equals("c") || direction.equals("r") || direction.equals("a"))){
                direction = "a";
            }

            try{
                Map data = trfficFlowService.getBoundTrafficFlow(date,carType,interval,direction);
                returnMessage.setCode(1);
                returnMessage.setData(data);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("获取省界高速公路交通量统计数据时发生错误！");
                returnMessage.setCode(0);
                returnMessage.setMessage("获取省界高速公路交通量统计数据时发生错误！");
            }
        }
        return returnMessage;
    }
    @ApiOperation(value = "计算点在线上的数据")
    @RequestMapping(value = "ShapeCountByLine", method = RequestMethod.GET)
    public ReturnMessage ShapeCountByLine(){
        ReturnMessage returnMessage = new ReturnMessage();
        try {
            List<GPSMultiLineStirng> list = trfficFlowService.ShapeCountByLine();
            returnMessage.setCode(1);
            returnMessage.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            returnMessage.setCode(0);
            returnMessage.setMessage("获取路段实时车辆数量排名失败！");
        }
        return returnMessage;
    }
}
