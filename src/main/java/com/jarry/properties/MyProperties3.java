package com.jarry.properties;

import com.jarry.domain.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * Created by jarry on 2018/6/25.
 * 在application.properties中添加自定定义的属性
 */

@ConfigurationProperties(prefix = "myprop")
public class MyProperties3 {

    private Map<String, User> myMap;

    private List<String> myList;

    private List<Map<String, String>> myListMap;

    private String[] myArray;

    public Map<String, User> getMyMap() {
        return myMap;
    }

    public void setMyMap(Map<String, User> myMap) {
        this.myMap = myMap;
    }

    public List<String> getMyList() {
        return myList;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public List<Map<String, String>> getMyListMap() {
        return myListMap;
    }

    public void setMyListMap(List<Map<String, String>> myListMap) {
        this.myListMap = myListMap;
    }

    public String[] getMyArray() {
        return myArray;
    }

    public void setMyArray(String[] myArray) {
        this.myArray = myArray;
    }
}
