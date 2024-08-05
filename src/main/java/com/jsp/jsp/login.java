package com.jsp.jsp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/loginForm")
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String myemail = req.getParameter("email1");
        String mypass = req.getParameter("pass1");

        // Database information
        String url = "jdbc:mysql://localhost:3306/JDBC";
        String user = "root";
        String password = "Pandey@0871";

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            con = DriverManager.getConnection(url, user, password);

            // Prepare and execute the query
            String q = "SELECT * FROM register WHERE myemail = ? AND mypass = ?";
            psmt = con.prepareStatement(q);
            psmt.setString(1, myemail);
            psmt.setString(2, mypass);
            rs = psmt.executeQuery();

            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("session_name",rs.getString("myname"));
                // Forward to profile.jsp
                RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
                rd.forward(req, resp);
            } else {
                out.print("<h3 style='color:red'>Email id and Password didn't match</h3>");
                // Forward to login.jsp
                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(req, resp);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.print("<h3 style='color:red'>An error occurred: " + e.getMessage() + "</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req, resp);
        } finally {
            // Close the resources
            try {
                if (rs != null) rs.close();
                if (psmt != null) psmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
