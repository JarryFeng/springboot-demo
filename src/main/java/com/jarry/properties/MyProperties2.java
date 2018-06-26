package com.jarry.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by jarry on 2018/6/25.
 * 在自定义的xxxx.properties中添加自定定义的属性
 */

/* 或者 @Component*/
@Configuration
@PropertySource("classpath:my.properties")
public class MyProperties2 {

    @Value("${my.username}")
    private String username;

    @Value("${my.age}")
    private String age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
