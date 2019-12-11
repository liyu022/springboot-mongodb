package com.hdsx.lwgl.statanalysis.util.cluster;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        ArrayList<Point> points = Data.generateSinData(200);//随机生成200个point
        DBScan dbScan = new DBScan(0.6,4);//r：领域半径参数 ，minPts领域密度阈值，密度值
        //ArrayList<Point> points = Data.generateSpecialData();
        //ArrayList<Point> points = Data.getData("D:\\tmp\\testData.txt");
        //DBScan dbScan = new DBScan(0.1,1000);
        // dbScan.process(points);
        for (Point p:points) {
            System.out.println(p);
        }
        //Data.writeData(points,"D:\\tmp\\data.txt");
    }

}
