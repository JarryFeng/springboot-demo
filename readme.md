# SpringBoot

该项目工程主要是学习springboot创建的demo,主要讲springboot整合redis、mysql、以及redis与spring-session的整合

## 技术栈

1.springboot  
2.redis  
3.mysql

## 说明

[版本1.0.0]：自定义Filter进行过滤

[版本1.0.1]：spring整合mybatis/spring整合hibernate + mysql
- 整合以及控制台打印sql语句
- hibernate实现JpaRepository即可实现CRUD
- mybatis提供注解版@Mapper和xml配置版(@MapperScan("com.jarry.mapper")和mapper-locations: classpath:mybatis/*.xml)，动态SQL拼装

[版本1.0.2]：


## 待解决的问题

- <h5 style="color:red">1.0.0版本</h5>自定义的过滤器，如何过滤@RequestParam注解?并需要了解@RequestParam在什么时候注入参数的?  
答:先来了解spring的加载过程：web.xml中配置项的加载顺序是context-param=>listener=>filter=>servlet,配置项的顺序并不会改变加载顺序
一次http请求执行的先后顺序是  filter前->interceptor前->dispatcherServlet->业务处理->interceptor后->filter后
其次在dispatcherServlet执行中是先获取请求中的请求参数(通过request.getParameterValues)，然后通过反射，执行真正的方法,所以如果要过滤@RequestParam注解其实就是在filter里面重写request.getParameterValues方法
@RequestParam其实只是对参数的一种校验，并非真正的注入，@RequestParam(required = false) String name 等同  String name



- <h5 style="color:red">1.0.1版本</h5>需要了解mybatis所有拼装sql的方式

## 参考文献

