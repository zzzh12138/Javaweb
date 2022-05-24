package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ProduceA;

import java.util.List;

public interface ProduceAService {
    List<ProduceA> selectAll();

    /**
     * 添加数据
     * @param produceA
     */
    void addA(ProduceA produceA);
    /**
     * 修改数据
     * @param produceA
     */
    void update(ProduceA produceA);
    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds( int[] ids);

    /**
     * 批量删除
     * @param produceA
     */
    void updateById(ProduceA produceA);

     Integer  returnCID(ProduceA produceA);
    /**
     * 分页查询
     * @param currentPage  当前页码
     * @param pageSize   每页展示条数
     * @return
     */
    PageBean<ProduceA> selectByPage(int currentPage, int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param produceA
     * @return
     */
    PageBean<ProduceA>  selectByPageAndCondition(int currentPage,int pageSize,ProduceA produceA);


    PageBean<ProduceA>  selectConsumerByPageAndCondition(int currentPage,int pageSize,ProduceA produceA);


}
