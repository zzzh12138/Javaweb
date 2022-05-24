package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.itheima.pojo.OrderManage;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ProduceA;
import com.itheima.service.ProduceAService;

import com.itheima.service.impl.ProduceAServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/produceA/*")
public class ProduceAServlet extends BaseServlet{
    private ProduceAService produceAService = new ProduceAServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<ProduceA> produceAS = produceAService.selectAll();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(produceAS);

        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        ProduceA produceA = JSON.parseObject(params, ProduceA.class);
        System.out.println(produceA);
        //2. 调用service添加
        produceAService.addA(produceA);
        //3. 响应成功的标识
        response.getWriter().write("success");
    }
    public void update(HttpServletRequest  request, HttpServletResponse response) throws ServletException, IOException{
        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        ProduceA produceA = JSON.parseObject(params, ProduceA.class);
        System.out.println(produceA);
        //2. 调用service添加
        produceAService.update(produceA);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
    public void deleteByIds(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        //1. 接收数据  [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
//        String params="[1,2]";
//        String param=params.substring(1,params.length()-1);
//        System.out.println(param);
//        String[] ids=param.split("\\,");
//        System.out.println(ids[0]);

        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //2. 调用service添加

        if(ids.length!=0)
        produceAService.deleteByIds(ids);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2. 调用service查询
        PageBean<ProduceA> pageBean = produceAService.selectByPage(currentPage, pageSize);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 ProduceA
        ProduceA produceA = JSON.parseObject(params, ProduceA.class);

        //2. 调用service查询
        PageBean<ProduceA> pageBean = produceAService.selectByPageAndCondition(currentPage,pageSize,produceA);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectConsumerByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 ProduceA
        ProduceA produceA = JSON.parseObject(params, ProduceA.class);

        //2. 调用service查询

        PageBean<ProduceA> pageBean = produceAService.selectConsumerByPageAndCondition(currentPage,pageSize,produceA);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    public void updateById(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        //1. 接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串


        //转为 orderManage
        OrderManage orderManage = JSON.parseObject(params, OrderManage.class);

        ProduceA produceA =new ProduceA();
        produceA.setId(Integer.parseInt(orderManage.getOrdered()));
        produceA.setStar(Integer.parseInt(orderManage.getStarA()));
        produceA.setEvaluation(orderManage.getDescriptionA());
        System.out.println(produceA);
        //2. 调用service添加

        produceAService.updateById(produceA);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
    public void returnCID(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 orderManage
        OrderManage orderManage = JSON.parseObject(params, OrderManage.class);

        ProduceA produceA =new ProduceA();
        produceA.setId(Integer.parseInt(orderManage.getOrdered()));


        Integer id=produceAService.returnCID(produceA);


        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(id+"");

    }
}
