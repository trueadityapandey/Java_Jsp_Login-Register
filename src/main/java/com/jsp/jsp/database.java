package com.jsp.jsp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/regForm")
public class database extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String myname =req.getParameter("name1");
        String myemail= req.getParameter("email1");
        String mypass = req.getParameter("pass1");
        String mygender = req.getParameter("gender1");
        String mycity = req.getParameter("city1");
        PrintWriter out = resp.getWriter();
        String url = "jdbc:mysql://localhost:3306/JDBC";
        String user = "root";
        String password = "Pandey@0871";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            String q = "insert into register values(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1,myname);
            pstmt.setString(2,myemail);
            pstmt.setString(3,mypass);
            pstmt.setString(4,mygender);
            pstmt.setString(5,mycity);
            int count = pstmt.executeUpdate();
            if(count>0){
                resp.setContentType("text/html");

                out.print("<h3 style = 'color:green'>User Registered Successfully</h3>");

                RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
                rd.include(req,resp);
            }
            else{
                resp.setContentType("text/html");

                out.print("<h3 style = 'color:red'>User not registered due to some error</h3>");

                RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
                rd.include(req,resp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            out.print("<h3 style = 'color:red'>Exception occurred : "+e.getMessage()+"</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.include(req,resp);
        }
        
    }
}
