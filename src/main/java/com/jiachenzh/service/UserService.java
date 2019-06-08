package com.jiachenzh.service;

import com.jiachenzh.entity.UserDO;

import java.util.List;

public interface UserService {

    List<UserDO> findAll();

    void insertUser(UserDO userDO, String verificationCode);

    UserDO logIn(UserDO userDO);

}
