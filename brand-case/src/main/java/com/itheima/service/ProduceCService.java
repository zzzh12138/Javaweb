package com.itheima.service;

import com.itheima.pojo.PageBean;
import com.itheima.pojo.ProduceA;
import com.itheima.pojo.ProduceC;

import java.util.List;

public interface ProduceCService {

    List<ProduceC> selectAll();

    /**
     * 添加数据
     * @param produceC
     */
    void add(ProduceC produceC);
    /**
     * 修改数据
     * @param produceC
     */
    void update(ProduceC produceC);
    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds( int[] ids);

    /**
     * 分页查询
     * @param currentPage  当前页码
     * @param pageSize   每页展示条数
     * @return
     */
    PageBean<ProduceC> selectByPage(int currentPage, int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param produceC
     * @return
     */
    PageBean<ProduceC>  selectServiceByPageAndCondition(int currentPage,int pageSize,ProduceC produceC);

    void updateById(ProduceC produceC);
}
