package com.itheima.service;
import com.itheima.pojo.User;
public interface UserService {

    public User login(String username,String password,String identity);

    public boolean register(User user);
}
