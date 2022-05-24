package com.itheima.service;

import com.itheima.pojo.OrderManage;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderManageService {
    PageBean<OrderManage> selectAll();
    void add(OrderManage orderManage);
    void deleteByIds(@Param("ids") int[] ids);

}
