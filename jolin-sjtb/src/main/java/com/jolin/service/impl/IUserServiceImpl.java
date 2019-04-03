package com.jolin.service.impl;

import com.jolin.entity.Book;
import com.jolin.entity.Student;
import com.jolin.entity.UserEntity;
import com.jolin.mapper.UserMapper;
import com.jolin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {


//    @Autowired
//    private MongoTemplate mongoTemplate;
     @Autowired
     private UserMapper userMapper;
     @Autowired
     private BathOperatorUtil bathOperatorService;

//    @Override
//    public void save(UserEntity user) {
//        mongoTemplate.save(user);
//    }

    @Override
    public List<Student> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public List<UserEntity> BatchUserList() {
        List<UserEntity> user = new ArrayList<UserEntity>();
        user.add(new UserEntity("liyu",34,"tetst1","d电子商城",LocalDateTime.now(),new Book("454","76")));
        user.add(new UserEntity("liyu",34,"tetst1","d电子商城",LocalDateTime.now(),new Book("454","76")));
//        bathOperatorService.bulkWriteInsert(user);
        return user;
    }

    @Override
    public List<UserEntity> BatchUpdateUserList() {
        List<UserEntity> users = new ArrayList<UserEntity>();
        users.add(new UserEntity("admin",56,"tetst2","批量修改电子商城(第10次修改)",LocalDateTime.now(),new Book("5","76")));
        users.add(new UserEntity("admin",85,"tetst2","批量修改电子商城(第10次修改)",LocalDateTime.now(),new Book("6","76")));
        users.add(new UserEntity("admin",85,"tetst2","批量修改电子商城(第10次修改)",LocalDateTime.now(),new Book("7","76")));
//        bathOperatorService.bulkWriteUpdate(users);
        return users;
    }
}
