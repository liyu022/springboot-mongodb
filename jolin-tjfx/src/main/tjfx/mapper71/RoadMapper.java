package com.hdsx.lwgl.statanalysis.mapper71;

import com.hdsx.lwgl.statanalysis.entity.FeatureLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoadMapper {

    /**
     * 获取路线
     *
     * @param param 查询参数
     * @return
     */
    List<FeatureLine> findLine(Map<String, Object> param);
}