package Project351;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UpdateStudentData {
	
	public static boolean FindStudent(StudentAccessBean sab) throws ClassNotFoundException {
	    Class.forName("com.mysql.jdbc.Driver");

	    Connection connection;
	    try {
	      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
	      String query = new String("SELECT TotalTimeAttended from students WHERE ID = ? AND Fname = ? AND Lname = ?");
	      PreparedStatement preparedStatement = connection.prepareStatement(query);
	      preparedStatement.setInt(1, sab.getID());
	      preparedStatement.setString(2, sab.getFname());
	      preparedStatement.setString(3, sab.getLname());
	      ResultSet rs = preparedStatement.executeQuery();
	      if(rs.next())
	    	  return true;	
	      connection.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	     return false;
	  }
  
  public static void AddNewRecord(StudentAccessBean sab) throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");

    Connection connection;
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
      PreparedStatement preparedStatement = connection.prepareStatement("insert into students "
          + "(ID, Fname, Lname, Login Time, Logout Time, Total Time Attended) "
          + "VALUES (?, ?, ?, ?, ?, ?)");
      preparedStatement.setInt(1, sab.getID());
      preparedStatement.setString(2, sab.getFname());
      preparedStatement.setString(3, sab.getLname());
      preparedStatement.setString(4, sab.getLI());
      preparedStatement.setString(5, sab.getLO());
      preparedStatement.setString(6, sab.getTTA());
      preparedStatement.executeUpdate();
      connection.close();
	}
	catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void EditLoginTime(StudentAccessBean sab, String LI) throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
    
    Connection connection;
    try {
      String getLogoutTime = UpdateStudentData.GetLogoutTime(sab);
      
      //if logout time is less than the login time, then the login time is not valid
      if (getLogoutTime.compareTo(LI) == -1)
        LI = UpdateStudentData.GetLoginTime(sab);
      
      //set up the connection and the update query 
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
      String updateQuery = new String("UPDATE students SET LoginTime = ? WHERE ID = ?");
      PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
      preparedStatement.setString(1, LI);
      preparedStatement.setInt(2, sab.getID());
      preparedStatement.executeUpdate();
      
      //a new login time implies a new total attendance time
      //we need to update that total time as well
      String newTotalTime = new String(""); 
      if(!getLogoutTime.equals("")) {
	      int tt1 = Character.getNumericValue(LI.charAt(0)) * 60 + Integer.parseInt(LI.substring(2));
	      int tt2 = Character.getNumericValue(getLogoutTime.charAt(0)) * 60 + Integer.parseInt(getLogoutTime.substring(2));
	      int d = tt2 - tt1;
	      newTotalTime += Integer.toString(d/60);
	      d %= 60;
	      newTotalTime += ':';
	      if (d < 10) {
	    	newTotalTime += '0';
	      }
	      newTotalTime += Integer.toString(d); 
      }
      
      //update the total attendance time 
      String query = new String("UPDATE students SET TotalTimeAttended = ? WHERE ID = ?");
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setString(1, newTotalTime);
      ps.setInt(2, sab.getID());
      ps.executeUpdate();
      connection.close();
	}
	catch (SQLException e) {
	  e.printStackTrace();
    }
    System.out.print(LI);
  }
  
  public static void EditLogoutTime(StudentAccessBean sab, String LO) throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
    
    Connection connection;
    try {
    	String getLoginTime = UpdateStudentData.GetLoginTime(sab);
        if (getLoginTime.compareTo(LO) == 1)
          LO = UpdateStudentData.GetLogoutTime(sab);
        
    	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
        String updateQuery = new String("UPDATE students SET LogoutTime = ? WHERE ID = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, LO);
        preparedStatement.setInt(2, sab.getID());
        preparedStatement.executeUpdate();
        
        String newTotalTime = new String("");
        if(!getLoginTime.equals("")) {
	        int tt1 = Character.getNumericValue(LO.charAt(0)) * 60 + Integer.parseInt(LO.substring(2));
	        int tt2 = Character.getNumericValue(getLoginTime.charAt(0)) * 60 + Integer.parseInt(getLoginTime.substring(2));
	        int d = tt1 - tt2;
	        newTotalTime += Integer.toString(d/60);
	        d %= 60;
	        newTotalTime += ':';
	        if (d < 10) {
	      	newTotalTime += '0';
	        }
	        newTotalTime += Integer.toString(d);
        }
        
        String query = new String("UPDATE students SET TotalTimeAttended = ? WHERE ID = ?");
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, newTotalTime);
        ps.setInt(2, sab.getID());
        ps.executeUpdate();
        connection.close();
      } 
      catch (SQLException e) {
        e.printStackTrace();
      }
  }

  public static boolean CheckIfAttended(StudentAccessBean sab) throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");

    Connection connection;
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
      String query = new String("SELECT TotalTimeAttended from students WHERE ID = ?");
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, sab.getID());
      ResultSet rs = preparedStatement.executeQuery();
      if(rs.getString("TotalTimeAttended").equals("0:00"))
    	  return false;	
      connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
     return true;
  }
  
  public static void SetAttendanceMark(StudentAccessBean sab, String AM) throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");

    Connection connection; 
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
      String updateQuery = new String("UPDATE students SET AttendanceMark = ? WHERE ID = ?");
      PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
      preparedStatement.setInt(1, Integer.parseInt(AM));
      preparedStatement.setInt(2, sab.getID());
      preparedStatement.executeUpdate();
      connection.close();
      } 
      catch (SQLException e) {
        e.printStackTrace();
      }
  }
  
  public static String GetLoginTime(StudentAccessBean sab) throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");

    Connection connection;
    String result = new String("");
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
      String getQuery = new String("SELECT LoginTime FROM students WHERE ID = ?");
      PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
      preparedStatement.setInt(1, sab.getID());
      ResultSet rs = preparedStatement.executeQuery();
      while(rs.next())
    	  result = rs.getString("LoginTime");
      connection.close();
    } 
    catch (SQLException e) {
      e.printStackTrace();
    }
    return result;		
  }
  
  public static String GetLogoutTime(StudentAccessBean sab) throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");

    Connection connection;
    String result = new String("");
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
      String getQuery = new String("SELECT LogoutTime FROM students WHERE ID = ?");
      PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
      preparedStatement.setInt(1, sab.getID());
      ResultSet rs = preparedStatement.executeQuery();
      while(rs.next())
    	  result = rs.getString("LogoutTime");
      connection.close();
     }
     catch (SQLException e) {
       e.printStackTrace();
     }
    return result;
  }
  
  public static String GetTotalTimeAttended(StudentAccessBean sab) throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");

    Connection connection;
    String result = new String("");
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
      String getQuery = new String("SELECT TotalTimeAttended FROM students WHERE ID = ?");
      PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
      preparedStatement.setInt(1, sab.getID());
      ResultSet rs = preparedStatement.executeQuery();
      while(rs.next())
    	  result = rs.getString("TotalTimeAttended");
      connection.close();
     }
     catch (SQLException e) {
       e.printStackTrace();
     }
   return result;	
  }
  
  public static int GetAttendanceMark(StudentAccessBean sab) throws ClassNotFoundException {
	    Class.forName("com.mysql.jdbc.Driver");

	    Connection connection;
	    int result = 0;
	    try {
	      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mira");
	      String getQuery = new String("SELECT AttendanceMark FROM students WHERE ID = ?");
	      PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
	      preparedStatement.setInt(1, sab.getID());
	      ResultSet rs = preparedStatement.executeQuery();
	      while(rs.next())
	    	  result = rs.getInt("AttendanceMark");
	      connection.close();
	     }
	     catch (SQLException e) {
	       e.printStackTrace();
	     }
	   return result;	
	  }
  
@SuppressWarnings("unused")
private void printSQLException(SQLException ex) {
    for (Throwable e: ex) {
      if (e instanceof SQLException) {
        e.printStackTrace(System.err);
        System.err.println("SQLState: " + ((SQLException) e).getSQLState());
        System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
        System.err.println("Message: " + e.getMessage());
        Throwable t = ex.getCause();
        while (t != null) {
          System.out.println("Cause: " + t);
          t = t.getCause();
        }
      }
    }
  }
}

