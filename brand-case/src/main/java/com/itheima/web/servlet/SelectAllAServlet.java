package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.ProduceA;
import com.itheima.service.ProduceAService;
import com.itheima.service.impl.ProduceAServiceImpl;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllAServlet")
public class SelectAllAServlet extends HttpServlet {
   private ProduceAService produceAService =new ProduceAServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProduceA> produceAs = produceAService.selectAll();

        String jsonString = JSON.toJSONString(produceAs);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
