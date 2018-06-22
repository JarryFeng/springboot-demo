package com.jarry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jarry on 2018/5/29.
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest httpServletRequest, @RequestParam String name){
        System.out.println(name);
        System.out.println(httpServletRequest.getParameter("name"));
        httpServletRequest.setAttribute("hello", name);
        return "index";
    }
}
