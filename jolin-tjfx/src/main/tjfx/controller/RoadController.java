package com.hdsx.lwgl.tjfx.controller;

import com.hdsx.lwgl.statanalysis.entity.FeatureLine;
import com.hdsx.lwgl.statanalysis.entity.ReturnMessage;
import com.hdsx.lwgl.statanalysis.service.IRoadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/road")
@Api(description = "路线信息服务")
public class RoadController{
    private Logger logger = LoggerFactory.getLogger(RoadController.class);

    @Autowired
    private IRoadService roadService;

    @ApiOperation(value = "通过路线代码查询线路")
    @RequestMapping(value = "/findByLxdm", method = RequestMethod.GET)
    public ReturnMessage findByLxdm(@RequestHeader(value = "distcode",required = false) String distcode,
                                    @RequestParam(value = "lxdm", required = true) String lxdm){
        ReturnMessage returnMessage = new ReturnMessage();
        try {
            List<FeatureLine> list = roadService.findLine(distcode,lxdm);
            if (list == null || list.isEmpty()) {
                returnMessage.setCode(0);
                returnMessage.setMessage("查无数据！");
                logger.warn("查无数据！！");
            } else {
                returnMessage.setCode(1);
                returnMessage.setData(list);
                returnMessage.setMessage("路线坐标信息查询成功！");
                logger.info("路线坐标信息查询成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMessage.setCode(0);
            returnMessage.setData(null);
            returnMessage.setMessage("路线坐标信息查询失败！");
            logger.error("路线坐标信息查询失败！");
        }
        return returnMessage;
    }
}
