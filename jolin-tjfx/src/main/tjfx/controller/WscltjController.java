package com.hdsx.lwgl.tjfx.controller;

import com.hdsx.lwgl.statanalysis.entity.OutCar;
import com.hdsx.lwgl.statanalysis.entity.ReturnMessage;
import com.hdsx.lwgl.statanalysis.service.IWscltjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carCount")
@Api(description = "外省车辆统计")
public class WscltjController {
    private Logger logger = LoggerFactory.getLogger(RoadController.class);

    @Autowired
    private IWscltjService iwscltjService;

    @ApiOperation(value = "统计外省车辆数量")
    @RequestMapping(value = "/getWscltjAll", method = RequestMethod.GET)
    public ReturnMessage getWscltjAll(@RequestParam(value = "startDate",required = false) String startDateStr,
                                      @RequestParam(value = "endDate",required = false) String endDateStr){
        ReturnMessage returnMessage = new ReturnMessage();
        try {
            List<OutCar> list = iwscltjService.getWscltjAll(startDateStr,endDateStr);
            if (list == null || list.isEmpty()) {
                returnMessage.setCode(0);
                returnMessage.setMessage("查无数据！");
                logger.warn("查无数据！！");
            } else {
                returnMessage.setCode(1);
                returnMessage.setData(list);
                returnMessage.setMessage("外省车辆数量查询成功！");
                logger.info("外省车辆数量查询成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMessage.setCode(0);
            returnMessage.setData(null);
            returnMessage.setMessage("外省车辆数量查询失败！");
            logger.error("外省车辆数量查询失败！");
        }
        return returnMessage;
    }
}
