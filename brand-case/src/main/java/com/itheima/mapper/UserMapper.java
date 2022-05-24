package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    /**
     * 根据用户名和密码查询用户对象
     * @param username
     * @param password
     * @param identity
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password} and identity=#{identity}")
    User select(@Param("username") String username,@Param("password")  String password,@Param("identity") String identity);

    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into user (username,password,identity) values (#{username},#{password},#{identity})")
    void add(User user);
}
