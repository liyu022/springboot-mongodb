package com.jolin.service;


import com.jolin.entity.GPSEntity;

import java.util.Collection;
import java.util.List;

public interface IGPSMongodbService {

    void SaveGPS();

    List<GPSEntity> GetGPSByMongodb();

    GPSEntity GetGPSMongodbMaxTime();

    Collection insertAll(List<GPSEntity> gpslist);

    Collection updateManyByID(List<GPSEntity> gpslist);

    Collection insertAllByID(List<GPSEntity> gpslist);

    void BatchUpdateUserList(List<GPSEntity> gpslist);

    void BatchInsertUserList(List<GPSEntity> gpslist);
}
