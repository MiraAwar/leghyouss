<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Login</title>
        <meta charset=UTF-8">
        <meta name="viewport" content ="width=device-width, initial-scale=1.0">
        <style>
        li {listt-style: none;}
        </style>
    </head>
    <body>
    <h2>Enter Login Information</h2>
    <ul>
    <form action="<%=request.getContextPath()%>/Login"  method="post"> 
    <label for="username">Username:</label>
    <input type ="text" name="username" placeholder="username" size="30" />
    <br><br>
    <label for="password">Password: </label>
    <input type ="password" name="password" placeholder="password" size="30" />
    <br><br>
    <input type="submit" name="Submit">
    <br><br>
    <a href="Registration.jsp"> Add new professor</a>
    </form>
    </ul>
    </body>
</html>
