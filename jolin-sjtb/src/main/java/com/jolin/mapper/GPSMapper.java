package com.jolin.mapper;
import com.jolin.entity.GPSEntity;
import com.jolin.entity.OutCarEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface GPSMapper {
    /**
     * @return
     */
    List<GPSEntity> GetGPSList();

    Date GetGPSMaxTime();

    List<GPSEntity> GetGPSListByDate(Map<String, Date> map);

    Date outGetGPSMaxTime();

    List<OutCarEntity> outGetGPSList();

    List<OutCarEntity> OutGetGPSListByDate(Map<String, Object> map);
}
