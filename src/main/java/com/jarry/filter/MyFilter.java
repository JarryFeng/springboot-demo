package com.jarry.filter;

import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by jarry on 2018/5/29.
 *
 * 自定义过滤器(只能过滤掉HttpServletRequest中的参数,如果过滤注解的参数 如：@RequestParam)
 * 过滤一些敏感字符
 *
 */
public class MyFilter implements Filter {

    private static final Set<String> set = new HashSet();

    static {
        set.add("草");
        set.add("艹");
        set.add("cao");
        set.add("([尼你][玛妈])");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        MyHttpServletRequest myRequest = new MyHttpServletRequest((HttpServletRequest) servletRequest);

        filterChain.doFilter(myRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * 自定义请求对象，增强原有请求对象的一些属性等
     */
    class MyHttpServletRequest extends HttpServletRequestWrapper {

        public MyHttpServletRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String name) {
            String oldString = super.getParameter(name);
            if(!StringUtils.isEmpty(oldString)){
                for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
                    oldString = oldString.replaceAll(it.next(),"*");
                }

            }

            return oldString;
        }
    }

    public static void main(String[] args) {
        String oldString  = "我要测试尼玛、尼玛、你妈、你玛、草、艹、cao会不会被替换掉";
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            oldString = oldString.replaceAll(it.next(),"**");
        }
        System.out.println(oldString);
    }
}
