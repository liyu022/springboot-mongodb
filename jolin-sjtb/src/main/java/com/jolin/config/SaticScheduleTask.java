package com.jolin.config;

import com.jolin.entity.GPSEntity;
import com.jolin.service.IGPSMongodbService;
import com.jolin.service.IGPSService;
import com.jolin.util.DateConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    private Logger logger = LoggerFactory.getLogger(SaticScheduleTask.class);
    //3.添加定时任务
    @Resource
    private IGPSService iGPSService;

    @Resource
    private IGPSMongodbService iGPSMongodbService;

//    @Scheduled(fixedRate=10000)
    private void configureTasks() {
        System.err.println("执行静态定时任务开始时间----------: " + LocalDateTime.now());
        GPSEntity gps = iGPSMongodbService.GetGPSMongodbMaxTime();
        Date gpszx = iGPSService.GetGPSMaxTime();
        if(gps != null && gpszx !=null){
            if(DateConvert.isAfterDate(gpszx,gps.getAttributes().getAddtime())){
                List<GPSEntity> gpslist =iGPSService.GetGPSListByDate(gps.getAttributes().getAddtime(),gpszx);
                if(gpslist!=null)iGPSMongodbService.BatchUpdateUserList(gpslist);
            }else {
                System.out.println("同步数据库中无需要同步的数据----------->");
            }
        }else if(gps != null && gpszx ==null){
            logger.info("【数据中心的数据被重新清理，mongodb同步库有异常数据，重新同步】：源数据库中最大数据更新时间为空，无可同步数据！");
        }else if(gps == null && gpszx !=null){
            List<GPSEntity> gpslist =  iGPSService.GetGPSList();
            Collection coll = iGPSMongodbService.insertAll(gpslist);
        }
        System.err.println("执行静态定时任务完成时间----------: " + LocalDateTime.now());
    }
}
