package Project351;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")
@WebServlet("/Student")
public class StudentServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  UpdateStudentData usd;

  public void init() {
    usd = new UpdateStudentData();
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {

    String id = request.getParameter("ID");
    String f = request.getParameter("Fname");
    String l = request.getParameter("Lname");
    StudentAccessBean sab = new StudentAccessBean();
    sab.setID(Integer.parseInt(id));
    sab.setFname(f);
    sab.setLname(l);
    
    try {
		if(UpdateStudentData.FindStudent(sab)) {
			int sCommand=Integer.parseInt(request.getParameter("command"));
			if(sCommand == 1) {
			  try {
			    UpdateStudentData.EditLoginTime(sab, request.getParameter("LI"));
			  } catch (ClassNotFoundException e) {
			    e.printStackTrace();
			  }
			  PrintWriter out = response.getWriter();
			  out.println("<html><h2>Login Time Successfully Edited.</h2></html>");
			}
			else if(sCommand == 2) {
			  try {
			    UpdateStudentData.EditLogoutTime(sab, request.getParameter("LO"));
			  } catch (ClassNotFoundException e) {
			    e.printStackTrace();
			  }
			  PrintWriter out = response.getWriter();
			  out.println("<html><h2>Logout Time Successfully Edited.</h2></html>");
			}
			else if(sCommand == 3) {
			  try {
			    PrintWriter out = response.getWriter();
			    if(UpdateStudentData.CheckIfAttended(sab)) {
			      out.println("<html><h2>" + f + " " + l + " has attended.</h2></html>");
			    }
			    else {
			      out.println("<html><h2>" + f + " " + l + " has not attended.</h2></html>");
			    }
			  } catch (ClassNotFoundException e) {
			    e.printStackTrace();
			  }
	
			}
			else if(sCommand == 4) {
			  try {
			    UpdateStudentData.SetAttendanceMark(sab,request.getParameter("AM") );
			    PrintWriter out=response.getWriter();
			    out.println("<html><h2>Attendance mark has been set.</h2></html>");
			  } catch (ClassNotFoundException e) {
			    e.printStackTrace();
			  }
			  
			}
			else if (sCommand == 5) {
			  try {
			    String LoginTime= UpdateStudentData.GetLoginTime(sab);
			    PrintWriter out = response.getWriter();
			    out.println("<html><h2>" + f + " " + l +  " logged in at "+ LoginTime +".</h2></html>");
			  } catch (ClassNotFoundException e) {
			    e.printStackTrace();
			  }
			  
			}
			else if (sCommand == 6) {
			  try {
			    String LogoutTime=UpdateStudentData.GetLogoutTime(sab);
			    PrintWriter out = response.getWriter();
			    out.println("<html><h2>" + f + " " + l + " logged out at "+LogoutTime+".</h2></html>");
			  } catch (ClassNotFoundException e) {
			    e.printStackTrace();
			  }
			}
			else if (sCommand == 7) {
			  try {
			    String TotalTime = UpdateStudentData.GetTotalTimeAttended(sab);
			    PrintWriter out = response.getWriter();
			    out.println("<html><h2>Total time attended by " + f + " " + l + " is "+ TotalTime.charAt(0) + " hour and " + TotalTime.substring(2) + " minutes.</h2></html>");
			  } catch (ClassNotFoundException e) {
			    e.printStackTrace();
			  }
			}
			else if (sCommand == 8) {
			    try {
			      int AttendanceMark = UpdateStudentData.GetAttendanceMark(sab);
			      PrintWriter out = response.getWriter();
			      out.println("<html><h2>" + f + " " + l + "'s attendance mark is " + AttendanceMark + ".</h2></html>");
			    } catch (ClassNotFoundException e) {
			      e.printStackTrace();
			    }
			  }
			}
		else {
			PrintWriter out=response.getWriter();
		    out.println("<html><h2>Entered name does not correspond to entered ID.</h2></html>");
		}
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}