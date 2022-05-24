package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.OrderManage;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ProduceA;
import com.itheima.pojo.ProduceC;
import com.itheima.service.ProduceCService;
import com.itheima.service.impl.ProduceCServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/produceC/*")
public class ProduceCServlet extends BaseServlet{
    private ProduceCService produceCService = new ProduceCServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<ProduceC> produceCS = produceCService.selectAll();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(produceCS);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        ProduceC produceC = JSON.parseObject(params, ProduceC.class);

        //2. 调用service添加
        produceCService.add(produceC);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
    public void update(HttpServletRequest  request, HttpServletResponse response) throws ServletException, IOException{
        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        ProduceC produceC = JSON.parseObject(params, ProduceC.class);
        System.out.println(produceC);
        //2. 调用service添加
        produceCService.update(produceC);

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
        produceCService.deleteByIds(ids);

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
        PageBean<ProduceC> pageBean = produceCService.selectByPage(currentPage, pageSize);

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

    public void selectServiceByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 ProduceA
        ProduceC produceC = JSON.parseObject(params, ProduceC.class);


        //2. 调用service查询

        PageBean<ProduceC> pageBean = produceCService.selectServiceByPageAndCondition(currentPage,pageSize,produceC);

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

        ProduceC produceC =new ProduceC();
        produceC.setId(orderManage.getId());
        produceC.setStar(Integer.parseInt(orderManage.getStarC()));
        produceC.setEvaluation(orderManage.getDescriptionC());
        System.out.println(produceC);
        //2. 调用service添加

        produceCService.updateById(produceC);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
}
