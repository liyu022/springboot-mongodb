package com.jolin.service;

import com.jolin.entity.GPSEntity;

import java.util.Date;
import java.util.List;

public interface IGPSService {


    Date GetGPSMaxTime();

    List<GPSEntity> GetGPSList();

    Boolean GetGPSSynchronous();

    List<GPSEntity> GetGPSListByDate(Date addtime, Date gpszx);

}
