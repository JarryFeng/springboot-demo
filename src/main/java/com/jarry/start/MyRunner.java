package com.jarry.start;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jarry on 2018/6/28.
 * 如何解决项目启动时初始化资源
 * 在Spring Beans 都初始化之后，SpringApplication.run() 之前执行
 */
@Component
public class MyRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("CommandLineRunner 正在执行");
        System.out.println(strings);
    }
}
