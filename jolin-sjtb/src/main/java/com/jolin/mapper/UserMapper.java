package com.jolin.mapper;


import com.jolin.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<Student> getUserList();
}
