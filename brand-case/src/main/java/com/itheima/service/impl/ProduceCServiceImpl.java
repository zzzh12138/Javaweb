package com.itheima.service.impl;

import com.itheima.mapper.ProduceAMapper;
import com.itheima.mapper.ProduceCMapper;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ProduceA;
import com.itheima.pojo.ProduceC;
import com.itheima.service.ProduceCService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ProduceCServiceImpl implements ProduceCService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<ProduceC> selectAll(){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceCMapper mapper = sqlSession.getMapper(ProduceCMapper.class);

        //4. 调用方法
        List<ProduceC> produceCs = mapper.selectAll();

        //5. 释放资源
        sqlSession.close();

        return  produceCs;
    }

    /**
     * 添加数据
     * @param produceC
     */
    @Override
    public void add(ProduceC produceC){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceCMapper mapper = sqlSession.getMapper(ProduceCMapper.class);
//        System.out.println(produceC);
        //4. 调用方法
        mapper.add(produceC);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }
    @Override
    public void update(ProduceC produceC){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceCMapper mapper = sqlSession.getMapper(ProduceCMapper.class);
        System.out.println(produceC);
        //4. 调用方法
        mapper.update(produceC);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceCMapper mapper = sqlSession.getMapper(ProduceCMapper.class);


        //4. 调用方法
        mapper.deleteByIds(ids);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }
    @Override
    public void updateById(ProduceC produceC){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceCMapper mapper = sqlSession.getMapper(ProduceCMapper.class);

        //4. 调用方法

        mapper.updateById(produceC);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<ProduceC> selectByPage(int currentPage, int pageSize) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceCMapper mapper = sqlSession.getMapper(ProduceCMapper.class);


        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        //5. 查询当前页数据
        List<ProduceC> rows = mapper.selectCByPage(begin, size);

        //6. 查询总记录数
        int totalCount = mapper.selectTotalCount();

        //7. 封装PageBean对象
        PageBean<ProduceC> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //8. 释放资源
        sqlSession.close();

        return pageBean;

    }

    @Override
    public PageBean<ProduceC> selectServiceByPageAndCondition(int currentPage, int pageSize, ProduceC produceC) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ProduceCMapper mapper = sqlSession.getMapper(ProduceCMapper.class);


        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理brand条件，模糊表达式
        String produceBrandCNameName = produceC.getProduceBrandCName();
        if (produceBrandCNameName != null && produceBrandCNameName.length() > 0) {
            produceC.setProduceBrandCName("%" + produceBrandCNameName + "%");
        }

        String companyCName = produceC.getCompanyCName();
        if (companyCName != null && companyCName.length() > 0) {
            produceC.setCompanyCName("%" + companyCName + "%");
        }

        //5. 查询当前页数据

        List<ProduceC> rows = mapper.selectServiceByPageAndCondition(begin, size, produceC);

        //6. 查询总记录数
        int totalCount = mapper.selectServiceTotalCountByCondition(produceC);

        //7. 封装PageBean对象
        PageBean<ProduceC> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }

}
