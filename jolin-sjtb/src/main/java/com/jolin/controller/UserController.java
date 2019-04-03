package com.jolin.controller;

import com.jolin.entity.Book;
import com.jolin.entity.Student;
import com.jolin.entity.UserEntity;
import com.jolin.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("user")
@Api(description = "用户")
public class UserController {

    @Resource
    private IUserService iUserService;

    @ApiOperation(value = "存储用户信息")
    @RequestMapping(value = "getSaveUserEntity", method = RequestMethod.GET)
    public void UserSave(){
        UserEntity userEntity = new UserEntity(new Long(13),"",10,"","",LocalDateTime.now(),new Book());
//        iUserService.save(userEntity);
    }

    @ApiOperation(value = "存储用户信息")
    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public List<Student> getUserList(){
        List<Student> userlist = iUserService.getUserList();
        return userlist;
    }

    @ApiOperation(value = "批量存储用户信息")
    @RequestMapping(value = "BatchUserList", method = RequestMethod.GET)
    public List<UserEntity> BatchUserList(){
        List<UserEntity> userlist = iUserService.BatchUserList();
        return userlist;
    }
    @ApiOperation(value = "批量修改用户信息")
    @RequestMapping(value = "BatchUpdateUserList", method = RequestMethod.GET)
    public List<UserEntity> BatchUpdateUserList(){
        List<UserEntity> userlist = iUserService.BatchUpdateUserList();
        return userlist;
    }
}
