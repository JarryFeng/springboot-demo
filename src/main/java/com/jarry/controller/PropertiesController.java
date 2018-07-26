package com.jarry.controller;

import com.jarry.domain.User;
import com.jarry.properties.MyProperties3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jarry on 2018/5/29.
 */
@Controller
public class PropertiesController {

    @Autowired
    private MyProperties3 myProperties3;


    @GetMapping("/prop")
    public String prop(){
        System.out.println("=============数组=============");
        String[] myArray = myProperties3.getMyArray();
        for(String str : myArray){
            System.out.println(str);
        }

        System.out.println("=============集合=============");

        List myList = myProperties3.getMyList();
        for (int i = 0; i< myList.size(); i++){
            System.out.println(myList.get(i));
        }

        System.out.println("=============map=============");
        Map<String, User> myMap = myProperties3.getMyMap();
        Set<Map.Entry<String, User>> entries = myMap.entrySet();
        for(Map.Entry<String, User> entry: entries){
            System.out.println("获取map的key值：" +entry.getKey());
            System.out.println("获取user对象的id属性值: " + entry.getValue().getId());
            System.out.println("获取user对象的age属性值: " + entry.getValue().getAge());
            System.out.println("获取user对象的name属性值: " + entry.getValue().getName());
            System.out.println("获取user对象的password属性值: " + entry.getValue().getPassword());
        }

        return "index";
    }
}
