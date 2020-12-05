<%@page import="java.sql.*"%>
<head>
<title>Access Student Information</title>
<meta content="text/html; charset=utf-8" />
</head>
<body>
<h2>Access Student Information</h2>

<h2 align="center"><font><strong> Student Data (Sorted by Last Name)</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>
   <th>First Name</th>
   <th>Last Name</th>
   <th>ID</th>
   <th>Login Time</th>
   <th>Logout Time</th>
   <th>Total Time Attended</th>
   <th>Attendance Mark</th>
</tr>

<%
try{ 
Class.forName("com.mysql.jdbc.Driver");
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
Statement statement=connection.createStatement();
String sql ="SELECT * FROM students ORDER BY Lname";

ResultSet resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>

<tr>

<td><%=resultSet.getString("Fname") %></td>
<td><%=resultSet.getString("Lname") %></td>
<td><%=resultSet.getInt("ID") %></td>
<td><%=resultSet.getString("LoginTime") %></td>
<td><%=resultSet.getString("LogoutTime") %></td>
<td><%=resultSet.getString("TotalTimeAttended") %></td>
<td><%=resultSet.getString("AttendanceMark") %></td>


</tr>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>

<ul>
<a href = "IndividualStudentPage.jsp"> Access Individual Student Data</a>
<br><br>
<a href = "AddStudent.jsp"> Create New Student Record</a>
<br><br>
<form action="<%=request.getContextPath()%>/StudentPage" method="post">
                        <strong>Sort by:</strong>
                        <select name="Options">
                            <option>FirstName</option>
                            <option selected>LastName</option>
                            <option>LoginTime</option>
                            <option>LogoutTime</option>
                            <option>TotalTimeAttended</option>
                            <option>ID</option>
                            <option>AttendanceMark</option>
                            <input type="submit" value="GO"/>
                    </form>

</ul>
</body>
</html>