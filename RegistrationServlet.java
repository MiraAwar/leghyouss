package Project351;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.*;

@WebServlet("/Register")
public class RegistrationServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pword = request.getParameter("password");
        String password2=request.getParameter("password2");
        if (pword.equals(password2)) {
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
			    Statement preparedStatement = connection.createStatement();
			    preparedStatement.executeUpdate("INSERT INTO professors (username, password) values('" + uname+"', '"+pword+"')");
			    PrintWriter out = response.getWriter();
	        	out.println("<html><body><p>Professor has been added to the database.</p>"
	        			+ "<a href='Login.jsp'>Click here to go back to Login Page</a>"
	        			+ "</body></html>");
			    connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
        else{
        	PrintWriter out = response.getWriter();
        	out.println("<html><body><h1> Passwords do not match! Please re-enter in the link below: </h1></body></html>");
        	out.println("<html><body><a href=\"Registration.jsp\"> Click here </a></body></html>");
        }
        
    }
}
