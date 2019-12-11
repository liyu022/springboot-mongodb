package com.hdsx.lwgl.statanalysis.util.cluster;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;


public class Data {
    private static DecimalFormat df=(DecimalFormat) NumberFormat.getInstance();

    //随机生成数据
    public static ArrayList<Point> generateSinData(int size) {
        ArrayList<Point> points = new ArrayList<Point>(size);
        Random rd = new Random(size);
        for (int i=0;i<size/2;i++) {
            double x = format(Math.PI / (size / 2) * (i + 1));
            double y = format(Math.sin(x)) ;
            points.add(new Point(x,y));
        }
        for (int i=0;i<size/2;i++) {
            double x = format(1.5 + Math.PI / (size/2) * (i+1));
            double y = format(Math.cos(x));
            points.add(new Point(x,y));
        }
        return points;
    }

    //输入指定数据
    public static ArrayList<Point> generateSpecialData() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(2,2));
        points.add(new Point(3,1));
        points.add(new Point(3,4));
        points.add(new Point(3,14));
        points.add(new Point(5,3));
        points.add(new Point(8,3));
        points.add(new Point(8,6));
        points.add(new Point(9,8));
        points.add(new Point(10,4));
        points.add(new Point(10,7));
        points.add(new Point(10,10));
        points.add(new Point(10,14));
        points.add(new Point(11,13));
        points.add(new Point(12,7));
        points.add(new Point(12,15));
        points.add(new Point(14,7));
        points.add(new Point(14,9));
        points.add(new Point(14,15));
        points.add(new Point(15,8));
        return points;
    }

    //获取文件数据
    public static ArrayList<Point> getData(String sourcePath) {
        ArrayList<Point> points = new ArrayList<Point>();
        File fileIn = new File(sourcePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileIn));
            String line = null;
            line = br.readLine();
            while (line != null) {
                Double x = Double.parseDouble(line.split(",")[3]);
                Double y = Double.parseDouble(line.split(",")[4]);
                points.add(new Point(x, y));
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    //输出到文件
    public static void writeData(ArrayList<Point> points,String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (Point point:points) {
                bw.write(point.toString()+"\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double format(double x) {
        return Double.valueOf(df.format(x));
    }

}
