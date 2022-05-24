package com.itheima.web.servlet;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserServiceImpl service=new UserServiceImpl();
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       //1. 接收用户数据
//       String username = request.getParameter("username");
//       String password = request.getParameter("password");
//       String identity = request.getParameter("identity");
//       String checkCode = request.getParameter("checkCode");
//
//       HttpSession session = request.getSession();
//       System.out.println(session.getAttribute("checkCodeGen"));
//       if (checkCode.equals(session.getAttribute("checkCodeGen"))) {
//           if (username != null) {
//               //封装用户对象
//               User user = new User();
//               user.setUsername(username);
//               user.setPassword(password);
//               user.setIdentity(identity);
//               System.out.println(user);
//           }
//       }
//       else
//           System.out.println(2);
   }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//       this.doGet(request,response);

        //1. 接收用户数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");
        String checkCode = request.getParameter("checkCode");


        HttpSession session = request.getSession();
//        System.out.println(session.getAttribute("checkCodeGen"));
        if(checkCode.equals(session.getAttribute("checkCodeGen"))) {
            if (username != null) {
                //封装用户对象
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setIdentity(identity);
                System.out.println(user);


                boolean flag = service.register(user);
                if (flag) {
                    request.setAttribute("register_msg", "注册成功快登陆吧");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("register_msg", "用户名已经存在啦");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                }
            }
        }
        else{
            request.setAttribute("register_msg", "验证码错啦，再输一次");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}