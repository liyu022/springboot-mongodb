package com.hdsx.lwgl.statanalysis.service.serviceImpl;

import com.hdsx.lwgl.statanalysis.entity.MainTainCluster;
import com.hdsx.lwgl.statanalysis.entity.T_BLOCK_YH_CURRENT;
import com.hdsx.lwgl.statanalysis.mapper184.BlockYhMapper;
import com.hdsx.lwgl.statanalysis.service.IHyfxService;
import com.hdsx.lwgl.statanalysis.service.IRemoteService;
import com.hdsx.lwgl.statanalysis.util.GisUtil;
import com.hdsx.lwgl.statanalysis.util.cluster.MainTainScan;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@ConfigurationProperties(prefix="parames.hyfx.networkeffect")
public class HyfxServiceImpl implements IHyfxService {
    @Autowired
    private BlockYhMapper yhMapper;

    @Autowired
    IRemoteService rmiService;

    public int getMeters() {
        return meters;
    }

    public void setMeters(int meters) {
        this.meters = meters;
    }

    public int getMinPts() {
        return minPts;
    }

    public void setMinPts(int minPts) {
        this.minPts = minPts;
    }

    private int meters;
    private int minPts;

    @Override
    public List getDateList(String year) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("year", year);
        List result = yhMapper.getDateList(param);
        return result;
    }

    @Override
    public List statMaintainTimesPerMonth(String year,int closeable) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("year", year);
        param.put("closeable", closeable);
        return this.yhMapper.statMaintainTimesPerMonth(param);
    }

    @Override
    public List statMaintainMilePerMonth(String year,int closeable) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("year", year);
        param.put("closeable", closeable);
        return this.yhMapper.statMaintainMilePerMonth(param);
    }

    @Override
    public List<MainTainCluster> getNetWorkEffectList() {
        HashMap<String, Object> param = new HashMap<>();
        List<T_BLOCK_YH_CURRENT> roadList = this.yhMapper.selectRoad(param);
        List<List<T_BLOCK_YH_CURRENT>> mmtList = new ArrayList<List<T_BLOCK_YH_CURRENT>>();
        for (T_BLOCK_YH_CURRENT yh : roadList) {
            param.clear();
            param.put("roadcode", yh.getRoadcode());
            param.put("direction", new BigDecimal(yh.getDirection()));
            List<T_BLOCK_YH_CURRENT> yhList = this.yhMapper.selectList(param);
            mmtList.add(yhList);
        }
        MainTainScan mtc = new MainTainScan(this.meters,this.minPts);
        mtc.process(mmtList);
        List<MainTainCluster> resultData = mtc.getClusterData();

        List<MainTainCluster> cloneData = (List<MainTainCluster>)((ArrayList<MainTainCluster>)resultData).clone();
        for (MainTainCluster cmtc : cloneData) {
//            String roadWkt = this.rmiService.getSegment(cmtc.getRoadcode(),cmtc.getRoadstart(),cmtc.getRoadend());
//            cmtc.setGeometry(roadWkt);

            List<T_BLOCK_YH_CURRENT> yhList = cmtc.getData();
            if(yhList!=null){
                Point[] points = new Point[yhList.size()];
                int i = 0;
                for (T_BLOCK_YH_CURRENT yh: yhList) {
                    points[i++] = (Point)GisUtil.wkt2Geo(yh.getCenterPoint());
                }
                LineString line = GisUtil.createLineString(points);
                cmtc.setGeometry(GisUtil.geo2Wkt(line));
            }

            cmtc.setGeometryType("MultiLineString");
            cmtc.setRoadfullname(cmtc.getRoadcode() + "-" + cmtc.getRoadname());
            cmtc.setRoadrang(cmtc.getRoadstart().toString() + "-" + cmtc.getRoadend());
            if(cmtc.getDirection() ==1){
                cmtc.setDirectiontext("上行");
            }else if(cmtc.getDirection() ==2){
                cmtc.setDirectiontext("下行");
            }else if(cmtc.getDirection() ==3){
                cmtc.setDirectiontext("双向");
            }

        }
        mtc.clear();
        mtc = null;
        return cloneData;
    }


}
