package com.jolin.service;

import com.jolin.entity.Student;
import com.jolin.entity.UserEntity;

import java.util.List;

public interface IUserService {

//    public void save(UserEntity userEntity);

    List<Student> getUserList();

    List<UserEntity> BatchUserList();

    List<UserEntity> BatchUpdateUserList();
}
