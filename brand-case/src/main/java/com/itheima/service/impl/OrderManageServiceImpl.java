package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.OrderManageMapper;
import com.itheima.mapper.ProduceAMapper;
import com.itheima.pojo.OrderManage;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ProduceA;
import com.itheima.service.OrderManageService;
import com.itheima.util.SqlSessionFactoryUtils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrderManageServiceImpl implements OrderManageService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public PageBean<OrderManage> selectAll(){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        OrderManageMapper mapper = sqlSession.getMapper(OrderManageMapper.class);

        //4. 调用方法
        List<OrderManage> rows = mapper.selectAll();

        //4.1封装成PageBean对象
        PageBean<OrderManage> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        //5. 释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public void add(OrderManage orderManages){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        OrderManageMapper mapper = sqlSession.getMapper(OrderManageMapper.class);

        //4. 调用方法
        mapper.add(orderManages);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        OrderManageMapper mapper = sqlSession.getMapper(OrderManageMapper.class);

        //4. 调用方法
        mapper.deleteByIds(ids);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }
}
