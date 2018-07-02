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

[版本1.0.2]：thymeleaf的使用以及未使用模版时如何跳转到页面  
- 将要显示的页面路径放开,既不被拦截器拦截,放开拦截方式有三种
    1. 将文件放入static文件夹下
    2. 配置静态访问资源类， 例如MvcConfigurer.java
    3. 静态文件的默认访问路径，以及默认文件夹，参见application.yml
    4. 如果要使用jsp作为动态视图文件，则需要将jsp放在webapp/WEB-INF下，并导入tomcat-embed-jasper的包
      
[版本1.0.3]：自定义properties,(1)使用@ConfigurationProperties注解指定自定义属性的前缀该注解需结合@EnableConfigurationProperties一起使用
(2)使用@PropertySource+@Component或者@Configuration

[版本1.0.4]：(1)添加spring-data-jpa对hibernate的分页对象Pageable(2)应用启动完成之前，初始化一些资源，例如初始化线程池，提前加载好加密证书等。具体详见MyRunner

[版本1.0.5]：(1)添加mybatis的分页插件PageHelper,5.0.0以前的版本直接使用PageHelper，5.0.0之后的版本改成了PageInterceptor



## 待解决的问题

- <h5 style="color:red">1.0.0版本</h5>自定义的过滤器，如何过滤@RequestParam注解?并需要了解@RequestParam在什么时候注入参数的?  
答:先来了解spring的加载过程：web.xml中配置项的加载顺序是context-param=>listener=>filter=>servlet,配置项的顺序并不会改变加载顺序
一次http请求执行的先后顺序是  filter前->interceptor前->dispatcherServlet->业务处理->interceptor后->filter后
其次在dispatcherServlet执行中是先获取请求中的请求参数(通过request.getParameterValues)，然后通过反射，执行真正的方法,所以如果要过滤@RequestParam注解其实就是在filter里面重写request.getParameterValues方法
@RequestParam其实只是对参数的一种校验，并非真正的注入，@RequestParam(required = false) String name 等同  String name



- <h5 style="color:red">1.0.1版本</h5>需要了解mybatis所有拼装sql的方式


- <h5 style="color:red">1.0.2版本</h5>需要了解thymeleaf有哪些标签,为什么jsp只能放在webapp/WEB-INF下  
答：为什么jsp只能放在webapp/WEB-INF下,这个没有找到相关的信息，只知道官方给的例子就是这样的目录结构,有webapp/WEB-INF目录打的是war包，没有打的是jar包


- <h5 style="color:red">1.0.3版本</h5>SpringBoot如何默认加载application.properties/application.yml文件的？如果这两个文件都存在并且存在相关属性,优先取谁的？
答:服务启动时ConfigFileApplicationListener.java会进行监听执行，主要是getSearchLocations()方法,获取文件夹路径(classpath:/,classpath:/config/,file:./,file:./config/")
循环获取文件夹路径下的默认文件，默认文件由getSearchNames()方法获取，默认以application为文件名的文件，文件类型由loader.getFileExtensions()获取,主要由PropertiesPropertySourceLoader和YamlPropertySourceLoader实现了PropertySourceLoader并重写了该方法，并且
从测试结果来说,优先取roperties中的，具体是如何选择的有待寻找源码

- <h5 style="color:red">1.0.5版本</h5>分页插件如何进行拦截工作的？
答：当执行DefaultSqlSession.java中selectList方法的executor.query(...)时【executor为Plugin.java中wrap方法生成的代理对象】。会通过反射调用Plugin.java的invoke方法，该方法中interceptor.intercept(new Invocation(target, method, args))【interceptor为PageInterceptor对象】,在intercept中做了分页处理。


## 参考文献

