package com.jarry.config;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by jarry on 2018/8/9.
 */

@Configuration
@EnableCaching
public class EhcacheConfig {

      /*
    错误案例：
    @Bean
    public CacheManager cacheManager(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        //不能直接返回ehCacheManagerFactoryBean.getObject()不能直接返回，值为空，初始化afterPropertiesSet()方法没有被调用，
        //如果要调用的话，必须交由spring容器管理ehCacheManagerFactoryBean对象
        return ehCacheManagerFactoryBean.getObject();
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(@Qualifier("cacheManager") CacheManager cacheManager){
        //由于cacheManager为空，即自己定义的bean CacheManager根本没启作用,但是为什么缓存又能用呢，因为EhCacheCacheManager对象初始化后
        //调用了afterPropertiesSet()方法，该方法会判断cacheManager是否为空，如果为空，重新加载classpath下名为ehcache.xml的文件，
        //如果该名字变更后，则无法使用ehcache缓存，会报错
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(cacheManager);
        return ehCacheCacheManager;
    }
    */

    /**
     * 有了上面错误案例的描述后，修改成如下形式
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean cacheManagerByXml(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(@Qualifier("cacheManagerByXml") CacheManager cacheManager){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(cacheManager);
        return ehCacheCacheManager;
    }

    //或者这样也行,只不过有点画蛇添足，ehCacheManagerFactoryBean()方法，最终生成beanName为ehCacheManagerFactoryBean，
    //实例对象为cacheManager = ehCacheManagerFactoryBean.getObject()
    public EhCacheCacheManager ehCacheCacheManager2(EhCacheManagerFactoryBean ehCacheManagerFactoryBean){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
        return ehCacheCacheManager;
    }
}
