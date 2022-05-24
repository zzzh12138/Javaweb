package com.itheima.web.fiter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//登陆验证的过滤器
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String[] urls={"/login.jsp","/register.jsp","/css/","/imgs/","/loginServlet","/registerServlet","/checkCodeServlet",};
        HttpServletRequest request =(HttpServletRequest) req;

        String url=request.getRequestURL().toString();
        for(String u:urls){
            if(url.contains(u)){//无条件放行
                //放行
                chain.doFilter(req, resp);
                return;
            }
        }

        //判断session中是否有user
        HttpSession session=request.getSession();
        Object user=session.getAttribute("user");

        String jsonString = JSON.toJSONString(user);
        JSONObject jsonObject=JSON.parseObject(jsonString);
        int count=0;
        String[] urlA={"/css/","/produceA/","/brand.html","/user/","/js/","/element-ui/"};
        String[] urlB={"/css/","/produceA/","/produceC/","/consumer.html","/orderManage.html","/user/","/js/","/element-ui/","/orderManage/"};
        String[] urlC={"/css/","/produceC/","/service.html","/user/","/js/","/element-ui/"};

        if(user!=null){//有用户登录了


            //放行
            if(jsonObject.getString("identity").equals("A")){
                for(String u:urlA)
                if(url.contains(u))
                {count++;
                    chain.doFilter(req, resp);
                }
                if(count==0)
                {
                req.setAttribute("login_msg","您无权访问");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
                }

            }
            if(jsonObject.getString("identity").equals("B")){
                for(String u:urlB)
                    if(url.contains(u))
                    {count++;
                        chain.doFilter(req, resp);
                    }
                if(count==0)
                {
                    req.setAttribute("login_msg","您无权访问");
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                }
            }

            if(jsonObject.getString("identity").equals("C")){
                for(String u:urlC)
                    if(url.contains(u))
                    {count++;
                        chain.doFilter(req, resp);
                    }
                if(count==0)
                {
                    req.setAttribute("login_msg","您无权访问");
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                }
            }
        }else{

            req.setAttribute("login_msg","您无权访问");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);

        }


    }
    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
