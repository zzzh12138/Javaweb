package com.itheima.mapper;


import com.itheima.pojo.OrderManage;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderManageMapper {
    /**
     * 查询所有
     *
     */
    @Select("select * from order_manage")
    @ResultMap("orderManageResultMap")
    List<OrderManage> selectAll();
    /**
    * 加入订单
    *
    */
    @Insert("insert order_manage set produce_brand_order_name=#{produceBrandOrderName},company_order_name=#{companyOrderName}," +
            "ordered= #{ordered},price= #{price},starA=#{starA},descriptionA= #{descriptionA},status=#{status}")
    void add(OrderManage orderManage);
    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

}
