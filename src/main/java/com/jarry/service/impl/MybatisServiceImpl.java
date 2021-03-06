package com.jarry.service.impl;

import com.jarry.domain.User;
import com.jarry.domain.UserRepository;
import com.jarry.mapper.MybatisMapper;
import com.jarry.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jarry on 2018/5/31.
 */
@Service
public class MybatisServiceImpl implements MybatisService {

    @Autowired
    private MybatisMapper mybatisMapper;

    @Override
    public User getUserById(Integer id) {

        return mybatisMapper.selectOne(id);
    }

    @Override
    public User addUser(User user) {
        mybatisMapper.addUser(user);
        return user;
    }

    @Override
    public void delUser(Integer id) {
        mybatisMapper.delById(id);
    }

    @Override
    public int updateUser(User user) {
        return mybatisMapper.update(user);
    }
}
