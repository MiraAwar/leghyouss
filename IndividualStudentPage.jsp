<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Individual Student Data Access and Modification</title>
</head>
<body>

<h2>Enter Student Information</h2>
    <ul>
    <form action="<%=request.getContextPath()%>/Student"  method="post"> 
    <label for="Fname">First Name:</label>
    <input type ="text" name="Fname"  size="30" />
    <br><br>
    <label for="Lname">Last Name:</label>
    <input type ="text" name="Lname"  size="30" />
    <br><br>
    <label for="ID">ID:</label>
    <input type ="text" name="ID"  size="30" />
    <br><br>
    
	<INPUT TYPE="radio" NAME="command" VALUE="1">Edit Login Time
    <input type ="text" name="LI" size="30" />
    <br><br>
	<INPUT TYPE="radio" NAME="command" VALUE="2">Edit Logout Time
    <input type ="text" name="LO"  size="30" />
    <br><br>
	<INPUT TYPE="radio" NAME="command" VALUE="3">Check If Attended
	<br><br>
	<INPUT TYPE="radio" NAME="command" VALUE="4">Set Attendance Mark
    <input type ="text" name="AM"  size="30" />
    <br><br>
	<INPUT TYPE="radio" NAME="command" VALUE="5">Get Login Time
	<br><br> 
	<INPUT TYPE="radio" NAME="command" VALUE="6">Get Logout Time
	<br><br> 
	<INPUT TYPE="radio" NAME="command" VALUE="7">Get Total Time Attended
	<br><br> 
	<INPUT TYPE="radio" NAME="command" VALUE="8">Get Attendance Mark
	<br><br> 
    <input type="submit" name="Submit">
    <br><br>    

</body>
</html>