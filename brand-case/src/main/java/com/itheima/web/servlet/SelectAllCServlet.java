package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.service.ProduceCService;
import com.itheima.service.impl.ProduceCServiceImpl;
import com.itheima.pojo.ProduceC;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllCServlet")
public class SelectAllCServlet extends HttpServlet {
    private ProduceCService produceCService =new ProduceCServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProduceC> produceCs= produceCService.selectAll();

        String jsonString = JSON.toJSONString(produceCs);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
