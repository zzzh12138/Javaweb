package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.ProduceAMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ProduceA;

import com.itheima.service.ProduceAService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ProduceAServiceImpl implements ProduceAService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<ProduceA> selectAll(){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceAMapper mapper = sqlSession.getMapper(ProduceAMapper.class);


        //4. 调用方法
        List<ProduceA> produceAs = mapper.selectAll();

        //5. 释放资源
        sqlSession.close();

        return produceAs;
    }
    @Override
    public void addA(ProduceA produceA){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceAMapper mapper = sqlSession.getMapper(ProduceAMapper.class);
        System.out.println(produceA);
        //4. 调用方法
        mapper.addA(produceA);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }
    @Override
    public void update(ProduceA produceA){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceAMapper mapper = sqlSession.getMapper(ProduceAMapper.class);
        System.out.println(produceA);
        //4. 调用方法
        mapper.update(produceA);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }
    @Override
    public void deleteByIds(int[] ids) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceAMapper mapper = sqlSession.getMapper(ProduceAMapper.class);

        //4. 调用方法

        mapper.deleteByIds(ids);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<ProduceA> selectByPage(int currentPage, int pageSize) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceAMapper mapper = sqlSession.getMapper(ProduceAMapper.class);


        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        //5. 查询当前页数据
        List<ProduceA> rows = mapper.selectAByPage(begin, size);
        //6. 查询总记录数
        int totalCount = mapper.selectTotalCount();

        //7. 封装PageBean对象
        PageBean<ProduceA> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<ProduceA> selectByPageAndCondition(int currentPage, int pageSize, ProduceA produceA) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceAMapper mapper = sqlSession.getMapper(ProduceAMapper.class);


        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理brand条件，模糊表达式
        String produceBrandANameName = produceA.getProduceBrandAName();
        if (produceBrandANameName != null && produceBrandANameName.length() > 0) {
            produceA.setProduceBrandAName("%" + produceBrandANameName + "%");
        }

        String companyAName = produceA.getCompanyAName();
        if (companyAName != null && companyAName.length() > 0) {
            produceA.setCompanyAName("%" + companyAName + "%");
        }

        //5. 查询当前页数据
        System.out.println(produceA);
        List<ProduceA> rows = mapper.selectByPageAndCondition(begin,size, produceA);
        //6. 查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(produceA);
        //7. 封装PageBean对象
        PageBean<ProduceA> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<ProduceA> selectConsumerByPageAndCondition(int currentPage, int pageSize, ProduceA produceA) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceAMapper mapper = sqlSession.getMapper(ProduceAMapper.class);


        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理brand条件，模糊表达式
        String produceBrandANameName = produceA.getProduceBrandAName();
        if (produceBrandANameName != null && produceBrandANameName.length() > 0) {
            produceA.setProduceBrandAName("%" + produceBrandANameName + "%");
        }

        String companyAName = produceA.getCompanyAName();
        if (companyAName != null && companyAName.length() > 0) {
            produceA.setCompanyAName("%" + companyAName + "%");
        }

        //5. 查询当前页数据

        List<ProduceA> rows = mapper.selectConsumerByPageAndCondition(begin,size, produceA);

        //6. 查询总记录数
        int totalCount = mapper.selectConsumerTotalCountByCondition(produceA);

        //7. 封装PageBean对象
        PageBean<ProduceA> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public void updateById(ProduceA produceA){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceAMapper mapper = sqlSession.getMapper(ProduceAMapper.class);

        //4. 调用方法

        mapper.updateById(produceA);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    public Integer returnCID(ProduceA produceA){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceAMapper mapper = sqlSession.getMapper(ProduceAMapper.class);
        //4. 调用方法

        Integer id=mapper.returnCID(produceA);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
        return id;
    }

    }

