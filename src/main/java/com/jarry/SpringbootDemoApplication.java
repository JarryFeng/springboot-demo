package com.jarry;

import com.jarry.config.MyDataSourceConfig;
import com.jarry.properties.MyProperties;
import com.jarry.properties.MyProperties3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 主程序入口
 *
 * @EnableConfigurationProperties使用自定义属性类
 */

@EnableConfigurationProperties({MyProperties.class, MyProperties3.class})
@SpringBootApplication
public class SpringbootDemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootDemoApplication.class);
    }

    public static void main(String[] args) {
        System.out.println("应用启动之前");
        SpringApplication.run(SpringbootDemoApplication.class, args);
        System.out.println("应用启动之后");
    }
}
