package com.hdsx.lwgl.statanalysis.util.cluster;

import com.hdsx.lwgl.statanalysis.entity.MainTainCluster;
import com.hdsx.lwgl.statanalysis.entity.T_BLOCK_YH_CURRENT;
import com.hdsx.lwgl.statanalysis.util.GisUtil;
import com.vividsolutions.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class MainTainScan {
    private double radius;
    private int minPts;
    private MTClusterManager manager;

    public MainTainScan (double meters,int minPts){
        this.radius = GisUtil.meterToRadian(meters);//领域半径参数
        this.minPts = minPts;//领域密度值，该领域内有多少个样本
        manager = new MTClusterManager();
    }

    public void process(List<List<T_BLOCK_YH_CURRENT>> mmtList) {
        for(List<T_BLOCK_YH_CURRENT> mtList : mmtList){
            for(int idx = 0;idx<mtList.size();idx ++) {
                // /样本总数
                T_BLOCK_YH_CURRENT mt = mtList.get(idx);
                Geometry p = GisUtil.wkt2Geo(mt.getCenterPoint());
                if (!mt.isVisit()) {
                    mt.setVisit(true);
                    //计算两点距离，看是否在领域内
                    ArrayList<T_BLOCK_YH_CURRENT> adjacentPoints = getAdjacentPoints(p, mtList);
                    if (adjacentPoints != null && adjacentPoints.size() < minPts) {
                        //噪音数据
                        mt.setNoised(true);
                    } else {
                        //建立该点作为领域核心对象
                        this.manager.create(mt);
                        this.manager.addData(mt);
                        for (int i = 0; i < adjacentPoints.size(); i++) {
                            T_BLOCK_YH_CURRENT adjacentPoint = adjacentPoints.get(i);//领域内的样本
                            if (!adjacentPoint.isVisit()) {
                                adjacentPoint.setVisit(true);
                                Geometry pi = GisUtil.wkt2Geo(mt.getCenterPoint());
                                ArrayList<T_BLOCK_YH_CURRENT> adjacentAdjacentPoints = getAdjacentPoints(pi, mtList);
                                if (adjacentAdjacentPoints != null && adjacentAdjacentPoints.size() >= minPts) {
                                    for (T_BLOCK_YH_CURRENT pp : adjacentAdjacentPoints){
                                        if (!adjacentPoints.contains(pp)){
                                            adjacentPoints.add(pp);
                                        }
                                    }
                                }
                            }
                            if (adjacentPoint.isNoised()) {
                                adjacentPoint.setNoised(false);
                            }
                        }
                        for (T_BLOCK_YH_CURRENT adjacentPoint : adjacentPoints) {
                            this.manager.addData(adjacentPoint);
                        }
                    }
                }
            }
        }
    }

    public List<MainTainCluster> getClusterData(){
        return this.manager.getClusterData();
    }

    public void clear(){
        this.manager.clear();
        this.manager = null;
    }

    private ArrayList<T_BLOCK_YH_CURRENT> getAdjacentPoints(Geometry p, List<T_BLOCK_YH_CURRENT> mtList) {
        ArrayList<T_BLOCK_YH_CURRENT> adjacentPoints = new ArrayList<T_BLOCK_YH_CURRENT>();
        for (T_BLOCK_YH_CURRENT mt :mtList) {
            Geometry _p = GisUtil.wkt2Geo(mt.getCenterPoint());
            double distance = p.distance(_p);
            if (distance<=radius) {
                adjacentPoints.add(mt);
            }
        }
        return adjacentPoints;
    }

}

/*
##DBScan算法流程图
算法：DBScan，基于密度的聚类算法
输入：
   D：一个包含n个数据的数据集
   r：半径参数
   minPts：领域密度阈值
输出：基于密度的聚类集合
标记D中所有的点为unvisted
for each p in D
	if p.visit = unvisted
		找出与点p距离不大于r的所有点集合N
		If N.size() < minPts
		   标记点p为噪声点
		Else
		   for each p' in N
		       If p'.visit == unvisted
		          找出与点p距离不大于r的所有点集合N'
		            If N'.size()>=minPts
		                将集合N'加入集合N中去
		            End if
				Else
			        If p'未被聚到某个簇
			            将p'聚到当前簇
			            If p'被标记为噪声点
			               将p'取消标记为噪声点
				        End If
			         End If
			     End If
			 End for
		 End if
	 End if
End for
*/
