package com.jarry.service;

import com.jarry.domain.User;

/**
 * Created by jarry on 2018/5/31.
 */
public interface HibernateService {

    public User getUserById(Integer id);

    public User findUserByAge(Integer age);

    public User addUser(User user);

    public void delUser(Integer id);

    public User updateUser(User user);

}
