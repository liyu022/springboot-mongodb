package com.hdsx.lwgl.statanalysis.util.cluster;

import com.hdsx.lwgl.statanalysis.entity.MainTainCluster;
import com.hdsx.lwgl.statanalysis.entity.T_BLOCK_YH_CURRENT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MTClusterManager {
    private int index = 1;
    private List<MainTainCluster> mtctManager;

    public MTClusterManager(){
        this.mtctManager = new ArrayList<MainTainCluster>();
    }

    public MainTainCluster create(T_BLOCK_YH_CURRENT mt){
        MainTainCluster mtc = new MainTainCluster();
        mtc.setId(this.index ++);
        mtc.setRoadcode(mt.getRoadcode());
        mtc.setRoadname(mt.getRoadname());
        mtc.setDirection(mt.getDirection());
        mtc.setRoadstart(mt.getRoadstart());
        mtc.setRoadend(mt.getRoadend());
        mtc.setData(new ArrayList<T_BLOCK_YH_CURRENT>());
        this.mtctManager.add(mtc);
        return mtc;
    }

    public void addData(T_BLOCK_YH_CURRENT mt){
        MainTainCluster mtc = this.mtctManager.get(this.mtctManager.size()-1);
        if(!mtc.getData().contains(mt)){
            mtc.setDirection(mt.getDirection() == 3?mt.getDirection():mtc.getDirection());
            BigDecimal nRoadStart = mt.getRoadstart();
            BigDecimal nRoadEnd = mt.getRoadend();
            mtc.setRoadstart(mtc.getRoadstart().doubleValue()<=nRoadStart.doubleValue()?mtc.getRoadstart(): nRoadStart);
            mtc.setRoadend(mtc.getRoadend().doubleValue()<nRoadEnd.doubleValue()?nRoadEnd:mtc.getRoadend());
            mtc.getData().add(mt);
        }
    }

    public List<MainTainCluster> getClusterData(){
        return this.mtctManager;
    }

    public void clear(){
        this.index = 0;
        if(this.mtctManager != null){
            this.mtctManager.clear();
            this.mtctManager = null;
        }
    }
}
