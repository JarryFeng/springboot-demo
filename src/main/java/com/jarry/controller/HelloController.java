package com.jarry.controller;

import com.jarry.properties.MyProperties;
import com.jarry.properties.MyProperties2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jarry on 2018/5/29.
 */
@Controller
public class HelloController {

    @Autowired
    private MyProperties myProperties;

    @Autowired
    private MyProperties2 myProperties2;

    @GetMapping("/hello")
    public String hello(HttpServletRequest httpServletRequest, @RequestParam String name){
        System.out.println(name);
        System.out.println(httpServletRequest.getParameter("name"));
        httpServletRequest.setAttribute("hello", name);

        System.out.println(myProperties.getName());

        System.out.println(myProperties2.getUsername());

        System.out.println(myProperties2.getAge());

        return "index";
    }


    @GetMapping("/testhello")
    @ResponseBody
    public String testhello(HttpServletRequest httpServletRequest, @RequestParam String name){
        System.out.println(name);
        System.out.println(httpServletRequest.getParameter("name"));
        httpServletRequest.setAttribute("hello", name);

        return "hello";
    }
}
