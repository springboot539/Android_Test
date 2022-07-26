package com.example.contentprovider.dao;

import com.example.contentprovider.pojo.User;

import java.util.List;

public interface IUserDao {
    //    添加是Long类型的
    long addUser(User user);

    int delUserById(int id);

    int updateUser(User user);

    User getUserById(int id);


    List<User> listAllUser();
}
