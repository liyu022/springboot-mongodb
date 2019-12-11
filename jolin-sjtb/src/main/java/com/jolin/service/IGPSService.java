package com.jolin.service;

import com.jolin.entity.GPSEntity;
import com.jolin.entity.OutCarEntity;

import java.util.Date;
import java.util.List;

public interface IGPSService {


    Date GetGPSMaxTime();

    List<GPSEntity> GetGPSList();

    Boolean GetGPSSynchronous();

    List<GPSEntity> GetGPSListByDate(Date addtime, Date gpszx);

    Date outGetGPSMaxTime();

    List<OutCarEntity> outGetGPSList();

    List<OutCarEntity> OurGetGPSListByDate(Date timestampstr, Date oracleCarMaxTime,int beginnumber,int endnumber);
}
