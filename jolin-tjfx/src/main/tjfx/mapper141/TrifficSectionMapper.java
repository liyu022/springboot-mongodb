package com.hdsx.lwgl.statanalysis.mapper141;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TrifficSectionMapper {

    List getTrafficSection(HashMap<String, Object> param);
}
