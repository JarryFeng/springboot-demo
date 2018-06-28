package com.jarry.service.impl;

import com.jarry.domain.User;
import com.jarry.domain.UserRepository;
import com.jarry.service.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by jarry on 2018/5/31.
 */
@Service
public class HibernateServiceImpl implements HibernateService {

    @Autowired
    private UserRepository userRepository;

    /**
     *
     不能使用girlRepository.findOne()的原因 Inferred type 'S' for type parameter 'S' is not within its bound; should extends xxxxxx
     1、springboot 版本问题，将 2.0.1 版本换成 1.5.4 版本。
     2、userRepository.findOne(id);  改为 userRepository.findById(id).orElse(null);userRepository.findById(id).get()
     * @return
     */
    @Override
    public User getUserById(Integer id) {

        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByAge(Integer age) {
        return userRepository.findUserByAge(age);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delUser(Integer id){
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user){
        boolean b = userRepository.existsById(user.getId());
        if(b){
            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    @Override
    public Page<User> findAll(int page) {
        Pageable pageable = new PageRequest(page,10);
        Page<User> users = userRepository.findAll(pageable);
        for (User u : users){
            System.out.println(u.getName());
        }
        return users;
    }
}
