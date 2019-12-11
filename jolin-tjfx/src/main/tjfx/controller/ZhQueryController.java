package com.hdsx.lwgl.tjfx.controller;

import com.github.pagehelper.Page;
import com.hdsx.lwgl.statanalysis.entity.ReturnMessage;
import com.hdsx.lwgl.statanalysis.service.IZhQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/ZhQuery"})
@Api(description="综合查询服务")
public class ZhQueryController
{
    private Logger logger = LoggerFactory.getLogger(ZhQueryController.class);

    @Autowired
    private IZhQueryService zhQueryService;

    @ApiOperation("路政执法信息查询")
    @GetMapping({"/lzzfQuery"})
    public ReturnMessage getLzzfQueryList(@RequestParam(value="ajbh", required=false) String ajbh, @RequestParam(value="ajmc", required=false) String ajmc, @RequestParam(value="ajlx", required=false) String ajlx, @RequestParam(value="ajclzcph", required=false) String ajclzcph, @RequestParam(value="zfjgmc", required=false) String zfjgmc, @RequestParam(value="begintime", required=false) String begintime, @RequestParam(value="endtime", required=false) String endtime, @RequestParam(value="pageNum", required=true) int pageNum, @RequestParam(value="pageSize", required=true) int pageSize)
    {
        Map param = new HashMap();
        ReturnMessage returnMessage = new ReturnMessage();
        param.put("ajbh", ajbh);
        param.put("ajmc", ajmc);
        param.put("ajlx", ajlx);
        param.put("ajclzcph", ajclzcph);
        param.put("zfjgmc", zfjgmc);
        param.put("begintime", begintime);
        param.put("endtime", endtime);
        param.put("pageNum", Integer.valueOf(pageNum));
        param.put("pageSize", Integer.valueOf(pageSize));
        try {
            Page page = this.zhQueryService.getPageLzzfAllDate(param);
            returnMessage.setTotal(Long.valueOf(page.getTotal()));
            returnMessage.setCode(1);
            returnMessage.setData(page);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("获取路政执法信息查询数据时发生错误！");
            returnMessage.setCode(0);
            returnMessage.setMessage("获取路政执法信息查询时发生错误！");
        }
        return returnMessage;
    }

    @ApiOperation("治超处罚信息查询")
    @GetMapping({"/zccfQuery"})
    public ReturnMessage getZccfQueryList(@RequestParam(value="chepaihao", required=false) String chepaihao, @RequestParam(value="daoluyunzh", required=false) String daoluyunzh, @RequestParam(value="anyou", required=false) String anyou, @RequestParam(value="huowumingc", required=false) String huowumingc, @RequestParam(value="chufashuhao", required=false) String chufashuhao, @RequestParam(value="begintime", required=false) String begintime, @RequestParam(value="endtime", required=false) String endtime, @RequestParam(value="pageNum", required=true) int pageNum, @RequestParam(value="pageSize", required=true) int pageSize)
    {
        ReturnMessage returnMessage = new ReturnMessage();
        Map param = new HashMap();
        param.put("chepaihao", chepaihao);
        param.put("daoluyunzh", daoluyunzh);
        param.put("anyou", anyou);
        param.put("huowumingc", huowumingc);
        param.put("chufashuhao", chufashuhao);
        param.put("begintime", begintime);
        param.put("endtime", endtime);
        param.put("pageNum", Integer.valueOf(pageNum));
        param.put("pageSize", Integer.valueOf(pageSize));
        try {
            Page page = this.zhQueryService.getPagezccfAllDate(param);
            returnMessage.setTotal(Long.valueOf(page.getTotal()));
            returnMessage.setCode(1);
            returnMessage.setData(page);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("获取治超处罚信息查询数据时发生错误！");
            returnMessage.setCode(0);
            returnMessage.setMessage("获取治超处罚信息查询数据时发生错误！");
        }
        return returnMessage;
    }
}
