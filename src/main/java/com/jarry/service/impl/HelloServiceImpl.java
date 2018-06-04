package com.jarry.service.impl;

import com.jarry.service.HelloService;

/**
 * Created by jarry on 2018/5/31.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String username) {
        return "您好" + username;
    }
}
