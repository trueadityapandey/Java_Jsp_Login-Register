<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h3>Welcome to Jsp Tutorial</h3>
<%--    <%--%>
<%--        request.setAttribute("request_name","Aditya Pandey");--%>

<%--    %>--%>
<%--    <h3>Hello : ${requestScope.request_name}</h3>--%>
<%--    <%--%>
<%--        session.setAttribute("session_cname","smartPrograming");--%>
<%--//        out.print(session.getAttribute ("session_cname"));--%>
<%--    %>--%>
<%--    <h3>${--%>
<%--        session_cname--%>
<%--    }</h3>--%>
    <form action="regForm" method="post">
       Name: <input type="text" name="name1" placeholder="Enter Name"/> <br/><br/>
       Email: <input type="text" name="email1" placeholder="Enter email" /><br/><br/>
       Password: <input type="password" name="pass1" placeholder="Enter Password"/><br/><br/>
        <input type="radio" name="gender1" value="Male">Male <input type="radio" name="gender1" value="Female"/>female<br/><br/>
        <select name="city1">
            <option>None</option>
            <option>New Delhi</option>
            <option>Bangalore</option>
            <option>Mumbai</option>
            <option>Gujarat</option>
        </select><br/><br/>
        <input type="submit" value="click me"/>
    </form>
    <h5>Already a user?</h5><a href="login.jsp">Login</a>

</body>
</html>