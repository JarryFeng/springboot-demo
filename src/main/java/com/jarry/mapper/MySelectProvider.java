package com.jarry.mapper;

/**
 * Created by jarry on 2018/6/14.
 * 动态SQL拼装类
 */
public class MySelectProvider {

    public String packageSelectSql(Integer id){
        return "select * from tbl_user where id =" + id;
    }
}
