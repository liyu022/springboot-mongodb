package com.hdsx.lwgl.tjfx.controller;

import com.hdsx.lwgl.statanalysis.entity.ReturnMessage;
import com.hdsx.lwgl.statanalysis.service.IYdpmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ydpm")
@Api(description = "交通流量统计服务")
public class YdpmController {

    private Logger logger = LoggerFactory.getLogger(YdpmController.class);

    @Autowired
    private IYdpmService ydpmService;


    @ApiOperation(value = "获取拥堵排名列表")
    @GetMapping("/getYdList")
    public ReturnMessage getYdList(@RequestHeader(value = "distcode",required = false) String distcode){
        if(distcode!=null && !distcode.isEmpty()){
            distcode = distcode.replaceAll("00","");
        }else{
            distcode = "61";
        }
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setCode(1);
        returnMessage.setData(ydpmService.getTriffic(distcode));
        return returnMessage;
    }

    @ApiOperation(value = "获取拥堵热力数据")
    @GetMapping("/getYdHeatData")
    public ReturnMessage getYdHeatData(@RequestHeader(value = "distcode",required = false) String distcode){
        if(distcode!=null && !distcode.isEmpty()){
            distcode = distcode.replaceAll("00","");
        }else{
            distcode = "61";
        }
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setCode(1);
        returnMessage.setData(ydpmService.getHeatTriffic(distcode));
        return returnMessage;
    }

    @ApiOperation(value = "获取历史拥堵排名列表")
    @GetMapping("/getYdHisList")
    public ReturnMessage getYdHisList(@RequestParam(value = "startDate",required = true) String startDateStr,
                                      @RequestParam(value = "endDate",required = true) String endDateStr){
        ReturnMessage returnMessage = new ReturnMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate;
        Date endDate;
        try {
            startDate = sdf.parse(startDateStr + " 00:00:00");
            endDate = sdf.parse(endDateStr + " 23:59:59");
        } catch (ParseException e1) {
            returnMessage.setCode(0);
            returnMessage.setMessage("非法参数");
            return returnMessage;
        }
        Map data = new HashMap<String,Object>();
        try {
            List ydData = this.ydpmService.getHisTriffic(startDate,endDate);
            List timeData = this.ydpmService.getTimeList(startDate,endDate);
            data.put("ydData",ydData);
            data.put("timeData",timeData);
            returnMessage.setCode(1);
            returnMessage.setData(data);
        }catch (Exception e) {
            returnMessage.setCode(0);
            returnMessage.setMessage("获取历史拥堵排名列表时发生失败！");
            logger.error("获取历史拥堵排名列表时发生失败！" + e.getMessage());
        }
        return returnMessage;
    }

    @ApiOperation(value = "获取历史拥堵热力数据")
    @GetMapping("/getYdHisHeatData")
    public ReturnMessage getYdHisHeatData(@RequestParam(value = "sjsj",required = true) long sjsjLong){
        ReturnMessage returnMessage = new ReturnMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
        Date sjsjDate;
        try {
            sjsjDate = new Date(sjsjLong);
            String sjsjStr = sdf.format(sjsjDate);
            System.out.println("getYdHisHeatData-sjsjDate:" + sjsjStr);
        } catch (Exception e1) {
            returnMessage.setCode(0);
            returnMessage.setMessage("非法参数");
            return returnMessage;
        }
        Map data = new HashMap<String,Object>();
        try {
            List heatData = this.ydpmService.getHisHeatTriffic(sjsjDate);
            returnMessage.setCode(1);
            returnMessage.setData(heatData);
        }catch (Exception e) {
            returnMessage.setCode(0);
            returnMessage.setMessage("获取历史拥堵热力数据时发生失败！");
            logger.error("获取历史拥堵热力数据时发生失败！" + e.getMessage());
        }
        return returnMessage;
    }
}
