package com.jarry.service.impl;

import com.github.pagehelper.PageHelper;
import com.jarry.DataSourceEnum;
import com.jarry.domain.User;
import com.jarry.handler.DataSourceHandler;
import com.jarry.mapper.MybatisMapper;
import com.jarry.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    @Cacheable("test")
    public List<User> findAll() {
        DataSourceHandler.setDataSource(DataSourceEnum.SLAVE.name());
        PageHelper.startPage(1,5);
        List<User> userList1 = mybatisMapper.findAll();

        DataSourceHandler.setDataSource(DataSourceEnum.MASTER.name());
        PageHelper.startPage(1,5);
        List<User> userList2 = mybatisMapper.findAll();

        return userList1;
    }

    //transactionManager属性必须是接口PlatformTransactionManager的实现类
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testTransation(){
        User user = new User();
        user.setPassword("9988");
        user.setName("纸老虎");
        user.setAge(99);
        mybatisMapper.addUser(user);

        User user2 = new User();
        user2.setPassword("19988");
        user2.setName("纸老虎纸老虎纸老虎纸老虎纸老虎纸老虎");//字段超长会报错
        user2.setAge(99);
        mybatisMapper.addUser(user2);
    }
}
