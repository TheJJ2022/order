package com.javaServlet.classes;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class loginServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    //验证登陆
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取登陆界面输入的账户名称和密码
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        if(account==null||account.length()==0){
            response.sendRedirect("homepage.jsp");
            return;
        }
        if(password==null||password.length()==0){
            response.sendRedirect("homepage.jsp");
            return;
        }
        try {
            //先获取数据池连接对象
            Context context=new InitialContext();
            Context contextNeeded=(Context) context.lookup("java:comp/env");
            DataSource dataSource=(DataSource) contextNeeded.lookup("dataPool");
            Connection connection=dataSource.getConnection();//获得对象
            //验证登陆
            //查询所有用户
            String sql="select * from tb_user";
            //获取preparedStatement对象
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            //执行sql语句
            ResultSet resultSet=preparedStatement.executeQuery();
            //处理结果
            while (resultSet.next()){
                String accountRight=resultSet.getString("user_account");
                String passwordRight=resultSet.getString("user_password");
                if(Objects.equals(account, accountRight) && Objects.equals(password, passwordRight)){
                    //验证成功
                    //根据用户权限转向三个页面：用户权限 0为管理员，1为普通用户，2为商家
                    int statusRight=resultSet.getInt("user_status");
                    if(statusRight==0){
                        //转向管理员界面
                    }else if(statusRight==1){
                        //转向普通用户界面
                        response.sendRedirect("clientHomepage.jsp");
                        return;
                    }else if(statusRight==2){
                        //转向商家用户界面
                    }
                }
            }
            //如果登陆失败
            response.sendRedirect("homepage.jsp");
            //释放资源
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("homepage.jsp");
        }
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
