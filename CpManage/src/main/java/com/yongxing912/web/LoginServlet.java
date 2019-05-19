package com.yongxing912.web;


import com.yongxing912.Dao.UserDao;
import com.yongxing912.model.User;
import com.yongxing912.util.DbUtil;
import com.yongxing912.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author yongxing912
 * @site www.giantest.cn
 * @company GianTest
 * @create 2019-05-10 13:51
 */
public class LoginServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    UserDao userDao=new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        request.setAttribute("userName",userName);
        request.setAttribute("password",password);

        if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
            request.setAttribute("error","用户名和密码不能为空");
            request.getRequestDispatcher("index.jsp").forward(request,response);
            return;
        }
        User user=new User(userName,password);
        Connection con=null;
        try {
            con=dbUtil.getCon();
            User currentUser=userDao.login(con,user);
            if(currentUser==null){
                //服务器跳转
                request.setAttribute("error","用户名或密码错误");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }else{
                // 获取Session
                HttpSession session=request.getSession();
                session.setAttribute("currentUser", currentUser);
                // 客户端跳转
                response.sendRedirect("main.jsp");

//                //获取session
//                HttpSession session=request.getSession();
//                session.setAttribute("currentUser",currentUser);
//                //客户端跳转
//                response.sendRedirect("main.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
