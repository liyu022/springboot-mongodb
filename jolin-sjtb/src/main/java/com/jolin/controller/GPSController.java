package com.jolin.controller;
import com.jolin.entity.GPSEntity;
import com.jolin.entity.OutCarEntity;
import com.jolin.service.IGPSMongodbService;
import com.jolin.service.IGPSService;
import com.jolin.util.DateConvert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("GPS")
@Api(description = "GPS数据同步")
public class GPSController {
    private Logger logger = LoggerFactory.getLogger(GPSController.class);
    @Resource
    private IGPSService iGPSService;

    @Resource
    private IGPSMongodbService iGPSMongodbService;

    @ApiOperation(value = "获取GPS信息")
    @RequestMapping(value = "getGPSList", method = RequestMethod.GET)
    public List<GPSEntity> GetGPSList(){
        List<GPSEntity> gps =  iGPSService.GetGPSList();
        return gps;
    }

    @ApiOperation(value = "存储GPS信息")
    @RequestMapping(value = "SaveGPS", method = RequestMethod.GET)
    public void SaveGPS(){
        iGPSMongodbService.SaveGPS();
    }

    @ApiOperation(value = "查询GPS信息")
    @RequestMapping(value = "GetGPSByMongodb", method = RequestMethod.GET)
    public List<GPSEntity> GetGPSByMongodb(){
        List<GPSEntity> gps =iGPSMongodbService.GetGPSByMongodb();
        return gps;
    }
    @ApiOperation(value = "查询mongodb数据库最新一条数据时间")
    @RequestMapping(value = "GetGPSMongodbMaxTime", method = RequestMethod.GET)
    public GPSEntity GetGPSMongodbMaxTime(){
        GPSEntity gps =iGPSMongodbService.GetGPSMongodbMaxTime();
        return gps;
    }
    @ApiOperation(value = "查询数据中心最新一条数据时间")
    @RequestMapping(value = "GetGPSMaxTime", method = RequestMethod.GET)
    public Date GetGPSMaxTime(){
        Date gps =iGPSService.GetGPSMaxTime();
        return gps;
    }
    @ApiOperation(value = "同步数据中心的数据")
    @RequestMapping(value = "GetGPSSynchronous", method = RequestMethod.GET)
    public Boolean GetGPSSynchronous(){
        long startTime=System.currentTimeMillis();
        System.out.println("获取批处处理同步mongdb数据开始时间："+ startTime );
        GPSEntity gps = iGPSMongodbService.GetGPSMongodbMaxTime();
        Date gpszx = iGPSService.GetGPSMaxTime();
        long endTime=System.currentTimeMillis();
        System.out.println("获取批处处理同步mongdb数据开始时间："+ endTime );
        System.out.println("第一步：查询数据库最新时间,总共耗时间："+(float)(endTime-startTime)/1000+"秒");
        if(gps != null && gpszx !=null){
            if(DateConvert.isAfterDate(gpszx,gps.getAttributes().getAddtime())){
                long statTime1=System.currentTimeMillis();
                List<GPSEntity> gpslist =iGPSService.GetGPSListByDate(gps.getAttributes().getAddtime(),gpszx);
                long endTime1=System.currentTimeMillis();
                System.out.println("第二步：查询数据中心数据工"+ gpslist.size()+"条,总共耗时间："+(float)(endTime1-statTime1)/1000+"秒");
                long statTime2=System.currentTimeMillis();
                if(gpslist!=null)iGPSMongodbService.BatchUpdateUserList(gpslist);
                long endTime2=System.currentTimeMillis();
                System.out.println("第三步：修改数据共耗时间："+(float)(endTime2-statTime2)/1000+"秒");
//                if(gpslist!=null)iGPSMongodbService.BatchInsertUserList(gpslist);
//                long endTime3=System.currentTimeMillis();
//                System.out.println("第三步：新增数据耗时间："+(float)(endTime3-endTime2)/1000+"秒");
                System.out.println("最后总计："+(float)(endTime2-startTime)/1000+"秒");
            }else {
                System.out.println("同步数据库中无需要同步的数据----------->");
            }
        }else if(gps != null && gpszx ==null){
            logger.info("【数据中心的数据被重新清理，mongodb同步库有异常数据，重新同步】：源数据库中最大数据更新时间为空，无可同步数据！");
        }else if(gps == null && gpszx !=null){
            logger.info("【数据中心新增数据，mongodb同步库无历史数据，重新同步】：源数据库中最大数据更新时间为空，无可同步数据！");
            long startTime3=System.currentTimeMillis();
            System.out.println("获取查询数据据中心当前时间："+ startTime3 );
            List<GPSEntity> gpslist =  iGPSService.GetGPSList();
            long endTime3=System.currentTimeMillis();
            System.out.println("获取查询数据据中心结束时间："+ startTime3 );
            System.out.println("查询数据中心数据工"+ gpslist.size()+"条,总共耗时间："+(float)(endTime3-startTime3)/1000+"秒");
            long startMoTime=System.currentTimeMillis();
            System.out.println("--------------------业务分界线--------------------------------" );
            System.out.println("准备插入mongodb数据当前时间："+ startMoTime );
            Collection coll = iGPSMongodbService.insertAll(gpslist);
            long endMoTime=System.currentTimeMillis();
            System.out.println("准备插入mongodb数据当前时间："+ endMoTime );
            System.out.println("同步插入mongodb数据库数据"+ coll.size()+"条,总共耗时间："+(float)(endMoTime-startMoTime)/1000+"秒");
        }
        return true;
    }
    @ApiOperation(value = "同步数据中心外省车辆的数据")
    @RequestMapping(value = "outGetGPSSynchronous", method = RequestMethod.GET)
    public Boolean outGetGPSSynchronous(){
        long startTime=System.currentTimeMillis();
        System.out.println("获取批处处理同步外省车辆mongdb数据开始时间："+ startTime );
        OutCarEntity gps = iGPSMongodbService.outGetGPSMongodbMaxTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date initDate = null;
        try {
            initDate=simpleDateFormat.parse("2019-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date oracleCarMaxTime = iGPSService.outGetGPSMaxTime();
        long endTime=System.currentTimeMillis();
        System.out.println("获取批处处理同步mongdb数据开始时间："+ endTime );
        System.out.println("第一步：查询数据库最新时间,总共耗时间："+(float)(endTime-startTime)/1000+"秒");
        // if(gps != null && oracleCarMaxTime !=null){
            if(DateConvert.isAfterDate(oracleCarMaxTime,initDate)){
                long statTime1=System.currentTimeMillis();
                for(int i=0;i<20000;i++){
                    List<OutCarEntity> gpslist =iGPSService.OurGetGPSListByDate(initDate,oracleCarMaxTime,i*100000,(i+1)*100000);
                    if(gpslist!=null){
                        iGPSMongodbService.insertOutCarAll(gpslist);
                        System.out.println("同步了第："+i*100000+1+"秒----至第"+(i+1)*100000+"条数据");
                    }else{
                        System.out.println("我调出来了数据已处理完成");
                        break;
                    }

                }
                long endTime2=System.currentTimeMillis();
                System.out.println("最后总计："+(float)(endTime2-startTime)/1000+"秒");
            }else {
                System.out.println("同步数据库中无需要同步的数据----------->");
            }
        /*}else if(gps != null && oracleCarMaxTime ==null){
            logger.info("【数据中心的数据被重新清理，mongodb同步库有异常数据，重新同步】：源数据库中最大数据更新时间为空，无可同步数据！");
        }else if(gps == null && oracleCarMaxTime !=null){
            logger.info("【数据中心新增数据，mongodb同步库无历史数据，重新同步】：源数据库中最大数据更新时间为空，无可同步数据！");
            long startTime3=System.currentTimeMillis();
            System.out.println("获取查询数据据中心当前时间："+ startTime3 );
            List<OutCarEntity> gpslist =  iGPSService.outGetGPSList();
            long endTime3=System.currentTimeMillis();
            System.out.println("获取查询数据据中心结束时间："+ startTime3 );
            System.out.println("查询数据中心数据工"+ gpslist.size()+"条,总共耗时间："+(float)(endTime3-startTime3)/1000+"秒");
            long startMoTime=System.currentTimeMillis();
            System.out.println("--------------------业务分界线--------------------------------" );
            System.out.println("准备插入mongodb数据当前时间："+ startMoTime );
            Collection coll = iGPSMongodbService.insertOutCarAll(gpslist);
            long endMoTime=System.currentTimeMillis();
            System.out.println("准备插入mongodb数据当前时间："+ endMoTime );
            System.out.println("同步插入mongodb数据库数据"+ coll.size()+"条,总共耗时间："+(float)(endMoTime-startMoTime)/1000+"秒");*/
       // }
        return true;
    }
}
