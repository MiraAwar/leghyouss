<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Registration Page</title>
  </head>
  <body>
  	<h2>Enter Registration Information:</h2>
  	<ul>
  		<form action="<%=request.getContextPath()%>/Register"  method="post"> 
  			<label for="username">Username:</label>
  			<input type ="text" name="username" size="30" />
  			<br><br>
  			<label for="password">Password: </label>
  			<input type ="password" name="password"  size="30" />
  			<br><br>
  			<label for="re-enter password">Re-enter Password: </label>
  			<input type ="password" name="password2"  size="30" />
  			<br><br>
  			<input type="submit" name="Submit">
  			<br><br>
  		</form>
  	</ul>
  </body>
</html>