package com.itheima.web.servlet;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl service=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");
//        System.out.println(username+password+identity);
        //2. 调用MyBatis完成查询
        //2.1 获取SqlSessionFactory对象
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        User user =service.login(username,password,identity);


        //获取字符输出流，并设置content type
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        //3. 判断user释放为null
        if(user != null){
            // 登陆成功
            writer.write("登陆成功");

            //登录成功后的user对象存储到Session
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            //System.out.println(session.getAttribute("user"));
            //System.out.println("3");
            if(identity.equals("A")){

                String contextPath=request.getContextPath();//动态虚拟目录
                response.sendRedirect(contextPath+"/brand.html");
            }
            if(identity.equals("B")){

                String contextPath=request.getContextPath();//动态虚拟目录
                response.sendRedirect(contextPath+"/consumer.html");
            }
            if(identity.equals("C")){

                String contextPath=request.getContextPath();//动态虚拟目录
                response.sendRedirect(contextPath+"/service.html");
            }
        }else {
            // 登陆失败
            writer.write("登陆失败");
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);//请求转发
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}