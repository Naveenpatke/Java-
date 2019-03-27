import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.Statement;
import javax.swing.JOptionPane;


public class SqlConnection {
	
	public static void main(String args[]) {
		databaseConnector();
	}
	public static Connection databaseConnector() {
		// TODO Auto-generated method stub
		String url1="jdbc:mysql://localhost:3306/library?useSSL=false";		//url contains the path of the localHost
		String name="root";								//username of the mysql database
		String password="Naveenpatke";					//password of teh mysql database
	  try {
			Class.forName("com.mysql.cj.jdbc.Driver");		//Returns the Class object associated with the class orinterface with the given string name. Invoking this method isequivalent to: 
			
			Connection conn=DriverManager.getConnection(url1, name, password);
								//Attempts to establish a connection to the given database URL.The DriverManager attempts to select an appropriate driver fromthe set of registered JDBC drivers.
			return conn;		//return the establised the connection to the all the pages which calls this methods
		  }catch(Exception e) {
			  JOptionPane.showMessageDialog(null, e);
			  	return null;
		  }
	}
}
