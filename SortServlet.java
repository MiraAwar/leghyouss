package Project351;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/StudentPage")
public class SortServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String query = (String)request.getParameter("Options");

        if (query.equals("FirstName")) {
		    response.sendRedirect("SortedByFname.jsp");
		}
		else if (query.equals("LastName")) {
			response.sendRedirect("SortedByLname.jsp");
		}
		else if(query.equals("LoginTime")) {
			 response.sendRedirect("SortedByLoginTime.jsp");
		}
		else if(query.equals("LogoutTime")) {
			 response.sendRedirect("SortedByLogoutTime.jsp");
		}
		else if(query.equals("TotalTimeAttended")) {
			 response.sendRedirect("SortedByTotalTimeAttended.jsp");
		}
		else if(query.equals("ID")){
			 response.sendRedirect("SortedByID.jsp");
		}
		else if(query.equals("AttendanceMark")){
			 response.sendRedirect("SortedByAttendanceMark.jsp");
		}
    }
}