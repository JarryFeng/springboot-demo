package com.jarry.controller;

import com.jarry.domain.User;
import com.jarry.service.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jarry on 2018/6/14.
 */
@RestController
@RequestMapping("/hibernate")
public class HibernateController {

    @Autowired
    private HibernateService hibernateService;

    @RequestMapping("/user")
    public User getUserById(Integer id){
        return hibernateService.getUserById(id);
    }

    @RequestMapping("/user/{age}")
    public User findUserByAge(@PathVariable(name = "age") Integer age){
        return hibernateService.findUserByAge(age);
    }

    @RequestMapping("/user/add")
    public User addUser(Integer age, String name, String password){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        user.setPassword(password);
        return hibernateService.addUser(user);
    }

    @RequestMapping("/user/del")
    public void delUser(Integer id){
        hibernateService.delUser(id);
    }

    @RequestMapping("/user/update")
    public User updateUser(Integer id, Integer age, String name, String password){
        User user = new User();
        user.setId(id);
        user.setAge(age);
        user.setName(name);
        user.setPassword(password);
        return hibernateService.updateUser(user);
    }

    @RequestMapping("/user/findAll")
    public Page<User> findAll(int page){
        return hibernateService.findAll(page);
    }
}
