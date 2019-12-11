package com.hdsx.lwgl.statanalysis.util.cluster;

import java.util.ArrayList;

public class DBScan {
    private double radius;
    private int minPts;

    public DBScan(double radius,int minPts) {
        this.radius = radius;//领域半径参数
        this.minPts = minPts;//领域密度值，该领域内有多少个样本
    }

    public void process(ArrayList<Point> points) {
        int size = points.size();
        int idx = 0;
        int cluster = 1;
        while (idx<size) {//样本总数
            Point p = points.get(idx++);
            //choose an unvisited point
            if (!p.getVisit()) {
                p.setVisit(true);//set visited
                ArrayList<Point> adjacentPoints = getAdjacentPoints(p, points);//计算两点距离，看是否在领域内
                //set the point which adjacent points less than minPts noised
                if (adjacentPoints != null && adjacentPoints.size() < minPts) {
                    p.setNoised(true);//噪音数据
                } else {//建立该点作为领域核心对象
                    p.setCluster(cluster);
                    for (int i = 0; i < adjacentPoints.size(); i++) {
                        Point adjacentPoint = adjacentPoints.get(i);//领域内的样本
                        //only check unvisited point, cause only unvisited have the chance to add new adjacent points
                        if (!adjacentPoint.getVisit()) {
                            adjacentPoint.setVisit(true);
                            ArrayList<Point> adjacentAdjacentPoints = getAdjacentPoints(adjacentPoint, points);
                            //add point which adjacent points not less than minPts noised
                            if (adjacentAdjacentPoints != null && adjacentAdjacentPoints.size() >= minPts) {
                                //adjacentPoints.addAll(adjacentAdjacentPoints);
                                for (Point pp : adjacentAdjacentPoints){
                                    if (!adjacentPoints.contains(pp)){
                                        adjacentPoints.add(pp);
                                    }
                                }
                            }
                        }
                        //add point which doest not belong to any cluster
                        if (adjacentPoint.getCluster() == 0) {
                            adjacentPoint.setCluster(cluster);
                            //set point which marked noised before non-noised
                            if (adjacentPoint.getNoised()) {
                                adjacentPoint.setNoised(false);
                            }
                        }
                    }
                    cluster++;
                }
            }
            if (idx%1000==0) {
                System.out.println(idx);
            }
        }
    }

    private ArrayList<Point> getAdjacentPoints(Point centerPoint, ArrayList<Point> points) {
        ArrayList<Point> adjacentPoints = new ArrayList<Point>();
        for (Point p:points) {
            //include centerPoint itself
            double distance = centerPoint.getDistance(p);
            if (distance<=radius) {
                adjacentPoints.add(p);
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
