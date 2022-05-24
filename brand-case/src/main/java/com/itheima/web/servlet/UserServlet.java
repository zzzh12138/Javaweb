package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user selectAll...");

    }


    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user add...");
    }

    public String user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Object user = session.getAttribute("user");
        //System.out.println(user);


        String jsonString = JSON.toJSONString(user);
        JSONObject jsonObject=JSON.parseObject(jsonString);
        response.setContentType("text/json;charset=utf-8");
        String t=jsonObject.getString("username");
        response.getWriter().write(t);
        return t;
    }
}
