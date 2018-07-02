package com.jarry.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by jarry on 2018/6/29.
 * 只要简单的将拦截器生成并放入spirng容器管理即可
 * 以5.0作为分界线，作者对pageHelper进行的职责单一话
 */
@Configuration
public class MyPageHelper {

    /**
     * 5.0以后的版本
     *
     * @return
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");

        pageInterceptor.setProperties(p);
        return pageInterceptor;
    }

    /**
     * 5.0以前的版本
     *
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");

        pageHelper.setProperties(p);
        return pageHelper;
    }
}
