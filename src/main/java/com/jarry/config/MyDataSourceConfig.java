package com.jarry.config;

import com.github.pagehelper.PageInterceptor;
import com.jarry.DataSourceEnum;
import com.jarry.handler.MyDataSourceRouting;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jarry on 2018/7/4.
 * <p>
 * 2.使用tomcat-jdbc连接池创建数据源
 */

@Configuration
public class MyDataSourceConfig {
    /**
     * 数据源1
     * @return
     */
    @ConfigurationProperties(prefix = "multiple.db01")
    @Bean
    public DataSource dbMaster() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 数据源2
     * @return
     */
    @ConfigurationProperties(prefix = "multiple.db02")
    @Bean
    public DataSource dbSlave1() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源设置
     * 1.不能在此使用@Primary标记哪个数据源为主数据源，会报循环引用错误
     * 2.若不在此使用@Primary标记,则无法切换数据源，只会使用有@Primary标记的数据源
     * @return
     */
    @Bean
    public DataSource dynamicDataSource() {
        MyDataSourceRouting myDataSourceRouting = new MyDataSourceRouting();
        //设置默认的数据源
        myDataSourceRouting.setDefaultTargetDataSource(dbMaster());

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.MASTER.name(), dbMaster());
        targetDataSources.put(DataSourceEnum.SLAVE.name(), dbSlave1());
        myDataSourceRouting.setTargetDataSources(targetDataSources);

        return myDataSourceRouting;
    }

    /**************************** 以下为使用Mybatis时用 ****************************************/

    /**
     * 自己定义创建SqlSessionFactory,该方法不能省略,否则报有多个数据源，无法确定用哪个数据源创建，就得使用@Primary表示主数据源，那就进入了上面@Primary出现的问题
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSession() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());

        PageInterceptor pageInterceptor = new PageInterceptor();
        //pageInterceptor必须设置Properties，因为dialect在设置属性的方法中创建。
        Properties properties = new Properties();
        pageInterceptor.setProperties(properties);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        return sqlSessionFactoryBean.getObject();
    }


    /**************************** 以下为使用spring-data-jpa(hibernate)时用 ****************************************/
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
            throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new
                LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dynamicDataSource());
        factoryBean.setPackagesToScan(new String[]{"com.jarry.domain"});
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }

    @Autowired
    private Environment environment;

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("spring.jpa.show-sql", environment.getProperty("spring.jpa.show-sql"));
        //或者properties.put("hibernate.show_sql", true);

        return properties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new
                HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dynamicDataSource());
        return jpaTransactionManager;
    }

}
