package com.itheima.service.impl;


import com.itheima.service.UserService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.itheima.pojo.User;
import com.itheima.mapper.UserMapper;
public class UserServiceImpl implements UserService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public User login(String username, String password, String identity) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4. 调用方法
        User users = mapper.select(username, password, identity);
        //5. 释放资源
        sqlSession.close();

        return users;
    }

    @Override
    public boolean register(User user) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User u = mapper.selectByUsername(user.getUsername());
        //u=true为之前尚未注册，注册成功，u=flase为注册失败
        if (u == null) {
            mapper.add(user);
            sqlSession.commit();
            sqlSession.close();
        } else {
            sqlSession.close();
        }
        return u==null? true:false;
    }
}
