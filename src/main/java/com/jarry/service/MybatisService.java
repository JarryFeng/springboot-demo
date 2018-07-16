package com.jarry.service;

import com.jarry.domain.User;

import java.util.List;

/**
 * Created by jarry on 2018/5/31.
 */
public interface MybatisService {

    public User getUserById(Integer id);

    User addUser(User user);

    void delUser(Integer id);

    int updateUser(User user);

    List<User> findAll();

    void testTransation();
}
