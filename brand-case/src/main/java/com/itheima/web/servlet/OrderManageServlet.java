package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.OrderManage;

import com.itheima.service.OrderManageService;
import com.itheima.service.impl.OrderManageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderManage/*")
public class OrderManageServlet extends BaseServlet {
    private OrderManageService orderManageService = new OrderManageServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        PageBean<OrderManage> orderManages = orderManageService.selectAll();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(orderManages);

        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象

        OrderManage orderManage = JSON.parseObject(params, OrderManage.class);
//        System.out.println(orderManage);
//        //2. 调用service添加
        orderManageService.add(orderManage);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        //1. 接收数据  [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //2. 调用service添加
        orderManageService.deleteByIds(ids);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
}
