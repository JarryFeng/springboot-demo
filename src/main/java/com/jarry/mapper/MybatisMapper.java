package com.jarry.mapper;

import com.jarry.domain.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by jarry on 2018/6/14.
 */
@Mapper
public interface MybatisMapper {

    //@Select("select * from tbl_user where id =#{id}")
    //动态SQL方式二
    @SelectProvider(type= MySelectProvider.class, method="packageSelectSql")
    public User selectOne(Integer id);

    @Delete("delete from tbl_user where id =#{id}")
    public void delById(Integer id);

    @Insert("insert into tbl_user(age, name, password) values(#{age}, #{name}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    //配置@Options(useGeneratedKeys=true, keyProperty="对象.属性") 这个的作用是设置是否使用JDBC的getGenereatedKeys()方法获取主键并赋值到keyProperty设置的对象的属性中
    public void addUser(User user);

    //动态SQL方式一
    @Update("<script>" +
            "update tbl_user set age=#{age},name=#{name}, password=#{password} " +
            "<where>" +
            "<if test='id!=null'> id = #{id}</if> " +
            "</where>" +
            "</script>")
    public int update(User user);
}
